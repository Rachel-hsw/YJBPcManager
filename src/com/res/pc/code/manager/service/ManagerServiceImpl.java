/**
 * 
 */
package com.res.pc.code.manager.service;

import java.math.BigDecimal;
import java.sql.SQLClientInfoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.res.pc.code.customer.bean.Rebate;
import com.res.pc.code.manager.bean.Billnumber;
import com.res.pc.code.manager.bean.DeliveryBean;
import com.res.pc.code.manager.bean.DriverBean;
import com.res.pc.code.manager.bean.EvaluateBean;
import com.res.pc.code.manager.bean.ExpenseBean;
import com.res.pc.code.manager.bean.InitialNavigationsInfo;
import com.res.pc.code.manager.bean.MyCondition;
import com.res.pc.code.manager.bean.OperationLog;
import com.res.pc.code.manager.bean.Orders;
import com.res.pc.code.manager.bean.PDBean;
import com.res.pc.code.manager.bean.PDItemBean;
import com.res.pc.code.manager.bean.PackageBean;
import com.res.pc.code.manager.bean.PackageItem;
import com.res.pc.code.manager.bean.PayMainBean;
import com.res.pc.code.manager.bean.RoleInfo;
import com.res.pc.code.manager.bean.SpdBean;
import com.res.pc.code.manager.bean.SupplierBean;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.manager.bean.WarehouseBean;
import com.res.pc.code.manager.bean.WithdrawBean;
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
	public List<SpdBean> queryRebateByType() throws SQLClientInfoException {

		return managerDao.queryRebateByType();
	}

	/**
	 * 修改利列信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String updateRebateByTypeAndLv(SpdBean spdBean) throws SQLClientInfoException {
		String result = "ok";

		managerDao.updateRebateByTypeAndLv(spdBean);

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
	public List<UserInfo> queryAllUserB(String userlb) throws SQLClientInfoException {

		return managerDao.queryAllUserB(userlb);
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
	// 获取商品表信息
	public List<MyCondition> findFSourceId(MyCondition condition) throws SQLClientInfoException {
		
		return managerDao.findFSourceId(condition);
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
					purItemBean.setFS_Date(condition.getFS_Date());
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

	// 查询条件查询订单
	public List<Orders> queryAllOrdersList(String query) throws SQLClientInfoException {

		return managerDao.queryAllOrdersList(query);
	}

/*	// 根据日期查询待发货订单列表
	public List<Orders> queryAllOrdersLists(String query) throws SQLClientInfoException {

		return managerDao.queryAllOrdersLists(query);
	}*/

	// 查询指定待发货订单
	public List<Orders> queryAllOrdersListss(String FBillID) throws SQLClientInfoException {

		return managerDao.queryAllOrdersListss(FBillID);
	}

	// 获取会员表信息
	public List<view_orderdetail> queryAllOrderDetail(String  FBillID) throws SQLClientInfoException {

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
	public void updateOrders_Status(String FBillID) throws SQLClientInfoException {
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
		// 获取指定仓库下的商品列表
		public List<Product> queryInventory( String FId) throws SQLClientInfoException {
			
			return managerDao.queryInventory(FId);
		}
		//获取目前数据库中的最大派单号
		public List<PDBean> queryPDBean( String FId) throws SQLClientInfoException {
			
			return managerDao.queryPDBean(FId);
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
	//更新盘点明细表
	public void updatePDItem(PDItemBean PDItemBean) throws SQLClientInfoException{
		managerDao.updatePDItem(PDItemBean);
		
	}
	//更新盘点主表
	public void updatePD(PDBean PDBean) throws SQLClientInfoException{
		managerDao.updatePD(PDBean);
		
	}

	/**
	 *	query EvaluateBean list by condition 根据条件查询评价列表
	 */
	public List<EvaluateBean> queryEvaluateListByCond(PageInfo info) {
		return managerDao.queryEvaluateListByCond(info);
	}

	/**
	 *	query EvaluateBean count by condition 根据条件查询评价总数
	 */
	public Integer queryCountInEvaluateByCond(PageInfo info) {
		return managerDao.queryEvaluateCountByCond(info);
	}
	/**
	 *	查询套餐列表
	 */
	public List<PackageBean> queryPackageList() {
		return managerDao.queryPackageList();
	}
	
	//根据套餐id查询套餐明细
	public List<PackageItem> queryPackageDetail(String id) {
		return managerDao.queryPackageDetail(id);
	}
	//根据套餐id查询套餐明细
	public List<Product> queryProductList() {
		return managerDao.queryProductList();
	}
	//根据套餐id查询套餐明细
	public String queryMaxPackage_id() {
		return managerDao.queryMaxPackage_id();
	}
	/**
	 * 修改用户信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public boolean updatePackage(PackageBean condition) throws SQLClientInfoException {
		 String P_Package_id=managerDao.queryMaxPackage_id();
		 condition.setP_Package_id(P_Package_id);
		//更新盘点主表
		managerDao.updatePackage(condition);
		if(condition.getItemsList()!=null){
			List<PackageItem> itemsList=condition.getItemsList();
			for (PackageItem PackageItem : itemsList) { 
				PackageItem.setP_Package_id(P_Package_id);
				if(PackageItem.getP_Id()!=null&&!"".equals(PackageItem.getP_Id())){
				managerDao.updatePackageItem(PackageItem); 
				}
					}
				
			
		
	}
		return true;
}
	/**
	 * 修改用户信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public boolean updatePackage2(PackageBean condition) throws SQLClientInfoException {
		managerDao.delItemById(condition.getP_Package_id());
		managerDao.delPackageById(condition.getP_Package_id());
			//更新盘点主表
			managerDao.updatePackage(condition);
			if(condition.getItemsList()!=null){
				List<PackageItem> itemsList=condition.getItemsList();
				for (PackageItem PackageItem : itemsList) { 
					PackageItem.setP_Package_id(condition.getP_Package_id());
					if(PackageItem.getP_Id()!=null&&!"".equals(PackageItem.getP_Id())){
					managerDao.updatePackageItem(PackageItem); 
						
						}
					
				}
			
		}
		
	
		return true;
}
	/**
	 * 一般费用提交和其他收入提交
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public boolean updateExpenseOrEarn(ExpenseBean condition) throws SQLClientInfoException {
	//插入数据
	managerDao.updateExpenseOrEarn(condition);
		return true;
	}
	// 获取商品表信息
	public List<PackageItem> queryProductbyId(String  P_Package_id) throws SQLClientInfoException {

		return managerDao.queryProductbyId(P_Package_id);
	}
	/**
	 *	查询套餐列表
	 */
	public List<PackageBean> queryPackagebyId(String  P_Package_id) {
		return managerDao.queryPackagebyId(P_Package_id);
	}
	// 查询采购单列表
	public List<PurchasingBean> queryPurchaseList(String query) throws SQLClientInfoException {

		return managerDao.queryPurchaseList(query);
	}
	//查询采购付款单列表
	public List<PayMainBean> queryPayList(String query) throws SQLClientInfoException {
		
		return managerDao.queryPayList(query);
	}
	//查询采购付款单列表
	public List<PayMainBean> queryPayListByID(String FId) throws SQLClientInfoException {
		
		return managerDao.queryPayListByID(FId);
	}
	//根据采购单id查询采购单
	public List<PurchasingBean> queryPurchaseListByID(String FBillID) throws SQLClientInfoException {
		
		return managerDao.queryPurchaseListByID(FBillID);
	}
	//根据采购单id查询采购单
	public List<PurItemBean> purchaseItemDetail(String FBillID) throws SQLClientInfoException {
		
		return managerDao.purchaseItemDetail(FBillID);
	}
	public UserInfo findLevelByUserid(String FBillID) throws SQLClientInfoException {
		
		return managerDao.findLevelByUserid(FBillID);
	}
	public Rebate queryIfEnough(String FBillID) throws SQLClientInfoException {
		
		return managerDao.queryIfEnough(FBillID);
	}
	public UserInfo queryUserById(String FBillID) throws SQLClientInfoException {
		
		return managerDao.queryUserById(FBillID);
	}

	public SpdBean findRateByLv(SpdBean FBillID) {
		return managerDao.findRateByLv(FBillID);
	}
	public void shengji(Orders order,String myLevel){
		//第一次购买本人折扣
	    SpdBean spdzk=new SpdBean();	   
	    spdzk.setFtype("1");
	    spdzk.setLv("1");
	    SpdBean zk =managerDao.findRateByLv(spdzk);
	    //查询一级推荐人
	    UserInfo recUser1 =managerDao.findRecUser1ByUserid(order.getUser_id());//2
	    //查询二级推荐人
	    UserInfo recUser2 =managerDao.findRecUser2ByUserid(order.getUser_id());
	    //查询省
	    UserInfo Province =managerDao.findProvinceByUserid(order.getUser_id());
	    //查询市
	    UserInfo City =managerDao.findCityByUserid(order.getUser_id());
	    //查询区
	    UserInfo County =managerDao.findCountyByUserid(order.getUser_id());	    
	    SpdBean fanli=new SpdBean();
	    //查询第一次购买一级推荐人的返利
	    fanli.setFtype("1");
	    fanli.setLv("2");
	    SpdBean Rebate1forRecUser1 =managerDao.findRateByLv(fanli);//0.25
	    //查询第一次购买二级推荐人的返利
	    fanli.setFtype("1");
	    fanli.setLv("3");
	    SpdBean Rebate1forRecUser2 =managerDao.findRateByLv(fanli);//0.1
	    //查询第一次购买省的返利
	    fanli.setFtype("1");
	    fanli.setLv("4");
	    SpdBean Rebate1forProvince =managerDao.findRateByLv(fanli);//0.05
	    //查询第一次购买市的返利
	    fanli.setFtype("1");
	    fanli.setLv("5");
	    SpdBean Rebate1forCity =managerDao.findRateByLv(fanli);//0.05
	    //查询第一次购买县的返利
	    fanli.setFtype("1");
	    fanli.setLv("6");
	    SpdBean Rebate1forCounty =managerDao.findRateByLv(fanli);//0.05
	    



		 //升级为高级会员
		 //会员本身
		 // 积分	返利	销售额	毛利润	电子币
		 //更新会员等级
	     UserInfo userInfo =new UserInfo(); 
	     userInfo.setUserid(order.getUser_id());
		 userInfo.setU_Llevel(myLevel);
		 managerDao.updateLevelbyUserID(userInfo);
		 //本人返利信息
		 Rebate rebate=new Rebate();
		 rebate.setFUser_Id(order.getUser_id());//会员ID
		 rebate.setFIntegral(order.getFje());//积分
		 rebate.setFRebate("0");//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain("0");//利润
		 rebate.setFe_vouche(order.getFje());//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("升级付款");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);
		 //返利
		 String RebaterecUser1=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser1.getRate()));
		 String RebaterecUser2=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser2.getRate()));
		 String RebateProvince=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forProvince.getRate()));
		 String RebateCity=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCity.getRate()));
		 String RebateCounty=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCounty.getRate()));
		 //一级推荐人
		/* if (!recUser1.getUserid().equals("0")) {*/
		 rebate.setFUser_Id(recUser1.getUserid());//会员ID
		 rebate.setFIntegral("0");//积分
		 rebate.setFRebate(RebaterecUser1);//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser1.getRate())));//利润
		 rebate.setFe_vouche("0");//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("一级推荐奖："+Double.parseDouble(Rebate1forRecUser1.getRate())*100+"%");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);/*}*/
		 //二级推荐人
		/* if (!recUser2.getUserid().equals("0")) {*/

		 rebate.setFUser_Id(recUser2.getUserid());//会员ID
		 rebate.setFIntegral("0");//积分
		 rebate.setFRebate(RebaterecUser2);//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser2.getRate())));//利润
		 rebate.setFe_vouche("0");//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("二级推荐奖:"+Double.parseDouble(Rebate1forRecUser2.getRate())*100+"%");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);/*}*/
		 //省
		 if (!"".equals(Province)&&null!=Province) {
			 rebate.setFUser_Id(Province.getUserid());//会员ID
		 }else{
			 rebate.setFUser_Id("0");//会员ID
		 }
   		 rebate.setFIntegral("0");//积分
   		 rebate.setFRebate(RebateProvince);//返利
   		 rebate.setFAmount(order.getFje());//销售额
   		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forProvince.getRate())));//利润
   		 rebate.setFe_vouche("0");//电子币
   		 //--会员返利表select *  from T_Qk_User_Rebate
   		  rebate.setFOrder_ID(order.getFBillID());
   		  rebate.setFRebate_name("省区域福利奖:"+Double.parseDouble(Rebate1forProvince.getRate())*100+"%");
   		  managerDao.addRebate(rebate);
   		 //--会员账务表select *  from T_Qk_User_Accounting
   		 managerDao.updateAccounting(rebate);
		
		
		 //市
		 if (!"".equals(City)&&null!=City) {
		 rebate.setFUser_Id(City.getUserid());//会员ID 
		 }else{
			 rebate.setFUser_Id("0");//会员ID 
		 }
		 rebate.setFIntegral("0");//积分
		 rebate.setFRebate(RebateCity);//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCity.getRate())));//利润
		 rebate.setFe_vouche("0");//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("市区域福利奖:"+Double.parseDouble(Rebate1forCity.getRate())*100+"%");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);
		
		 //区
		 
		 if (!"".equals(County)&&null!=County) {
		 rebate.setFUser_Id(County.getUserid());//会员ID
		 }else{
			 rebate.setFUser_Id("0");//会员ID
		 }
		 rebate.setFIntegral("0");//积分
		 rebate.setFRebate(RebateCounty);//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCounty.getRate())));//利润
		 rebate.setFe_vouche("0");//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("区区域福利奖:"+Double.parseDouble(Rebate1forCounty.getRate())*100+"%");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);
		
	/*	 //公司
		 rebate.setFUser_Id("0");//会员ID
		 rebate.setFIntegral(order.getFje());//积分
		 double i=Double.parseDouble( Rebate1forRecUser1.getRate())+ Double.parseDouble( Rebate1forRecUser2.getRate())+Double.parseDouble( Rebate1forProvince.getRate())+Double.parseDouble( Rebate1forCity.getRate())+Double.parseDouble( Rebate1forCounty.getRate());
		 rebate.setFRebate(String.valueOf(Double.parseDouble(order.getFje())*(1+i)));//返利
		 rebate.setFAmount(order.getFje());//销售额
		 rebate.setFGain(String.valueOf(Double.parseDouble(order.getFje())*(1+i)));//利润
		 rebate.setFe_vouche("0");//电子币
		 rebate.setFRemark("0");//电子币
		 //--会员返利表select *  from T_Qk_User_Rebate
		  rebate.setFOrder_ID(order.getFBillID());
		  rebate.setFRebate_name("公司返利");
		  managerDao.addRebate(rebate);
		 //--会员账务表select *  from T_Qk_User_Accounting
		 managerDao.updateAccounting(rebate);*/
		  order.setFSl(String.valueOf(Double.parseDouble(order.getFje())/Double.parseDouble(zk.getRate())));//2000
		  order.setFdiscount(zk.getRate());
	}

	public String  queryshengjiRebate(Orders order,String myLevel){
		//第一次购买本人折扣
	    SpdBean spdzk=new SpdBean();	   
	    spdzk.setFtype("1");
	    spdzk.setLv("1");
	    SpdBean zk =managerDao.findRateByLv(spdzk);
	    //查询一级推荐人
	    UserInfo recUser1 =managerDao.findRecUser1ByUserid(order.getUser_id());//2
	    String recUser1Name="null";
	    if (null!=recUser1) {
	    	recUser1Name=recUser1.getName();
		}
	    //查询二级推荐人
	    UserInfo recUser2 =managerDao.findRecUser2ByUserid(order.getUser_id());
	    String recUser2Name="null";
	    if (null!=recUser2) {
	    	recUser2Name=recUser2.getName();
		}
	    //查询省
	    UserInfo Province =managerDao.findProvinceByUserid(order.getUser_id());
	  String ProvinceName="null";
	    if (null!=Province) {
	    	ProvinceName=Province.getName();
	    	if("0".equals(Province.getUserid())){
	    		Province.setPCityname(Province.getPCityname()+"无");
	    	}
		}
	    //查询市
	    UserInfo City =managerDao.findCityByUserid(order.getUser_id());
	    String CityName="null";
	    if (null!=City) {
	    	CityName=City.getName();
	    	if("0".equals(City.getUserid())){
	    	City.setPCityname(City.getPCityname()+"无");
	    	}
		}
	    //查询区
	    UserInfo County =managerDao.findCountyByUserid(order.getUser_id());	    
	   String CountyName="null";
	    if (null!=County) {
	    	CountyName=County.getName();
	    	if("0".equals(County.getUserid())){
	    		County.setPCityname(County.getPCityname()+"无");
		    	}
		}
	    SpdBean fanli=new SpdBean();
	    //查询第一次购买一级推荐人的返利
	    fanli.setFtype("1");
	    fanli.setLv("2");
	    SpdBean Rebate1forRecUser1 =managerDao.findRateByLv(fanli);//0.25
	    //查询第一次购买二级推荐人的返利
	    fanli.setFtype("1");
	    fanli.setLv("3");
	    SpdBean Rebate1forRecUser2 =managerDao.findRateByLv(fanli);//0.1
	    //查询第一次购买省的返利
	    fanli.setFtype("1");
	    fanli.setLv("4");
	    SpdBean Rebate1forProvince =managerDao.findRateByLv(fanli);//0.05
	    //查询第一次购买市的返利
	    fanli.setFtype("1");
	    fanli.setLv("5");
	    SpdBean Rebate1forCity =managerDao.findRateByLv(fanli);//0.05
	    //查询第一次购买县的返利
	    fanli.setFtype("1");
	    fanli.setLv("6");
	    SpdBean Rebate1forCounty =managerDao.findRateByLv(fanli);//0.05
		 //返利
		 String RebaterecUser1=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser1.getRate()));
		 String RebaterecUser2=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forRecUser2.getRate()));
		 String RebateProvince=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forProvince.getRate()));
		 String RebateCity=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCity.getRate()));
		 String RebateCounty=String.valueOf(Double.parseDouble(order.getFje())*Double.parseDouble(Rebate1forCounty.getRate()));

		 String hh="一级推荐人（"+recUser1Name+"）:返利"+RebaterecUser1+
				  "<br>二级推荐人（"+recUser2Name+"）:返利"+RebaterecUser2+
				  "<br>"+Province.getPCityname()+"代理（"+ProvinceName+"）:返利"+RebateProvince+
				  "<br>"+City.getPCityname()+"代理（"+CityName+"）:返利"+RebateCity+
				  "<br>"+County.getPCityname()+"代理（"+CountyName+"）:返利"+RebateCounty;
		return hh;
				 
				          
	
		
	
	}
	/**
	 * 会员付款
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String updateCustmercost(Orders order) throws SQLClientInfoException {

		//1，将订单信息存入Order bean中
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		Date date=new Date();
		String time=df.format(date);// new Date()为获取当前系统时间
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		//订单流水号
		order.setFOrder_Id("X"+time+rannum);
	    //订单状态，已完成
	    order.setFstatus("5");
	    //发生日期
	    order.setFS_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
	    //支付方式，现金
	    order.setFpayWay("4");


		order.setFBillID(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
	    //查询会员等级
	    UserInfo userInfo =managerDao.findLevelByUserid(order.getUser_id());//0,0 U_Llevel,Fproxy 
	    int je=Integer.valueOf(order.getFje());//1000
	    int shengji=0;
	    if (!"".equals(order.getShengji())&&null!=order.getShengji()) {
			shengji=Integer.parseInt(order.getShengji());
		}
	    if (je==9800) {
	    	shengji(order,"1");
	    	
	    }else if (je==19800) {
	    	shengji(order,"2");
			
		}else{
			return "发生了某些错误";	
		}
	    order.setMark("升级转账");
	    order.setFSl("1");
	    //生成订单
	    managerDao.addOrder(order);	    
	    //生成订单明细
	    managerDao.addOrderItem(order);
	    //商品销售金额表T_Qk_Amount
		 managerDao.addAmount(order);	
	    //更新字号表
	    //managerDao.updateBillnumberByFClass("1003");
		return "成功";
	   
	   
	    
	  
	    
		
	}
	
	/**
	 * 补发货
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public String deliveryBymanSubmit(Orders order) throws SQLClientInfoException {
		
		//1，将订单信息存入Order bean中
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		Date date=new Date();
		String time=df.format(date);// new Date()为获取当前系统时间
		Random random = new Random();
		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;
		//订单流水号
		order.setFOrder_Id("X"+time+rannum);
		//订单状态，已完成
		order.setFstatus("5");
		order.setFW_Id(order.getDeliveryBean().getFW_ID());
/*	//发生日期.由页面上用户选择，而不是自己生成
		order.setFS_Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));*/
		//支付方式，电子币
		order.setFpayWay("1");
		 order.setMark("发货扣券");
		//Billnumber billnumber=managerDao.findBillNumberByFMax("1003");
		order.setFBillID(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32));
		//生成订单
		 order.setFSl("1");
		managerDao.addOrder(order);	    
		//生成订单明细
		managerDao.addOrderItem(order);
		//商品销售金额表T_Qk_Amount
		managerDao.addAmount(order);	

		DeliveryBean deliveryBean=order.getDeliveryBean();
		deliveryBean.setFBillID(order.getFBillID());
		managerDao.updateDeliver_Goods(deliveryBean);
		//更新字号表
		//managerDao.updateBillnumberByFClass("1003");
	    Rebate rebate=new Rebate();
	    rebate.setFIntegral("0");
	    rebate.setFRebate("0");
	    rebate.setFAmount("0");
	    rebate.setFGain("0");    
	    rebate.setFbeging_balance("0");
	    rebate.setFUser_Id(order.getUser_id());
	    rebate.setFe_vouche("-"+order.getFje());
	    managerDao.updateAccounting(rebate);
		return "成功";
	}
	//查询采购付款单列表
	public List<WithdrawBean> queryWithdrawList(String query) throws SQLClientInfoException {
		
		return managerDao.queryWithdrawList(query);
	}

	
	public boolean updateWithdrawStatus(WithdrawBean condition) throws SQLClientInfoException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date=new Date();
		String time=df.format(date);// new Date()为获取当前系统时间
	   condition.setFUpdatetime(time);
		managerDao.updateWithdrawStatus(condition);
		 if ("2".equals(condition.getFStatus())) {
			 BigDecimal Fblance = new BigDecimal(condition.getFJe());
		/*	 String FSxf=condition.getFSxf();
			 BigDecimal Fblance = new BigDecimal("0");
			 if ("".equals(FSxf)) {
				FSxf="0";
			}
			 BigDecimal b1 = new BigDecimal(condition.getFJe());
			 BigDecimal b2 = new BigDecimal(FSxf);
			
			 
			 if ( b1.compareTo(b2)>0) {
				 Fblance=b1.subtract(b2);
				
			}*/
			 Rebate rebate=new Rebate();	
			 rebate.setFIntegral("0");//积分
			 rebate.setFRebate("0");//返利
			 rebate.setFAmount("0");//销售额
			 rebate.setFGain("0");//利润
			 rebate.setFe_vouche("0");//电子币
			 rebate.setFbalance("-"+Fblance);//余额     //在rebate实体类新增余额字段
			 rebate.setFUser_Id(condition.getFUser_Id());
			 managerDao.updateAccounting2(rebate);
			 
		}
		return true;
	}
	public boolean ajaxUpdateRefuseReason(WithdrawBean condition) throws SQLClientInfoException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date=new Date();
		String time=df.format(date);// new Date()为获取当前系统时间
		condition.setFUpdatetime(time);
		managerDao.ajaxUpdateRefuseReason(condition);
		return true;
	}
	public void updateAccounting(Rebate info) throws SQLClientInfoException {
		managerDao.updateAccounting(info);
	
	}
	public void updateAccounting2(Rebate info) throws SQLClientInfoException {
		managerDao.updateAccounting2(info);
		
	}
	//查询采购付款单列表
	public Rebate  queryBalance(String query) throws SQLClientInfoException {
		
		return managerDao.queryBalance(query);
	}
}
