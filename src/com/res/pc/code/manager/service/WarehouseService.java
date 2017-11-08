package com.res.pc.code.manager.service;

import com.res.pc.code.manager.bean.WarehouseBean;
import com.res.pc.code.manager.bean.WarehouseEntity;
import com.res.pc.code.manager.bean.WarehouseVo;
import com.res.pc.code.manager.dao.WarehouseDao;
import com.res.pc.code.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLClientInfoException;
import java.util.List;

/**
 * 仓库管理service
 * @author mz
 * @date: 2017:10:24 11:09
 */
@Service("warehouseService")
public class WarehouseService {

    //引入dao
    @Resource(name = "warehouseDao")
    private WarehouseDao warehouseDao;

    /**
     * 查询所有仓库
     * @return
     * @throws SQLClientInfoException
     */
    public List<WarehouseEntity> queryAllWarehouse(WarehouseEntity warehouseEntity)throws SQLClientInfoException {
        return warehouseDao.queryAllWarehouse(warehouseEntity);
    }

    /**
     * 分页查询
     * @param vo
     * @return
     * @throws SQLClientInfoException
     */
    public PageBean<WarehouseEntity> queryWarehouseCount(WarehouseVo vo) throws SQLClientInfoException {
        PageBean<WarehouseEntity> pageBean=new PageBean();
        //设置开始页
        pageBean.setCurrentPage(Integer.valueOf(vo.getBegin()));
        //设置尾页
        pageBean.setCurrentCount(Integer.valueOf(vo.getEnd()));
        //总条数
        Integer totalCount = warehouseDao.queryWarehouseCount(vo);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        int totalPage=(totalCount / 20) + 1;
        pageBean.setTotalPage(totalPage);

        vo.setEnd(String.valueOf( Integer.valueOf(vo.getBegin())* 20));
        vo.setBegin(String.valueOf((Integer.valueOf(vo.getBegin()) - 1) * 20+ 1));
        pageBean.setQueryNumberList(warehouseDao.queryWarehouse(vo));
        return pageBean;
    }

    /**
     * 更新仓库信息
     * @param warehouseEntity
     * @throws SQLClientInfoException
     */
    public void updateWarehouse(WarehouseEntity warehouseEntity)throws SQLClientInfoException {
        warehouseDao.updateWarehouse(warehouseEntity);
    }

    /**
     * 删除仓库
     * @param warehouseEntity
     * @throws SQLClientInfoException
     */
    public void deleteWarehouse(WarehouseEntity warehouseEntity)throws SQLClientInfoException {
        warehouseDao.deleteWarehouse(warehouseEntity);
    }

    /**
     * 新增仓库
     * @param warehouseEntity
     * @throws SQLClientInfoException
     */
    public void addWarehouse(WarehouseEntity warehouseEntity)throws SQLClientInfoException {
        warehouseDao.addWarehouse(warehouseEntity);
    }

    /**
     * 根据id查询单个仓库信息
     * @param warehouseEntity
     * @return
     */
    public WarehouseEntity queryWarehouseById(WarehouseEntity warehouseEntity)throws SQLClientInfoException {
        return warehouseDao.queryWarehouseById(warehouseEntity);
    }
}
