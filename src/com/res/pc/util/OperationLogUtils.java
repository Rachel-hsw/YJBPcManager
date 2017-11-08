package com.res.pc.util;

import java.sql.SQLClientInfoException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.res.pc.code.manager.ManagerConntroller;
import com.res.pc.code.manager.bean.OperationLog;
import com.res.pc.code.manager.bean.UserInfo;
import com.res.pc.code.manager.dao.ManagerDaoImpl;
@Service("OperationLogUtils")
public class OperationLogUtils {
	/**
	 * 错误日志
	 */
	private static final Logger logger = Logger.getLogger(OperationLogUtils.class);
	/**
	 * 引入Dao
	 */
	@Resource(name = "managerDao")
	private   ManagerDaoImpl managerDao;
	
	public    void log(HttpServletRequest request, String log){
		OperationLog operationLog=new OperationLog();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		operationLog.setTime(df.format(new Date()));
		UserInfo userInfo=(UserInfo) request.getSession().getAttribute("managerUser");
		operationLog.setUser_id(String.valueOf(userInfo.getUserid()));
		operationLog.setLog(userInfo.getUsername()+log);
		try{
		managerDao.insertLog(operationLog);
		} catch (SQLClientInfoException e)
			{
				logger.error("数据库异常",e);
			}
	}
}
