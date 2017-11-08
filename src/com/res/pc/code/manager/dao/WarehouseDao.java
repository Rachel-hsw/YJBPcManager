package com.res.pc.code.manager.dao;

import com.res.pc.code.manager.bean.WarehouseEntity;
import com.res.pc.code.manager.bean.WarehouseVo;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.SQLClientInfoException;
import java.util.List;

/**
 * 仓库管理Dao
 * @author mz
 * @date: 2017年10月24 上午10:48
 */
@Repository("warehouseDao")
public class WarehouseDao extends SqlMapClientDaoSupport {

    @Resource(name = "sqlMapClient")
    private SqlMapClientTemplate sqlMapClient;

    @PostConstruct
    public void initSqlMapClient() {
        super.setSqlMapClientTemplate(sqlMapClient);
    }

    /**
     * 查询所有仓库
     * @return
     * @throws SQLClientInfoException
     */
    public List<WarehouseEntity> queryAllWarehouse (WarehouseEntity warehouseEntity) throws SQLClientInfoException {
        return sqlMapClient.queryForList("warehouse.queryAllWarehouse", warehouseEntity);
    }

    /**
     * 查询仓库个数
     * @return
     * @throws SQLClientInfoException
     */
    public Integer queryWarehouseCount(WarehouseVo vo)throws SQLClientInfoException {
        return (Integer) sqlMapClient.queryForObject("warehouse.queryWarehouseCount", vo);
    }

    /**
     * 分页查询
     * @param vo
     * @return
     * @throws SQLClientInfoException
     */
    public List<WarehouseEntity> queryWarehouse(WarehouseVo vo) throws SQLClientInfoException{
        return sqlMapClient.queryForList("warehouse.queryWarehouse", vo);
    }


    /**
     * 更新仓库信息
     * @param warehouseEntity
     */
    public void updateWarehouse (WarehouseEntity warehouseEntity) throws SQLClientInfoException{
        sqlMapClient.update("warehouse.updateWarehouse",warehouseEntity);
    }

    /**
     * 删除仓库 0代表未删除，1代表已删除
     * @param warehouseEntity
     */
    public void deleteWarehouse (WarehouseEntity warehouseEntity) throws SQLClientInfoException{
        sqlMapClient.update("warehouse.deleteWarehouse",warehouseEntity);
    }

    /**
     * 新增仓库
     * @param warehouseEntity
     */
    public void addWarehouse (WarehouseEntity warehouseEntity) throws SQLClientInfoException{
        sqlMapClient.insert("warehouse.addWarehouse",warehouseEntity);
    }

    /**
     * 查询单个仓库信息
     * @param warehouseEntity
     * @return
     */
    public WarehouseEntity queryWarehouseById(WarehouseEntity warehouseEntity)throws SQLClientInfoException{
        return (WarehouseEntity) sqlMapClient.queryForObject("warehouse.queryWarehouseById", warehouseEntity);
    }
}
