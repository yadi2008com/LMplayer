package com.nuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.nuc.util.DBUtil;
/**
 * 
 * @author yadi
 *
 */
public class BaseDao {
	private Connection connection =null;
	private PreparedStatement preparedStatement=null;
	private ResultSet resultSet=null;
	/**
	 * 
	 * @param sql
	 * @param params 参数第一个参数传主键
	 * @return 操作结果 返回新增数据的主键
	 * @throws Exception 
	 */
	public Object  insert(String sql,Object[] params) throws Exception{
		connection = DBUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		if(null!=params && params.length>0){
			for (int i = 1; i <= params.length; i++) {
				preparedStatement.setObject(i, params[i-1]);
			}
		}
		int result =preparedStatement.executeUpdate();
		resultSet = preparedStatement.getGeneratedKeys();
		Object pkid=0;
		if (resultSet.next()) { 
			pkid = resultSet.getObject(1); 
		} 
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(result<1){
			return 0;
		}
		return 	pkid;
	}
	/**
	 * 
	 * @param sql 
	 * @param id 根据主键删除
	 * @return 操作结果，返回数据对应的id
	 * @throws Exception
	 */
	public int delete(String sql,Object id)throws Exception{
		connection = DBUtil.getConnection();
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setObject(1,id);
		int result =preparedStatement.executeUpdate();
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(result<1){
			return 0;
		}
		return Integer.valueOf(String.valueOf(id));
	}
	
}
