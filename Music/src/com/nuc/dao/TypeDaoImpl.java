package com.nuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nuc.bean.TypeBean;
import com.nuc.util.DBUtil;

public class TypeDaoImpl implements TypeDao {
	DBUtil dbUtil=new DBUtil();
	Connection connection =null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	/***
	 * @function 添加歌曲类型
	 * @author 陈雅迪
	 */
	@Override
	public boolean addType(TypeBean typeBean) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "insert into type (typeName,typeParentId) values (?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, typeBean.getTypeName());
		preparedStatement.setInt(2, typeBean.getTypeParentId());
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}/***
	 * @function 检查歌曲类型是否存在
	 * @author 陈雅迪
	 * @return true 存在 false 不存在
	 */
	@Override
	public boolean checkType(String typeName) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "select * from type where typeName like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,typeName);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			flag = true;
			break;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return flag;
	}
	/**
	 * @function 遍历所有歌曲类型*/
	@Override
	public List<TypeBean> fetchAlltypeInfoList() throws Exception{
		List<TypeBean> typeInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from type";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		typeInfoList = new ArrayList<TypeBean>();
		while (resultSet.next()) {
			TypeBean typeBean = new TypeBean();
			typeBean.setTypeId(resultSet.getInt("typeId"));
			typeBean.setTypeName(resultSet.getString("typeName"));
			typeBean.setTypeParentId(resultSet.getInt("typeParentId"));
			typeInfoList.add(typeBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return typeInfoList;
	}
	/**@function 删除类型*/
	@Override
	public boolean deleteTypeById(int typeId) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "delete from type where typeId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,typeId);
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	/**
	 * @function 查找类型信息*/
	@Override
	public TypeBean selType(int typeId) throws Exception {
		connection = dbUtil.getConnection();
		String sql = "select * from type where typeId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,typeId);
		resultSet = preparedStatement.executeQuery();
		TypeBean typeBean = new TypeBean();
		while (resultSet.next()) {
			typeBean.setTypeId(resultSet.getInt("typeId"));
			typeBean.setTypeName(resultSet.getString("typeName"));
			typeBean.setTypeParentId(resultSet.getInt("typeParentId"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return typeBean;
	}
	/**
	 * @function 更新类型信息*/
	@Override
	public boolean updaType(TypeBean typeBean) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "update type set typeName=?,typeParentId=? where typeId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, typeBean.getTypeName());
		preparedStatement.setInt(2, typeBean.getTypeParentId());
		preparedStatement.setInt(3, typeBean.getTypeId());
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	/**
	 * @function 检查歌曲类型是否被歌曲使用
	 * @return false该类型没有被歌曲使用 ，true该类型被歌曲
	 */
	@Override
	public boolean checkRelsong(int typeId) throws Exception {
		boolean result = false;// 无相关歌曲为false,有相关为true

		DBUtil dbUtil = new DBUtil();
		connection = dbUtil.getConnection();
		String sql = "select * from songs where typeId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, typeId);
		resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			result = true;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return result;

	}

	
}
