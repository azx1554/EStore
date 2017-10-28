import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCTest {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://139.199.192.109:3306/estore";
	public static final String USER = "root";
	public static final String PASSWORD = "mariko2008";
	public static Map<String, String> map = new HashMap<String, String>();
	static {
		map.put("java.lang.Long", "Long");
		map.put("java.lang.Double", "Double");
		map.put("java.math.BigDecimal", "BigDecimal");
		map.put("java.lang.String", "String");
		map.put("java.lang.Integer", "Integer");
		map.put("java.sql.Timestamp", "Date");
		map.put("java.sql.Date", "Date");
		map.put("java.lang.Boolean", "Boolean");
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getClassString("select * from e_sys_user", "sys");
		getMybaitsXml("select * from e_sys_user", "sys");
	}

	public static String getMybaitsXml(String sql, String packageName) throws ClassNotFoundException, SQLException {
		String className = "I" + getClassName(sql) + "DAO";
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >");
		sb.append("\n<mapper namespace=\"com.mg.background." + packageName + ".dao." + className + "\">\n");
		Map<String, Object> meta = getMeta(getMetaData(sql));
		sb.append(getSelectSql(meta, sql));
		sb.append(getConditionSql(meta, sql));
		sb.append(getFindListSql(meta, sql));
		sb.append(getInsertSql(meta, sql));
		sb.append(getUpdateSql(meta, sql));
		sb.append(getDeteleSql(meta, sql));
		sb.append("\n</mapper>");
		System.out.println(sb);
		return sb.toString();
	}

	public static String getClassName(String sql) {
		String[] arr = sql.split(" ");
		String tableName = arr[arr.length - 1].replaceFirst("e_", "");
		String[] tableArr = tableName.split("_");
		String className = "";
		for (String str : tableArr) {
			className += str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		return className;
	}

	public static String getTableName(String sql) {
		String[] arr = sql.split(" ");
		String tableName = arr[arr.length - 1];
		return tableName;
	}

	public static String getClassString(String sql, String packageName) throws ClassNotFoundException, SQLException {
		String className = getClassName(sql);
		StringBuffer sb = new StringBuffer();
		sb.append("package com.mg.background." + packageName + ".entity;" + "\n");
		sb.append("public class " + className + " extends BaseDataEntity<" + className + "> {\n");
		ResultSetMetaData metaData = getMetaData(sql);//拿到表中每一列的名称和类型信息
		List<String> list = new ArrayList<String>();
		Map<String, String> fieldTypeMap = new HashMap<String, String>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			String columnName = metaData.getColumnName(i);
			String[] columnNameArr = columnName.split("_");
			String fieldName = "";
			for (String string : columnNameArr) {
				fieldName += string.substring(0, 1).toUpperCase() + string.substring(1);
			}
			String realField = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
			list.add(realField);
			String columnClassName = metaData.getColumnClassName(i);//得到第i列数据类型对应的类
			fieldTypeMap.put(realField, map.get(columnClassName));
		}
		list.remove("id");
		list.remove("updateBy");
		list.remove("createBy");
		list.remove("updateTime");
		list.remove("createTime");
		for (String field : list) {
			sb.append("private ");
			sb.append(fieldTypeMap.get(field) + " ");
			sb.append(field + ";\n");
		}
		for (String field : list) {
			sb.append("public ");
			sb.append(fieldTypeMap.get(field) + " ");
			sb.append("get" + field.substring(0, 1).toUpperCase() + field.substring(1) + "(){" + "\n");
			sb.append("return " + field + ";\n");
			sb.append("}\n\n");
			sb.append("public void ");
			sb.append("set" + field.substring(0, 1).toUpperCase() + field.substring(1) + "(" + fieldTypeMap.get(field) + " " + field + "){" + "\n");
			sb.append("this." + field + "=" + field + ";\n");
			sb.append("}\n\n");
		}
		list.remove("limitStart");
		list.remove("limitSize");
		sb.append("@Override\n");
		sb.append("public String toString() { return \"{");
		int index = 0;
		if (true) {
			sb.append("\"+super.toString()");
			index++;
		}
		for (String field : list) {
			if (index == 0) {
				sb.append(field + ":\"+" + field);
				index++;
			} else {
				sb.append("+\"," + field + ":\"+" + field);
			}
		}
		sb.append("+\"}\";\n}\n");
		sb.append("}");
		System.out.println(sb);
		return sb.toString();
	}

	public static ResultSetMetaData getMetaData(String sql) throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		ResultSetMetaData metaData = resultSet.getMetaData();
		return metaData;
	}

	public static Map<String, Object> getMeta(ResultSetMetaData metaData) throws SQLException {
		List<String> list = new ArrayList<String>();
		Map<String, String> fieldTypeMap = new HashMap<String, String>();
		Map<String, String> typeMap = new HashMap<String, String>();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			String columnName = metaData.getColumnName(i);
			String[] columnNameArr = columnName.split("_");
			String fieldName = "";
			for (String string : columnNameArr) {
				fieldName += string.substring(0, 1).toUpperCase() + string.substring(1);
			}
			String realField = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);
			fieldTypeMap.put(realField, columnName);
			String columnClassName = metaData.getColumnClassName(i);
			typeMap.put(realField, map.get(columnClassName));
			list.add(realField);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("map", fieldTypeMap);
		map.put("type", typeMap);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static String getSelectSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		List<String> list = (List<String>) map.get("list");
		Map<String, String> fieldMap = (Map<String, String>) map.get("map");
		String table = getTableName(sql);
		sb.append("    <sql id=\"select_column_sql\">\n");
		sb.append("        select ");
		int index = 0;
		int size = list.size();
		for (String field : list) {
			index++;
			sb.append("\n        " + fieldMap.get(field) + " as " + field);
			if (index != size) {
				sb.append(",");
			}
		}
		sb.append("\n");
		sb.append("        from " + table + "\n");
		sb.append("    </sql>\n\n");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getConditionSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		List<String> list = (List<String>) map.get("list");
		Map<String, String> fieldMap = (Map<String, String>) map.get("map");
		Map<String, String> typeMap = (Map<String, String>) map.get("type");
		sb.append("    <sql id=\"condition_sql\">\n");
		sb.append("        <where> ");
		for (String field : list) {
			String type = typeMap.get(field);
			sb.append("\n            <if test = \"" + field + " != null");
			if (type.equals("String")) {
				sb.append(" and " + field + " != ''");
			}
			sb.append("\">");
			sb.append("\n              and " + fieldMap.get(field) + " = #{" + field + "}");
			sb.append("\n            </if>");
		}
		sb.append("\n    </where>");
		sb.append("\n    </sql>");
		sb.append("\n\n");
		return sb.toString();
	}

	public static String getFindListSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		String className = getClassName(sql);
		String table = getTableName(sql);
		sb.append("    <select id=\"findList\" parameterType=\"" + className + "\" resultType=\"" + className + "\">");
		sb.append("\n        <include refid=\"select_column_sql\" />");
		sb.append("\n        <include refid=\"condition_sql\" />");
		sb.append("\n        order by id desc");
		sb.append("\n    </select>");
		sb.append("\n\n");
		sb.append("    <select id=\"find\" parameterType=\"java.lang.Long\" resultType=\"" + className + "\">");
		sb.append("\n        <include refid=\"select_column_sql\" />");
		sb.append("\n        where id = #{0}");
		sb.append("\n    </select>");
		sb.append("\n\n");
		sb.append("    <select id=\"findSelective\" parameterType=\"" + className + "\" resultType=\"" + className + "\">");
		sb.append("\n        <include refid=\"select_column_sql\" />");
		sb.append("\n        <include refid=\"condition_sql\" />");
		sb.append("\n    </select>");
		sb.append("\n\n");
		sb.append("    <select id=\"getCount\" parameterType=\"" + className + "\" resultType=\"java.lang.Integer\">");
		sb.append("\n        select count(id) from " + table);
		sb.append("\n        <include refid=\"condition_sql\" />");
		sb.append("\n    </select>");
		sb.append("\n\n");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getInsertSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		String className = getClassName(sql);
		String table = getTableName(sql);
		List<String> list = (List<String>) map.get("list");
		Map<String, String> fieldMap = (Map<String, String>) map.get("map");
		Map<String, String> typeMap = (Map<String, String>) map.get("type");
		int index = 1;
		int size = list.size();
		list.remove("id");
		sb.append("    <insert id=\"insert\" parameterType=\"" + className + "\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n");
		sb.append("        insert into " + table + "(");
		for (String field : list) {
			index++;
			sb.append("\n            " + fieldMap.get(field));
			if (index != size) {
				sb.append(",");
			}
		}
		sb.append("\n        )values(");
		index = 1;
		for (String field : list) {
			index++;
			sb.append("\n            #{" + field + "}");
			if (index != size) {
				sb.append(",");
			}
		}
		sb.append("\n        )");
		sb.append("\n    </insert>");
		sb.append("\n\n");
		sb.append("    <insert id=\"insertSelective\" parameterType=\"" + className + "\" useGeneratedKeys=\"true\" keyProperty=\"id\">\n");
		sb.append("        insert into " + table);
		sb.append("\n	   <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
		for (String field : list) {
			String type = typeMap.get(field);
			sb.append("\n            <if test = \"" + field + " != null");
			if (type.equals("String")) {
				sb.append(" and " + field + " != ''");
			}
			sb.append("\">");
			sb.append("\n                " + fieldMap.get(field) + ",");
			sb.append("\n            </if>");
		}
		sb.append("\n        </trim>");
		sb.append("\n        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
		for (String field : list) {
			String type = typeMap.get(field);
			sb.append("\n            <if test = \"" + field + " != null");
			if (type.equals("String")) {
				sb.append(" and " + field + " != ''");
			}
			sb.append("\">");
			sb.append("\n            	#{" + field + "},");
			sb.append("\n            </if>");
		}
		sb.append("\n        </trim>");
		sb.append("\n    </insert>");
		sb.append("\n\n");
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static String getUpdateSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		String className = getClassName(sql);
		String table = getTableName(sql);
		List<String> list = (List<String>) map.get("list");
		Map<String, String> fieldMap = (Map<String, String>) map.get("map");
		Map<String, String> typeMap = (Map<String, String>) map.get("type");
		list.remove("id");
		sb.append("    <update id=\"update\" parameterType=\"" + className + "\">");
		sb.append("\n        update " + table);
		sb.append("\n        <set>");
		for (String field : list) {
			sb.append("\n            " + fieldMap.get(field) + " = " + "#{" + field + "},");
		}
		sb.append("\n        </set>");
		sb.append("\n        where id = #{id}");
		sb.append("\n    </update>");
		sb.append("\n\n");
		sb.append("    <update id=\"updateSelective\" parameterType=\"" + className + "\">");
		sb.append("\n        update " + table);
		sb.append("\n        <set>");
		for (String field : list) {
			String type = typeMap.get(field);
			sb.append("\n            <if test = \"" + field + " != null");
			if (type.equals("String")) {
				sb.append(" and " + field + " != ''");
			}
			sb.append("\">");
			sb.append("\n                " + fieldMap.get(field) + " = " + "#{" + field + "},");
			sb.append("\n            </if>");
		}
		sb.append("\n        </set>");
		sb.append("\n        where id = #{id}");
		sb.append("\n    </update>");
		sb.append("\n\n");
		return sb.toString();
	}

	public static String getDeteleSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		String table = getTableName(sql);
		String className = getClassName(sql);
		sb.append("    <delete id=\"delete\" parameterType=\"java.lang.Long\">");
		sb.append("\n        delete from " + table + " where id = #{0}");
		sb.append("\n    </delete>");
		sb.append("\n\n");
		sb.append("    <delete id=\"deleteSelective\" parameterType=\"" + className + "\">");
		sb.append("\n        delete from " + table);
		sb.append("\n        <include refid=\"condition_sql\" />");
		sb.append("\n    </delete>");
		return sb.toString();
	}
}
