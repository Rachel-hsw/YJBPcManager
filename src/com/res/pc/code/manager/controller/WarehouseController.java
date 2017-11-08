package com.res.pc.code.manager.controller;

import com.res.pc.code.manager.ManagerConntroller;
import com.res.pc.code.manager.bean.WarehouseEntity;
import com.res.pc.code.manager.bean.WarehouseVo;
import com.res.pc.code.manager.service.WarehouseService;
import com.res.pc.code.vo.PageBean;
import com.res.pc.util.OperationLogUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLClientInfoException;
import java.util.List;

/**
 * 仓库管理控制器
 * @author mz
 * @date: 2017:10:24 11:25
 */
@Controller
@RequestMapping(value = "/Warehouse")
public class WarehouseController {

    /**
     * 错误日志
     */
    private static final Logger logger = Logger.getLogger(ManagerConntroller.class);

    @Resource(name="warehouseService")
    private WarehouseService warehouseService;

    @Resource(name = "OperationLogUtils")
    private OperationLogUtils operationLogUtils;


    /**
     * 查询所有仓库信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/warehouseList")
    public String warehouseList(String FName, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("查询条件"+FName);
            WarehouseEntity warehouseEntity = new WarehouseEntity();
            if (!"".equals(FName) && null != FName) {
                warehouseEntity.setFName(FName);
            }
            List<WarehouseEntity> warehouseEntityList = warehouseService.queryAllWarehouse(warehouseEntity);

            request.setAttribute("warehouseEntityList", warehouseEntityList);
            request.setAttribute("FName", FName);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
        }
        return "manager/warehouseList";
    }

    /**
     * 分页查询
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/warehousePageLise")
    public String warehousePageLise(WarehouseVo vo, HttpServletRequest request, HttpServletResponse response) {
        PageBean<WarehouseEntity> pageBean = null;
        int count = 0;
        vo.setBegin(vo.getCurrentPage());
        vo.setEnd("20");

        try {
            pageBean = warehouseService.queryWarehouseCount(vo);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
            e.printStackTrace();
        }
        request.setAttribute("vo", vo);
        request.setAttribute("pageBean", pageBean);
        return "manager/warehouseList";
    }

    /**
     * 删除仓库
     * @param
     * @return
     */
    @RequestMapping("/deleteWarehouse")
    public String deleteWarehouse(WarehouseVo vo, HttpServletRequest request, HttpServletResponse response) {
        logger.info("删除" + vo.getFId());
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        warehouseEntity.setFId(Long.parseLong(vo.getFId()));
         //long FId1 = 1;
        try {
            warehouseService.deleteWarehouse(warehouseEntity);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
            e.printStackTrace();
        }

         //return warehouseList(request, response);
        return "redirect:/Warehouse/warehousePageLise.do";
    }

    /**
     * 跳转到新增页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/toAddWarehouse")
    public ModelAndView toAddWarehouse(String method, HttpServletRequest request, HttpServletResponse response){
        logger.info("去新增页面");
        ModelAndView view = new ModelAndView("manager/addwarehouse");
        request.setAttribute("method", method);
        return view;
    }

    /**
     * 新增仓库
     * @param warehouseEntity
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("addWarehouse")
    public String addWarehouse(WarehouseEntity warehouseEntity, HttpServletRequest request, HttpServletResponse response){
        logger.info("进来了");
        logger.info(warehouseEntity.getFName());
        try {
            warehouseService.addWarehouse(warehouseEntity);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
            e.printStackTrace();
        }
        return "redirect:/Warehouse/warehousePageLise.do";
    }

    /**
     * 跳转到更新页面
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(WarehouseVo vo, String method, HttpServletRequest request, HttpServletResponse response){
        logger.info("去更新页面");
        logger.info(vo.getFId());

        ModelAndView view = new ModelAndView("manager/addwarehouse");

        WarehouseEntity warehouse = null;

        try {
            WarehouseEntity warehouseEntity = new WarehouseEntity();
            warehouseEntity.setFId(Long.parseLong(vo.getFId()));
            //查询单个仓库
            warehouse  = warehouseService.queryWarehouseById(warehouseEntity);
            logger.info(warehouse);

            request.setAttribute("warehouse", warehouse);
            request.setAttribute("method",method);
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
            e.printStackTrace();
        }
        return view;
    }

    /**
     * 保存的仓库信息
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("saveWarehouse")
    public String updateWarehouse(WarehouseVo vo, String method, HttpServletRequest request, HttpServletResponse response){
        logger.info("新增或修改仓库");
        WarehouseEntity warehouseEntity = new WarehouseEntity();

        if (null!=vo.getFId() &&!"".equals(vo.getFId())) {
            //如果是修改，则保存id
            warehouseEntity.setFId(Long.parseLong(vo.getFId()));
        }
        warehouseEntity.setFName(vo.getFName());
        warehouseEntity.setFAddress(vo.getFAddress());
        warehouseEntity.setFTel(vo.getFTel());
        warehouseEntity.setFRemark(vo.getFRemark());

        try {
            if ("update".equals(method)) {
                //更新操作
                warehouseService.updateWarehouse(warehouseEntity);
            } else {
                //新增
                warehouseService.addWarehouse(warehouseEntity);
            }
        } catch (SQLClientInfoException e) {
            logger.error("数据库异常",e);
            e.printStackTrace();
        }
        return "redirect:/Warehouse/warehousePageLise.do";
    }
}
