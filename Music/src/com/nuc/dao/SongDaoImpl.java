package com.nuc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nuc.bean.PageBean;
import com.nuc.bean.SongsBean;
import com.nuc.bean.SongsVo;
import com.nuc.util.DBUtil;

public class SongDaoImpl extends BaseDao implements SongDao {
	/**
	 * @author yadi
	 * @param songsBean
	 *            歌曲信息
	 * @exception if
	 *                have a error
	 */
	@Override
	public int addSong(SongsBean songsBean) throws Exception {
		/*
		 * String sql =
		 * "insert into songs (songName,singerId,songTime,typeId,songWord,songHeat,songAlbum,songImg) values (?,?,?,?,?,?,?,?)"
		 * ; Object []
		 * params={songsBean.getSongName(),songsBean.getSingerId(),songsBean
		 * .getSongTime(),songsBean.getTypeId(),
		 * songsBean.getSongWord(),songsBean
		 * .getSongHeat(),songsBean.getSongAlbum(),songsBean.getSongImg()};
		 */
		String sql = "insert into songs (songName,singerId,songTime,typeId,songWord,songHeat,songAlbum) values (?,?,?,?,?,?,?)";
		Object[] params = { songsBean.getSongName(), songsBean.getSingerId(),
				songsBean.getSongTime(), songsBean.getTypeId(),
				songsBean.getSongWord(), songsBean.getSongHeat(),
				songsBean.getSongAlbum() };
		int resultId = 0;
		try {
			Object resultObject = this.insert(sql, params);
			if (null != resultObject) {
				resultId = Integer.valueOf(String.valueOf(resultObject));
			}
			if (resultId < 1) {
				throw new Exception("添加歌曲失败！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("添加歌曲失败！！");
		}
		return resultId;
	}

	/**
	 * 删除歌曲内容
	 * 
	 * @param id
	 *            歌曲信息编号
	 * @throws Exception
	 *             if have a error
	 */
	public int deleteSonnById(int id) throws Exception {
		String sql = "delete from songs where songId=?";
		int resultId = 0;
		try {
			resultId = this.delete(sql, id);
			if (resultId < 1) {
				throw new Exception("删除歌曲失败！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("删除歌曲失败！！");
		}
		return resultId;
	}

	/**
	 * @function 得到歌曲列表行数
	 */
	DBUtil dbUtil = new DBUtil();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	@Override
	public int getSongInfoRows() throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			String sql = "select count(*) from songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @function 得到歌曲列表行数
	 * @param 歌曲名称
	 */
	public int getSongInfoRows(String songName) throws Exception {
		int result = 0;
		try {
			DBUtil dbUtil = new DBUtil();
			connection = dbUtil.getConnection();
			// String sql =
			// "select count(*) from songs where songName like '%'||?||'%'";
			String sql = "select count(*) from songs s,type t,singers a where s.songName=? and s.typeId = t.typeId and  s.singerId = a.singerId";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, songName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				result = resultSet.getInt(1);
			}
			dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * @function 歌曲列表
	 */
	@Override
	public List<SongsVo> fetchAllSongsInfoList(int pageno) throws Exception {
		List<SongsVo> songsVoInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "SELECT s.*,t.typeName,a.singerName FROM songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		songsVoInfoList = new ArrayList<SongsVo>();
		while (resultSet.next()) {
			SongsVo songsVo = new SongsVo();
			songsVo.setSongId(resultSet.getInt("songId"));
			songsVo.setSongName(resultSet.getString("songName"));
			songsVo.setSingerName(resultSet.getString("singerName"));
			songsVo.setSongTime(resultSet.getDate("songTime"));
			songsVo.setTypeName(resultSet.getString("typeName"));
			songsVo.setSongWord(resultSet.getString("songWord"));
			songsVo.setSongHeat(resultSet.getInt("songHeat"));
			songsVoInfoList.add(songsVo);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsVoInfoList;
	}

	/**
	 * @function 歌手列表
	 * @param songName
	 *            ,pageno
	 */
	@Override
	public List<SongsVo> fetchAllSongsInfoList(int pageno, String songName)
			throws Exception {
		List<SongsVo> songsVoInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		// String sql =
		// "SELECT s.*,t.typeName,a.singerName FROM songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId and s.songName like '%'||?||'%' limit ?,?";
		String sql = "SELECT s.*,t.typeName,a.singerName FROM songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId and s.songName=? limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		// preparedStatement.setString(1,"%"+songName+"%");
		preparedStatement.setString(1, songName);
		preparedStatement.setInt(2, indexRows);
		preparedStatement.setInt(3, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		songsVoInfoList = new ArrayList<SongsVo>();
		while (resultSet.next()) {
			SongsVo songsVo = new SongsVo();
			songsVo.setSongId(resultSet.getInt("songId"));
			songsVo.setSongName(resultSet.getString("songName"));
			songsVo.setSingerName(resultSet.getString("singerName"));
			songsVo.setSongTime(resultSet.getDate("songTime"));
			songsVo.setTypeName(resultSet.getString("typeName"));
			songsVo.setSongWord(resultSet.getString("songWord"));
			songsVo.setSongHeat(resultSet.getInt("songHeat"));
			songsVoInfoList.add(songsVo);
		}

		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsVoInfoList;
	}

	@Override
	public List<SongsVo> fetchAllSongsInfoList() throws Exception {
		List<SongsVo> songsVoInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "SELECT s.*,t.typeName,a.singerName FROM songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		songsVoInfoList = new ArrayList<SongsVo>();
		while (resultSet.next()) {
			SongsVo songsVo = new SongsVo();
			songsVo.setSongId(resultSet.getInt("songId"));
			songsVo.setSongName(resultSet.getString("songName"));
			songsVo.setSingerName(resultSet.getString("singerName"));
			songsVo.setSongTime(resultSet.getDate("songTime"));
			songsVo.setTypeName(resultSet.getString("typeName"));
			songsVo.setSongWord(resultSet.getString("songWord"));
			songsVo.setSongHeat(resultSet.getInt("songHeat"));
			songsVoInfoList.add(songsVo);
		}

		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsVoInfoList;
	}

	@Override
	public List<SongsVo> fetchAllSongsIdList() throws Exception {
		List<SongsVo> songsVoInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "SELECT s.songId,s.songName FROM songs s";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		songsVoInfoList = new ArrayList<SongsVo>();
		while (resultSet.next()) {
			SongsVo songsVo = new SongsVo();
			songsVo.setSongId(resultSet.getInt("songId"));
			songsVo.setSongName(resultSet.getString("songName"));
			songsVoInfoList.add(songsVo);
		}
		DBUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsVoInfoList;
	}

	@Override
	public List<SongsBean> fetchAllSongForAnd() throws Exception {
		List<SongsBean> songsInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from songs";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		songsInfoList = new ArrayList<SongsBean>();
		while (resultSet.next()) {
			SongsBean songsBean = new SongsBean();
			songsBean.setSongId(resultSet.getInt("songId"));
			songsBean.setSongName(resultSet.getString("songName"));
			songsInfoList.add(songsBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsInfoList;
	}

	/**
	 * @funtcion 删除歌曲
	 */
	@Override
	public boolean deleteSongById(int songId) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "delete from songs where songId=?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, songId);
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if (rows == 1) {
			flag = true;
		}
		return flag;
	}

	
	/**
	 * @funtion 排行后歌曲列表
	 */
	@Override
	public List<SongsVo> fetchAllSongsRankInfoList(int pageno) throws Exception {
		List<SongsVo> songsVoInfoList = null;
		connection = dbUtil.getConnection();
		int indexRows = (pageno - 1) * PageBean.ROWS_PRE_PAGE;
		String sql = "SELECT s.*,t.typeName,a.singerName FROM songs s,type t,singers a WHERE s.typeId = t.typeId and  s.singerId = a.singerId order by songHeat DESC limit ?,?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, indexRows);
		preparedStatement.setInt(2, PageBean.ROWS_PRE_PAGE);
		resultSet = preparedStatement.executeQuery();
		songsVoInfoList = new ArrayList<SongsVo>();
		while (resultSet.next()) {
			SongsVo songsVo = new SongsVo();
			songsVo.setSongId(resultSet.getInt("songId"));
			songsVo.setSongName(resultSet.getString("songName"));
			songsVo.setSingerName(resultSet.getString("singerName"));
			songsVo.setSongTime(resultSet.getDate("songTime"));
			songsVo.setTypeName(resultSet.getString("typeName"));
			songsVo.setSongWord(resultSet.getString("songWord"));
			songsVo.setSongHeat(resultSet.getInt("songHeat"));
			songsVoInfoList.add(songsVo);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return songsVoInfoList;
	}

	

}
