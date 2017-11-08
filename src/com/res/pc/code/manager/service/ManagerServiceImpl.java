/**
 * 
 */
package com.res.pc.code.manager.service;

import java.sql.SQLClientInfoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.res.pc.code.manager.bean.DeliveryBean;
import com.res.pc.code.manager.bean.DriverBean;
import com.res.pc.code.manager.bean.InitialNavigationsInfo;
import com.res.pc.code.manager.bean.MyCondition;
import com.res.pc.code.manager.bean.OperationLog;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.PayMainBean;
import com.res.pc.code.manager.bean.RewardBean;
import com.res.pc.code.manager.bean.RoleInfo;
import com.res.pc.code.manager.bean.SupplierBean;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.manager.bean.WarehouseBean;
import com.res.pc.code.manager.bean.PriceBean;
import com.res.pc.code.manager.bean.Product;
import com.res.pc.code.manager.bean.PurItemBean;
import com.res.pc.code.manager.bean.PurchaseBean;
import com.res.pc.code.manager.bean.PurchasingBean;
import com.res.pc.code.manager.bean.QueryProdVo;
import com.res.pc.code.manager.bean.QuerySupplierVo;
import com.res.pc.code.manager.bean.configInfo;
import com.res.pc.code.manager.bean.view_orderdetail;
import com.res.pc.code.manager.dao.ManagerDaoImpl;
import com.res.pc.code.vo.BeginingVo;
import com.res.pc.code.vo.BeginningCondition;
import com.res.pc.code.vo.NumberCondition;
import com.res.pc.code.vo.PageBean;
import com.res.pc.code.vo.QueryNumberVo;
import com.res.pc.util.MD5Util;
import com.res.pc.util.PageInfo;
import com.res.pc.util.StringTools;

/**
 * @author liuqiang 管理台登录service
 */
@Service("managerService")
public class ManagerServiceImpl {

	/**
	 * 引入Dao
	 */
	@Resource(name = "managerDao")
	private ManagerDaoImpl managerDao;

	/**
	 * 登录
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo login(UserInfo info) throws SQLClientInfoException {
		info.setPassword(MD5Util.getMD5String(info.getPassword()));
		return managerDao.login(info);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<UserInfo> queryUserList(PageInfo info) throws SQLClientInfoException {
		info.setStart((info.getPage() - 1) * 10);
		info.setEnd(info.getPage() * 10);
		return managerDao.queryUserList(info);
	}

	/**
	 * 查询用户列表总条数
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryUserListCount(PageInfo info) throws SQLClientInfoException {
		return managerDao.queryUserListCount(info);
	}

	public List<configInfo> queryconfigList(PageInfo info) throws SQLClientInfoException {
		info.setStart((info.getPage() - 1) * 10);
		info.setEnd(info.getPage() * 10);
		return managerDao.queryconfigList(info);
	}

	public Integer queryconfigListCount() throws SQLClientInfoException {
		return managerDao.queryconfigListCount();
	}

	/**
	 * 修改用户信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateUserInfo(UserInfo info) throws SQLClientInfoException {
		managerDao.updateUserInfo(info);
	}

	/**
	 * 删除用户
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteUserInfo(UserInfo info) throws SQLClientInfoException {
		managerDao.deleteUserInfo(info);
	}

	/**
	 * 查询用户名是否存在
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryUserAccount(UserInfo info) throws SQLClientInfoException {
		return managerDao.queryUserAccount(info);
	}

	/**
	 * 修改密码
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePW(UserInfo info) throws SQLClientInfoException {
		info.setPassword(MD5Util.getMD5String(info.getPassword()));
		managerDao.updatePW(info);
	}

	public void updateconfig(configInfo config) throws SQLClientInfoException {
		managerDao.updateconfig(config);
	}

	/**
	 * 查询用户详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo queryUserInfo(UserInfo info) throws SQLClientInfoException {
		return managerDao.queryUserInfo(info);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateUserInfo1(UserInfo info) throws SQLClientInfoException {
		managerDao.updateUserInfo(info);
	}

	/**
	 * 查询菜单列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<InitialNavigationsInfo> queryINList(PageInfo info) throws SQLClientInfoException {
		info.setStart((info.getPage() - 1) * 10);
		info.setEnd(info.getPage() * 10);
		return managerDao.queryNIList(info);
	}

	/**
	 * 查询菜单列表总条数
	 * 
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryINCount() throws SQLClientInfoException {
		return managerDao.queryNICount();
	}

	/**
	 * 添加菜单
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String addINInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		String result = "ok";

		// 查询用户名是否存在
		Integer aut = managerDao.queryINAccount(info);

		if (aut > 0) {
			return "error";
		}

		managerDao.addNIInfo(info);

		return result;
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateINInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		managerDao.updateNIInfo(info);
	}

	/**
	 * 删除菜单
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteINInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		managerDao.deleteNIInfo(info);
	}

	/**
	 * 查询菜单详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public InitialNavigationsInfo queryINInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		return managerDao.queryINInfo(info);
	}

	/**
	 * 查询角色列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<RoleInfo> queryRoleList(PageInfo info) throws SQLClientInfoException {
		info.setStart((info.getPage() - 1) * 10);
		info.setEnd(info.getPage() * 10);
		return managerDao.queryRoleList(info);
	}

	/**
	 * 查询角色列表总条数
	 * 
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryRoleCount() throws SQLClientInfoException {
		return managerDao.queryRoleCount();
	}

	/**
	 * 添加角色
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String addRoleInfo(RoleInfo info) throws SQLClientInfoException {
		String result = "ok";

		// 查询角色名是否存在
		Integer aut = managerDao.queryRoleAccount(info);

		if (aut > 0) {
			return "error";
		}

		managerDao.addRoleInfo(info);

		return result;
	}

	/**
	 * 修改角色信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateRoleInfo(RoleInfo info) throws SQLClientInfoException {
		managerDao.updateRoleInfo(info);
	}

	/**
	 * 删除角色
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteRoleInfo(RoleInfo info) throws SQLClientInfoException {
		managerDao.deleteRoleInfo(info);
	}

	/**
	 * 查询角色详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public RoleInfo queryRoleInfo(RoleInfo info) throws SQLClientInfoException {
		return managerDao.queryIRolenfo(info);
	}

	/**
	 * IDS查菜单
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<InitialNavigationsInfo> queryINByIds(String ids) throws SQLClientInfoException {

		return managerDao.queryNIByIds(ids);
	}

	/**
	 * IDS查用户
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<UserInfo> queryUsersByIds(String ids) throws SQLClientInfoException {

		return managerDao.queryUsersByIds(ids);
	}

	/**
	 * 
	 * 根据用户查询菜
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public List<InitialNavigationsInfo> queryINByUserId(String UserId) throws SQLClientInfoException {

		List<RoleInfo> roleInfo = managerDao.queryRoleByUserId(UserId);
		String inid = "";
		for (int i = 0; i < roleInfo.size(); i++) {
			inid += roleInfo.get(i).getPower() + ",";
		}
		List<InitialNavigationsInfo> inInfos = new ArrayList<InitialNavigationsInfo>();
		if (!"".equals(inid)) {
			inid = inid.substring(0, inid.length() - 1);
			inInfos = managerDao.queryNIByIds(inid);
		}
		List<InitialNavigationsInfo> rinInfos = new ArrayList<InitialNavigationsInfo>();
		for (int i = 0; i < inInfos.size(); i++) {
			if (inInfos.get(i).getIsMoveby() == 0) {

				rinInfos.add(inInfos.get(i));
			}
		}

		return rinInfos;
	}

	public List<InitialNavigationsInfo> queryAllIN() throws SQLClientInfoException {

		return managerDao.queryAllIN();
	}

	public List<UserInfo> queryAllUser() throws SQLClientInfoException {

		return managerDao.queryAllUser();
	}

	public List<UserInfo> queryAllUser2() throws SQLClientInfoException {

		return managerDao.queryAllUser2();
	}

	/**
	 * 修改折扣信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String updateZk(PriceBean zkbean) throws SQLClientInfoException {
		String result = "ok";

		managerDao.updateZk(zkbean);

		return result;
	}

	// 获取折扣列表信息
	public List<PriceBean> queryAllZk() throws SQLClientInfoException {

		return managerDao.queryAllZk();
	}

	// 获取返利列表信息
	public List<RewardBean> queryAllReward() throws SQLClientInfoException {

		return managerDao.queryAllReward();
	}

	/**
	 * 修改利列信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String updateReward(RewardBean rewardbean) throws SQLClientInfoException {
		String result = "ok";

		managerDao.updateReward(rewardbean);

		return result;
	}

	/**
	 * 修改折扣信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String addPurchase(PurchaseBean purchaseBean) throws SQLClientInfoException {
		String result = "ok";

		managerDao.addPurchase(purchaseBean);

		return result;
	}

	// 获取折扣列表信息
	public List<PurchaseBean> queryPurchase() throws SQLClientInfoException {

		return managerDao.queryPurchase();
	}

	// 获取仓库表信息
	public List<WarehouseBean> queryAllWarehouse() throws SQLClientInfoException {

		return managerDao.queryAllWarehouse();
	}

	// 获取会员表信息
	public List<UserInfo> queryAllUserB() throws SQLClientInfoException {

		return managerDao.queryAllUserB();
	}

	// 获取驾驶员表信息
	public List<DriverBean> queryAllDriver() throws SQLClientInfoException {

		return managerDao.queryAllDriver();
	}

	// 获取进货单位表信息
	public List<SupplierBean> queryAllSupplier() throws SQLClientInfoException {

		return managerDao.queryAllSupplier();
	}


	// 获取商品表信息
	public List<Product> queryAllProduct() throws SQLClientInfoException {

		return managerDao.queryAllProduct();
	}

	/*// 提交采货单明细
	public void updatepurchasing(PurItemBean purItemBean) throws SQLClientInfoException {
		managerDao.updatepurchasing(purItemBean);

	}*/

	// 提交采货单主表
	public boolean updatepurchasingM(MyCondition condition) throws SQLClientInfoException {
		if(condition.getItemsList()!=null){
			List<PurItemBean> itemsList=condition.getItemsList();
			for (PurItemBean purItemBean : itemsList) {
				//商品名称,规格            ,单位        ,单价            ,数量        ,重量,金额          ,备注
				//FP_Id ,FP_Price,FP_Num,FP_ZL,FP_Money,Remark
				if(purItemBean.getFP_Id()!=null&&!"".equals(purItemBean.getFP_Id())){
					managerDao.updatepurchasing(purItemBean);//更新采购明细表
					purItemBean.setFW_Id(condition.getFW_Id());
					managerDao.updateStock(purItemBean); //更新库存表
					
					}
				//更新采购明细表//更新库存表//更新采购主表//更新采购金额表//更新供应商表 //更新供应商资金变动表//更新字号表
			}
			
			managerDao.updatepurchasingM(condition);//更新采购主表
			managerDao.updateAmount(condition);//更新采购金额表
			managerDao.updateSupplier(condition);//更新供应商表
			managerDao.updateCfg(condition); //更新供应商资金变动表
			managerDao.updateBillnumber(condition);//更新字号表
			}
		managerDao.updatepurchasingM(condition);
		return true;
		
	}

	// 提交退货单明细
	public void updatereturn(PurItemBean purItemBean) throws SQLClientInfoException {
		managerDao.updatereturn(purItemBean);

	}

	// 提交退货单主表
	public void updatereturnM(MyCondition condition) throws SQLClientInfoException {
		managerDao.updatereturnM(condition);
	}

	// 更新采购金额表
	public void updateAmount(MyCondition condition) throws SQLClientInfoException {
		managerDao.updateAmount(condition);
	}

	// 更新供应商表
	public void updateSupplier(MyCondition condition) throws SQLClientInfoException {
		managerDao.updateSupplier(condition);
	}

	// 更新供应商资金变动表
	public void updateCfg(MyCondition condition) throws SQLClientInfoException {
		managerDao.updateCfg(condition);
	}

	// 单号表
	public void updateBillnumber(MyCondition condition) throws SQLClientInfoException {
		managerDao.updateBillnumber(condition);
	}

	// 库存
	public void updateStock(PurItemBean purItemBean) throws SQLClientInfoException {
		managerDao.updateStock(purItemBean);
	}

	// 获取会员表信息
	public List<Orders> queryAllOrdersList() throws SQLClientInfoException {

		return managerDao.queryAllOrdersList();
	}

	// 获取会员表信息
	public List<Orders> queryAllOrdersList(String query) throws SQLClientInfoException {

		return managerDao.queryAllOrdersList(query);
	}

	// 获取会员表信息
	public List<Orders> queryAllOrdersList(int FBillID) throws SQLClientInfoException {

		return managerDao.queryAllOrdersList(FBillID);
	}

	// 获取会员表信息
	public List<view_orderdetail> queryAllOrderDetail(int FBillID) throws SQLClientInfoException {

		return managerDao.queryAllOrderDetail(FBillID);
	}
	
	// 获取会员表信息
	public List<view_orderdetail> queryPurchaseDetail(int FBillID) throws SQLClientInfoException {
		
		return managerDao.queryPurchaseDetail(FBillID);
	}

	// 提交发货单
	public void updateDeliver_Goods(DeliveryBean deliveryBean) throws SQLClientInfoException {
		managerDao.updateDeliver_Goods(deliveryBean);
	}
	// 提交发货单
	public void updateOrders_Status(int FBillID) throws SQLClientInfoException {
		managerDao.updateOrders_Status(FBillID);
	}
	// 提交发货单
	public void updatePurchase_Status(int FBillID) throws SQLClientInfoException {
		managerDao.updatePurchase_Status(FBillID);
	}
	// 提交发货单
	public void updateAmountPay(int FBillID) throws SQLClientInfoException {
		managerDao.updateAmountPay(FBillID);
	}
	// 提交发货单
	public void updateSupplier1(PayMainBean payMainBean) throws SQLClientInfoException {
		managerDao.updateSupplier1(payMainBean);
	}
	// 库存
		public void updateStock2(PurItemBean purItemBean) throws SQLClientInfoException {
			managerDao.updateStock2(purItemBean);
		}
		// 获取采购列表
		public List<PurchasingBean> queryPurchasing( String FId) throws SQLClientInfoException {
			
			return managerDao.queryPurchasing(FId);
		}
		public PageBean<SupplierBean> querySupplierList(QuerySupplierVo vo) throws SQLClientInfoException {
			 //目的：就是封装一个PageBean 并返回
			PageBean<SupplierBean> pageBean=new PageBean();
			pageBean.setCurrentPage(Integer.valueOf(vo.getBegin()));
			pageBean.setCurrentCount(Integer.valueOf(vo.getEnd()));
			Integer totalCount=managerDao.querySupplierListCount(vo);
			pageBean.setTotalCount(totalCount);
			int totalPage=(totalCount / 20) + 1;
			pageBean.setTotalPage(totalPage);
			vo.setEnd(String.valueOf( Integer.valueOf(vo.getBegin())* 20));
			vo.setBegin(String.valueOf((Integer.valueOf(vo.getBegin()) - 1) * 20+ 1));
			  
				pageBean.setQueryNumberList( managerDao.querySupplierList(vo));
			   return pageBean;
		}


		public SupplierBean querySupplierInfo(SupplierBean reqInfo) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			return managerDao.querySupplierInfo(reqInfo);
		}


		public void deleteSupplierInfo(SupplierBean supplierBean) throws SQLClientInfoException {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			supplierBean.setFendtime(df.format(new Date()));
			managerDao.deleteSupplierInfo(supplierBean);
		}


		public void updateSupplierInfo(SupplierBean supplierInfo) throws SQLClientInfoException {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			supplierInfo.setFendtime(df.format(new Date()));
			managerDao.updateSupplierInfo(supplierInfo);
			
		}


		public void addSupplierInfo(SupplierBean supplierInfo) throws SQLClientInfoException {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			supplierInfo.setFbeginTime(df.format(new Date()));
			managerDao.addSupplierInfo(supplierInfo);
			
		}
	public List<Product> findAllProd(QueryProdVo vo) throws SQLClientInfoException {
			
			return managerDao.findAllProd(vo);
		}
	
	public Product queryProductInfo(String prod_id)throws SQLClientInfoException {
		return managerDao.queryProductInfo(prod_id);
	}
	
	public void deleteProductInfo(Product product) throws SQLClientInfoException{
		managerDao.deleteProductInfo(product);
		
	}
	public void updateProductInfo(Product product) throws SQLClientInfoException{
		managerDao.updateProductInfo(product);
		
	}
	public void updateT_Qk_FK(PayMainBean payMainBean) throws SQLClientInfoException{
		managerDao.updateT_Qk_FK(payMainBean);
		
	}
	public void addProductInfo(Product product) throws SQLClientInfoException {
		managerDao.addProductInfo(product);
		
	}
}


