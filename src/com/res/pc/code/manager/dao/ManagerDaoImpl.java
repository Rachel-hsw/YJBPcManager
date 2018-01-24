/**
 * 
 */
package com.res.pc.code.manager.dao;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

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
import com.res.pc.code.vo.BeginingVo;
import com.res.pc.code.vo.BeginningCondition;
import com.res.pc.code.vo.NumberCondition;
import com.res.pc.code.vo.QueryNumberVo;
import com.res.pc.util.PageInfo;

/**
 * 管理台登录
 * 
 * @author liuqiang,rlw
 */
@Repository("managerDao")
public class ManagerDaoImpl extends SqlMapClientDaoSupport {
	@Resource(name = "sqlMapClient")
	private SqlMapClientTemplate sqlMapClient;

	@PostConstruct
	public void initSqlMapClient() {
		super.setSqlMapClientTemplate(sqlMapClient);
	}

	/**
	 * 登录
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo login(UserInfo info) throws SQLClientInfoException {
		return (UserInfo) sqlMapClient.queryForObject("manager.login", info);
	}

	/**
	 * 查询用户列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> queryUserList(PageInfo info) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryUserList", info);
	}

	/**
	 * 查询用户列表总条数
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryUserListCount(PageInfo info) throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryUserListCount", info);
	}

	public List<configInfo> queryconfigList(PageInfo info) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryconfigList", info);
	}

	public Integer queryconfigListCount() throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryconfigListCount");
	}

	/**
	 * 查询角色列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	/*
	 * @SuppressWarnings("unchecked") public List<UserInfo>
	 * queryRoleList(PageInfo info)throws SQLClientInfoException { return
	 * sqlMapClient.queryForList("manager.queryUserList", info); }
	 * 
	 * 
	 * /** 查询角色列表总条数
	 * 
	 * @return
	 * 
	 * @throws SQLClientInfoException
	 */
	/*
	 * public Integer queryRoleListCount() throws SQLClientInfoException {
	 * return (Integer)
	 * sqlMapClient.queryForObject("manager.queryUserListCount"); }
	 * 
	 * 
	 * /** 添加用户
	 * 
	 * @param info
	 * 
	 * @throws SQLClientInfoException
	 */
	public void addUserInfo(UserInfo info) throws SQLClientInfoException {
		sqlMapClient.insert("manager.addUserInfo", info);
	}

	/**
	 * 修改用户
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateUserInfo(UserInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateUserInfo", info);
	}

	/**
	 * 删除用户
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteUserInfo(UserInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.deleteUserInfo", info);
	}

	/**
	 * 查询用户名是否存在
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryUserAccount(UserInfo info) throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryUserAccount", info);
	}

	/**
	 * 修改密码
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePW(UserInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatePW", info);
	}

	public void updateconfig(configInfo config) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateconfig", config);
	}

	/**
	 * 查询用户详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public UserInfo queryUserInfo(UserInfo info) throws SQLClientInfoException {
		return (UserInfo) sqlMapClient.queryForObject("manager.queryUserInfo", info);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void upUserInfo(UserInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.upUserInfo", info);
	}

	/**
	 * 查询菜单列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<InitialNavigationsInfo> queryNIList(PageInfo info) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryINList", info);
	}

	/**
	 * 查询菜单列表总条数
	 * 
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryNICount() throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryINCount");
	}

	/**
	 * 添加菜单
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void addNIInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		sqlMapClient.insert("manager.addINInfo", info);
	}

	/**
	 * 修改菜单
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateNIInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateINInfo", info);
	}

	/**
	 * 删除菜单
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteNIInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.delINInfo", info);
	}

	/**
	 * 查询菜单是否存在
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryINAccount(InitialNavigationsInfo info) throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryINAccount", info);
	}

	/**
	 * 查询菜单详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public InitialNavigationsInfo queryINInfo(InitialNavigationsInfo info) throws SQLClientInfoException {
		return (InitialNavigationsInfo) sqlMapClient.queryForObject("manager.queryINinfo", info);
	}

	/**
	 * 查询角色列表
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<RoleInfo> queryRoleList(PageInfo info) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryRoleList", info);
	}

	/**
	 * 查询角色列表总条数
	 * 
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryRoleCount() throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryRoleCount");
	}

	/**
	 * 添加角色
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void addRoleInfo(RoleInfo info) throws SQLClientInfoException {
		sqlMapClient.insert("manager.addRoleInfo", info);
	}

	/**
	 * 修改角色
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateRoleInfo(RoleInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateRoleInfo", info);
	}

	/**
	 * 删除角色
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void deleteRoleInfo(RoleInfo info) throws SQLClientInfoException {
		sqlMapClient.update("manager.delRoleInfo", info);
	}

	/**
	 * 查询角色是否存在
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public Integer queryRoleAccount(RoleInfo info) throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.queryRoleAccount", info);
	}

	/**
	 * 查询角色详情
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	public RoleInfo queryIRolenfo(RoleInfo info) throws SQLClientInfoException {
		return (RoleInfo) sqlMapClient.queryForObject("manager.queryRoleinfo", info);
	}

	/**
	 * IDS查询菜单
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<InitialNavigationsInfo> queryNIByIds(String ids) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryINByIds", ids);
	}

	/**
	 * IDS查询用户
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<UserInfo> queryUsersByIds(String ids) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryUsersByIds", ids);
	}

	/**
	 * 
	 * 根据用户查询角色
	 * 
	 * @param info
	 * @return
	 * @throws SQLClientInfoException
	 */
	@SuppressWarnings("unchecked")
	public List<RoleInfo> queryRoleByUserId(String userId) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryRolesByUserId", userId);
	}

	@SuppressWarnings("unchecked")
	public List<InitialNavigationsInfo> queryAllIN() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllIN");
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> queryAllUser() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllUser");
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> queryAllUser2() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllUser2");
	}

	public void insertLog(OperationLog log) throws SQLClientInfoException {
		sqlMapClient.insert("manager.addlog", log);

	}

	/**
	 * 修改折扣信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateZk(PriceBean zkbean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateZk", zkbean);
	}

	// 获取折扣列表信息
	@SuppressWarnings("unchecked")
	public List<PriceBean> queryAllZk() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllZk");
	}

	// 获取返利列表信息
	@SuppressWarnings("unchecked")
	public List<SpdBean> queryRebateByType() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryRebateByType");
	}

	/**
	 * 修改返利信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateRebateByTypeAndLv(SpdBean rewardbean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateRebateByTypeAndLv", rewardbean);
	}

	/**
	 * 修改折扣信息
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void addPurchase(PurchaseBean purchaseBean) throws SQLClientInfoException {
		sqlMapClient.insert("manager.addPurchase", purchaseBean);
	}

	// 获取折扣列表信息
	@SuppressWarnings("unchecked")
	public List<PurchaseBean> queryPurchase() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryPurchase");
	}

	// 获取仓库列表信息
	@SuppressWarnings("unchecked")
	public List<WarehouseBean> queryAllWarehouse() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllWarehouse");
	}

	// 获取用户列表信息
	@SuppressWarnings("unchecked")
	public List<UserInfo> queryAllUserB(String userlb) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllUserB",userlb);
	}

	// 获取驾驶员列表信息
	@SuppressWarnings("unchecked")
	public List<DriverBean> queryAllDriver() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllDriver");
	}

	// 获取进货单位列表信息
	@SuppressWarnings("unchecked")
	public List<SupplierBean> queryAllSupplier() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllSupplier");
	}


	// 获取商品列表信息
	@SuppressWarnings("unchecked")
	public List<Product> queryAllProduct() throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllProduct");
	}

	// 提交采货单明细
	public void updatepurchasing(PurItemBean purItemBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatepurchasing", purItemBean);
	}

	// 提交采货单主
	public void updatepurchasingM(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatepurchasingM", condition);
	}
	
	@SuppressWarnings("unchecked")
	public List<MyCondition>  findFSourceId(MyCondition condition) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.findFSourceId", condition);
	}

	// 提交退货单明细
	public void updatereturn(PurItemBean purItemBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatereturn", purItemBean);
	}

	// 提交退货单主
	public void updatereturnM(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatereturnM", condition);
	}

	// 更新采购金额表
	public void updateAmount(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateAmount", condition);
	}

	// 更新供应商表
	public void updateSupplier(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateSupplier", condition);
	}

	// 更新供应商资金变动表
	public void updateCfg(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateCfg", condition);
	}

	// 单号表
	public void updateBillnumber(MyCondition condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateBillnumber", condition);
	}

	// 库存
	public void updateStock(PurItemBean purItemBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateStock", purItemBean);
	}

	// 代发货
	// 查询条件查询订单
	@SuppressWarnings("unchecked")
	public List<Orders> queryAllOrdersList(String query) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllOrdersList",query);
	}

/*	// 查询条件查询订单
	@SuppressWarnings("unchecked")
	public List<Orders> queryAllOrdersLists(String query) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllOrdersLists", query);
	}*/

	// 查询指定待发货订单
	@SuppressWarnings("unchecked")
	public List<Orders> queryAllOrdersListss(String FBillID) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllOrdersListss", FBillID);
	}

	// 获取进货单位列表信息
	@SuppressWarnings("unchecked")
	public List<view_orderdetail> queryAllOrderDetail(String  FBillID) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryAllOrderDetail", FBillID);
	}
	// 获取进货单位列表信息
	@SuppressWarnings("unchecked")
	public List<view_orderdetail> queryPurchaseDetail(int FBillID) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryPurchaseDetail", FBillID);
	}

	// 提交发货单
	public void updateDeliver_Goods(DeliveryBean deliveryBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateDeliver_Goods", deliveryBean);
	}
	// 提交发货单
	public void updateOrders_Status(String FBillID) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateOrders_Status", FBillID);
	}
	// 提交发货单
	public void updatePurchase_Status(int FBillID) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatePurchase_Status", FBillID);
	}
	// 提交发货单
	public void updateAmountPay(int FBillID) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateAmountPay", FBillID);
	}
	// 提交发货单
	public void updateSupplier1(PayMainBean payMainBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateSupplier1", payMainBean);
	}
	// 库存
	public void updateStock2(PurItemBean purItemBean) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateStock2", purItemBean);
	}
	// 获取采购列表
	@SuppressWarnings("unchecked")
	public List<PurchasingBean> queryPurchasing( String FId) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryPurchasing", FId);
	}
	// 获取指定仓库下的商品列表
	@SuppressWarnings("unchecked")
	public List<Product> queryInventory( String FId) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryInventory", FId);
	}
	// //获取目前数据库中的最大派单号
	@SuppressWarnings("unchecked")
	public List<PDBean> queryPDBean( String FId) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryPDBean", FId);
	}
	
	/**
	 * 查询进货单位
	 * @param vo
	 * @return
	 */
	public Integer querySupplierListCount(QuerySupplierVo vo) throws SQLClientInfoException {
		return (Integer) sqlMapClient.queryForObject("manager.selectSupplierListCount",vo);
	}

	/**
	 * 查询进货单位个数
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SupplierBean> querySupplierList(QuerySupplierVo vo) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.selectSupplierAll",vo);
	}

	public SupplierBean querySupplierInfo(SupplierBean reqInfo)throws SQLClientInfoException {
		return (SupplierBean) sqlMapClient.queryForObject("manager.querySupplierInfo",reqInfo);
	}

	public void deleteSupplierInfo(SupplierBean supplierBean) throws SQLClientInfoException{
		sqlMapClient.update("manager.deleteSupplierInfo", supplierBean);
		
	}

	public void updateSupplierInfo(SupplierBean supplierInfo) throws SQLClientInfoException{
		sqlMapClient.update("manager.updateSupplierInfo", supplierInfo);
		
	}

	public void addSupplierInfo(SupplierBean supplierInfo) throws SQLClientInfoException {
		sqlMapClient.update("manager.addSupplierInfo", supplierInfo);
		
	}
	@SuppressWarnings("unchecked")
	public List<Product> findAllProd(QueryProdVo vo) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		return sqlMapClient.queryForList("manager.findAllProd",vo);
	}
	public Product queryProductInfo(String prod_id)throws SQLClientInfoException {
		return (Product) sqlMapClient.queryForObject("manager.queryProdInfo",prod_id);
	}
	
	public void deleteProductInfo(Product product) throws SQLClientInfoException{
		sqlMapClient.update("manager.deleteProdInfo", product);
		
	}
	public void updateProductInfo(Product product) throws SQLClientInfoException{
		sqlMapClient.update("manager.updateProdInfo", product);
		
	}
	public void updateT_Qk_FK(PayMainBean payMainBean) throws SQLClientInfoException{
		sqlMapClient.update("manager.updateT_Qk_FK", payMainBean);
		
	}

	public void addProductInfo(Product product) throws SQLClientInfoException {
		sqlMapClient.update("manager.addProdInfo", product);
		
	}
	//更新盘点明细表
	public void updatePDItem(PDItemBean PDItemBean) throws SQLClientInfoException{
		sqlMapClient.update("manager.updatePDItem", PDItemBean);
		
	}
	//更新盘点主表
	public void updatePD(PDBean PDBean) throws SQLClientInfoException{
		sqlMapClient.update("manager.updatePD", PDBean);
		
	}

	/**
	 *  query Evaluate by condition 根据条件查询评价
	 */
	public List<EvaluateBean> queryEvaluateListByCond(PageInfo info) {
		return sqlMapClient.queryForList("manager.queryEvaluateByCond", info);
	}


	/**
	 *  query Evaluate count by condition 根据条件查询评价总数
	 */
	public Integer queryEvaluateCountByCond(PageInfo info) {
		return (Integer) sqlMapClient.queryForObject("manager.queryCountInEvaluateByCond", info);
	}
	
	/**查询套餐列表
	 *  
	 */
	@SuppressWarnings("unchecked")
	public List<PackageBean> queryPackageList() {
		return sqlMapClient.queryForList("manager.queryPackageList");
	}
	
	/**查询套餐列表
	 *  
	 */
	@SuppressWarnings("unchecked")
	public String queryMaxPackage_id() {
		return (String) sqlMapClient.queryForObject("manager.queryMaxPackage_id");
	}
	/**
	 *  根据套餐id查询套餐明细
	 */
	@SuppressWarnings("unchecked")
	public List<PackageItem> queryPackageDetail(String id) {
		return sqlMapClient.queryForList("manager.queryPackageDetail",id);
	}
	/**
	 *  根据套餐id查询套餐明细
	 */
	@SuppressWarnings("unchecked")
	public List<Product> queryProductList() {
		return sqlMapClient.queryForList("manager.queryProductList");
	}


	/**
	 * 修改用户
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePackageItem(PackageItem info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatePackageItem", info);
	}

	/**
	 * 修改用户
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updatePackage(PackageBean info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updatePackage", info);
	}
	/**
	 * 一般费用提交
	 * 
	 * @param info
	 * @throws SQLClientInfoException
	 */
	public void updateExpenseOrEarn(ExpenseBean condition) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateExpenseOrEarn", condition);
	}

	// 获取商品列表信息
	@SuppressWarnings("unchecked")
	public List<PackageItem> queryProductbyId(String  P_Package_id) throws SQLClientInfoException {
		return sqlMapClient.queryForList("manager.queryProductbyId",P_Package_id);
	}
	/**查询套餐列表
	 *  
	 */
	@SuppressWarnings("unchecked")
	public List<PackageBean> queryPackagebyId(String  P_Package_id) {
		return sqlMapClient.queryForList("manager.queryPackagebyId",P_Package_id);
	}
	public void delItemById(String  P_Package_id) throws SQLClientInfoException {
		sqlMapClient.update("manager.delItemById",P_Package_id);
	}
	public void delPackageById(String  P_Package_id) throws SQLClientInfoException{
		 sqlMapClient.update("manager.delPackageById",P_Package_id);
	}
	
	/**
	 *  查询采购单列表
	 */
	@SuppressWarnings("unchecked")
	public List<PurchasingBean> queryPurchaseList(String query) {
		return sqlMapClient.queryForList("manager.queryPurchaseList",query);
	}
	/**
	 *  查询采购付款单列表
	 */
	@SuppressWarnings("unchecked")
	public List<PayMainBean> queryPayList(String query) {
		return sqlMapClient.queryForList("manager.queryPayList",query);
	}
	/**
	 *  查询采购付款单列表
	 */
	@SuppressWarnings("unchecked")
	public List<PayMainBean> queryPayListByID(String FId) {
		return sqlMapClient.queryForList("manager.queryPayListByID",FId);
	}
	/**
	 *  根据采购单id查询采购单
	 */
	@SuppressWarnings("unchecked")
	public List<PurchasingBean> queryPurchaseListByID(String FBillID) {
		return sqlMapClient.queryForList("manager.queryPurchaseListByID",FBillID);
	}
	/**
	 *  根据采购单id查询采购单
	 */
	@SuppressWarnings("unchecked")
	public List<PurItemBean> purchaseItemDetail(String FBillID) {
		return sqlMapClient.queryForList("manager.purchaseItemDetail",FBillID);
	}
	
	@SuppressWarnings("unchecked")
	public void updateBillnumberByFClass(String FBillID) {
		sqlMapClient.update("manager.updateBillnumberByFClass",FBillID);
	}
	@SuppressWarnings("unchecked")
	public void addOrder(Orders order) {
		sqlMapClient.update("manager.addOrder",order);
	}
	@SuppressWarnings("unchecked")
	public void addAmount(Orders order) {
		sqlMapClient.update("manager.addAmount",order);
	}
	@SuppressWarnings("unchecked")
	public void addRebate(Rebate order) {
		sqlMapClient.update("manager.addRebate",order);
	}
	@SuppressWarnings("unchecked")
	public void updateAccounting(Rebate order) {
		sqlMapClient.update("manager.updateAccounting",order);
	}
	@SuppressWarnings("unchecked")
	public void updateLevelbyUserID(UserInfo userinfo) {
		sqlMapClient.update("manager.updateLevelbyUserID",userinfo);
	}
	@SuppressWarnings("unchecked")
	public void addOrderItem(Orders order) {
		sqlMapClient.update("manager.addOrderItem",order);
	}
	
	@SuppressWarnings("unchecked")
	public SpdBean findRateByLv(SpdBean FBillID) {
		return (SpdBean) sqlMapClient.queryForObject("manager.findRateByLv",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findLevelByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findLevelByUserid",FBillID);
	}
	@SuppressWarnings("unchecked")
	public Rebate queryIfEnough(String FBillID) {
		return  (Rebate) sqlMapClient.queryForObject("manager.queryIfEnough",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo queryUserById(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.queryUserById",FBillID);
	}
	@SuppressWarnings("unchecked")
	public Billnumber findBillNumberByFMax(String FClass) {
		return  (Billnumber) sqlMapClient.queryForObject("manager.findBillNumberByFMax",FClass);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findRecUser1ByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findRecUser1ByUserid",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findRecUser2ByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findRecUser2ByUserid",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findProvinceByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findProvinceByUserid",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findCityByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findCityByUserid",FBillID);
	}
	@SuppressWarnings("unchecked")
	public UserInfo findCountyByUserid(String FBillID) {
		return  (UserInfo) sqlMapClient.queryForObject("manager.findCountyByUserid",FBillID);
	}
	/**
	 *  根据采购单id查询采购单
	 */
	@SuppressWarnings("unchecked")
	public List<WithdrawBean> queryWithdrawList(String query) {
		return sqlMapClient.queryForList("manager.queryWithdrawList",query);
	}
	public void updateWithdrawStatus(WithdrawBean info) throws SQLClientInfoException {
		sqlMapClient.update("manager.updateWithdrawStatus", info);
	}
	public void ajaxUpdateRefuseReason(WithdrawBean info) throws SQLClientInfoException {
		sqlMapClient.update("manager.ajaxUpdateRefuseReason", info);
	}
	@SuppressWarnings("unchecked")
	public void updateAccounting2(Rebate order) {
		sqlMapClient.update("manager.updateAccounting2",order);
	}
	@SuppressWarnings("unchecked")
	public Rebate queryBalance(String FBillID) {
		return  (Rebate) sqlMapClient.queryForObject("manager.queryBalance",FBillID);
	}

} 


