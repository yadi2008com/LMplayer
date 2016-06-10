package com.nuc.dao;

import java.util.List;

import com.nuc.bean.SongsBean;
import com.nuc.bean.SongsVo;

public interface SongDao {
	/**
	 * @author yadi
	 * @param songsBean 歌曲信息
	 * @exception if have a error
 	 */
	public int addSong(SongsBean songsBean) throws Exception;
	/**
	 * 删除歌曲内容
	 * @param id 歌曲信息编号
	 * @throws Exception if have a error
	 */
	
	public int deleteSonnById(int id) throws Exception;
	/**
	 * @param songName 
	 * @function 得到歌曲列表行数*/
	public int getSongInfoRows(String songName)throws Exception;
	/**
	 * 
	 * @function 得到歌曲列表行数*/
	public int getSongInfoRows()throws Exception;
	/**
	 * @function 歌手列表*/
	public List<SongsVo> fetchAllSongsInfoList(int pageno)throws Exception;
	/**
	 * @function 歌手列表
	 * @param songName*/
	public List<SongsVo> fetchAllSongsInfoList(int pageno,String songName)throws Exception;
	public List<SongsVo> fetchAllSongsInfoList()throws Exception;
	public List<SongsVo> fetchAllSongsIdList()throws Exception;
	public List<SongsBean> fetchAllSongForAnd()throws Exception;
	/**
	 * @funtcion 删除歌曲*/
	public boolean deleteSongById(int songId)throws Exception;
	
	/**
	 * @funtion 排行后歌曲列表*/
	public List<SongsVo> fetchAllSongsRankInfoList(int pageno)throws Exception;
	
}	
