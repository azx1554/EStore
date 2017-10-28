package com.yang.background.common.persistence.interceptor;


import com.yang.background.common.persistence.Pages;
import com.yang.background.common.persistence.dialect.Dialect;
import com.yang.background.common.persistence.dialect.db.*;
import com.yang.background.common.persistence.utils.Reflections;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import java.io.Serializable;
import java.util.Properties;

public abstract class AbstractInterceptor implements Interceptor, Serializable {
	private static final long serialVersionUID = 7601006451417393141L;
	protected static final String PAGE = "pages";
	protected static final String DELEGATE = "delegate";
	protected static final String MAPPED_STATEMENT = "mappedStatement";
	protected Log log = LogFactory.getLog(this.getClass());
	protected Dialect DIALECT;

	@SuppressWarnings("unchecked")
	protected static Pages<Object> convertParameter(Object parameterObject, Pages<Object> page) {
		try {
			if (parameterObject instanceof Pages) {
				return (Pages<Object>) parameterObject;
			} else {
				return (Pages<Object>) Reflections.getFieldValue(parameterObject, PAGE);
			}
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 设置属性，支持自定义方言类和制定数据库的方式
	 * <code>dialectClass</code>,自定义方言类。可以不配置这项
	 * <ode>dbms</ode> 数据库类型，插件支持的数据库
	 * <code>sqlPattern</code> 需要拦截的SQL ID
	 *
	 * @param p 属性
	 */
	protected void initProperties(Properties p) {
		Dialect dialect = null;
		String dbType = "mysql";//这里
		if ("db2".equals(dbType)) {
			dialect = new DB2Dialect();
		} else if ("derby".equals(dbType)) {
			dialect = new DerbyDialect();
		} else if ("h2".equals(dbType)) {
			dialect = new H2Dialect();
		} else if ("hsql".equals(dbType)) {
			dialect = new HSQLDialect();
		} else if ("mysql".equals(dbType)) {
			dialect = new MySQLDialect();
		} else if ("oracle".equals(dbType)) {
			dialect = new OracleDialect();
		} else if ("postgre".equals(dbType)) {
			dialect = new PostgreSQLDialect();
		} else if ("mssql".equals(dbType) || "sqlserver".equals(dbType)) {
			dialect = new SQLServer2005Dialect();
		} else if ("sybase".equals(dbType)) {
			dialect = new SybaseDialect();
		}
		if (dialect == null) {
			throw new RuntimeException("mybatis dialect error.");
		}
		DIALECT = dialect;
	}
}
