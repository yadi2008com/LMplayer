package com.nuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nuc.bean.PageBean;
import com.nuc.bean.SingersBean;
import com.nuc.util.DBUtil;

public class SingerDaoImpl implements SingerDao {
	DBUtil dbUtil=new DBUtil();
	Connection connection =null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	/**
	 * @function 检查歌手名是否存在*/
	@Override
	public boolean checkSinger(String singerName) throws Exception {
		boolean flag = false;
		connection = DBUtil.getConnection();
		String sql = "select * from singers where singerName like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,singerName);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			flag = true;
			break;
		}
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		return flag;
	}
	@Override
	public boolean checkSinger(String singerName, String singerInfo)
			throws Exception {
		boolean flag = false;
		connection = DBUtil.getConnection();
		String sql = "select * from singers where singerName like ? and singerInfo like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,singerName);
		preparedStatement.setString(2,singerInfo);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			flag = true;
			break;
		}
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		return flag;
	}
	/**
	 * @function 增加歌手*/
	@Override
	public boolean addSinger(SingersBean singersBean) throws Exception {
		boolean flag = false;
		connection = DBUtil.getConnection();
		String sql = "insert into singers (singerName,singerInfo) values (?,?)";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, singersBean.getSingerName());
		preparedStatement.setString(2, singersBean.getSingerInfo());
		int rows = preparedStatement.executeUpdate();
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	/**
	 * @function 得到歌手列表行数*/
	@Override
	public int getSingerInfoRows() throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = DBUtil.getConnection();
			String sql = "select count(*) from singers";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
		
	}
	/**
	 * @function 歌手列表*/
	@Override
	public List<SingersBean> fetchAllSingersInfoList(int pageno)
			throws Exception {
		List<SingersBean> singersBeanInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "select * from singers limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		singersBeanInfoList = new ArrayList<SingersBean>();
		while (resultSet.next()) {
			SingersBean singersBean = new SingersBean();
			singersBean.setSingerId(resultSet.getInt("singerId"));
			singersBean.setSingerName(resultSet.getString("singerName"));
			singersBean.setSingerInfo(resultSet.getString("singerInfo"));
			singersBeanInfoList.add(singersBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return singersBeanInfoList;
	}
	
	@Override
	public List<SingersBean> fetchAllSingersInfoList() throws Exception {
		List<SingersBean> singersBeanInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from singers";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		singersBeanInfoList = new ArrayList<SingersBean>();
		while (resultSet.next()) {
			SingersBean singersBean = new SingersBean();
			singersBean.setSingerId(resultSet.getInt("singerId"));
			singersBean.setSingerName(resultSet.getString("singerName"));
			singersBean.setSingerInfo(resultSet.getString("singerInfo"));
			singersBeanInfoList.add(singersBean);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return singersBeanInfoList;
	}
	
	/**@function 删除歌手*/
	@Override
	public boolean deleteSingerById(int singerId) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "delete from singers where singerId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,singerId);
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	/**@fucntion 查找歌手信息*/
	@Override
	public SingersBean selSinger(int singerId) throws Exception {
		connection = dbUtil.getConnection();
		String sql = "select * from singers where singerId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1,singerId);
		resultSet = preparedStatement.executeQuery();
		SingersBean singersBean = new SingersBean();
		while (resultSet.next()) {
			singersBean.setSingerId(resultSet.getInt("singerId"));
			singersBean.setSingerName(resultSet.getString("singerName"));
			singersBean.setSingerInfo(resultSet.getString("singerInfo"));
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return singersBean;
	}
	/**@function 修改歌手信息*/
	@Override
	public boolean updaSinger(SingersBean singersBean) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "update singers set singerName=?,singerInfo=? where singerId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, singersBean.getSingerName());
		preparedStatement.setString(2, singersBean.getSingerInfo());
		preparedStatement.setInt(3, singersBean.getSingerId());
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	
	/** @fucntion 检查歌曲表内是否含此歌手 */
	@Override
	public boolean checkRelSongSinger(int singerId) {
		boolean result = false;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from songs where singerId=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, singerId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}
}
