package com.nuc.biz;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.nuc.bean.SongsBean;
import com.nuc.bean.SongsVo;

public interface SongBiz {
	/**
	 * @author yadi
	 * @todo 增加歌曲
	 * @param songParamter 歌曲内容信息
	 * @throws Exception if have a error
	 */
	public SongsBean addBeforeSong(String...songParamter) throws Exception;
	/**
	 * @author yadi
	 * @param songsBean 歌曲bin
	 * @throws Exception if have a error
	 */
	public void addSong(SongsBean songsBean,HttpServletRequest request) throws Exception;
	/**
	 * 删除歌曲内容
	 * @param id 歌曲信息编号
	 * @throws Exception if have a error
	 */
	public void deleteSonnById(int id) throws Exception;
	/**
	 * @function 得到歌曲列表行数*/
	public int getSongInfoRows();
	/**
	 * @function 得到歌曲列表行数
	 * @param 歌曲名称*/
	public int getSongInfoRows(String songName);
	/**
	 * @funtion 歌曲列表*/
	public List<SongsVo> fetchAllSongsInfoList();
	/**
	 * @funtion 歌曲列表*/
	public List<SongsVo> fetchAllSongsInfoList(int pageno);
	/**
	 * @funtion 歌曲列表
	 * @param songName*/
	public List<SongsVo> fetchAllSongsInfoList(int pageno,String songName);
	public List<SongsVo> fetchAllSongsIdList();
	public List<SongsBean> fetchAllSongForAnd();
	/**
	 * @funtcion 删除歌曲*/
	public boolean deleteSongById(int songId);
	
	/**
	 * @funtion 排行后歌曲列表*/
	public List<SongsVo> fetchAllSongsRankInfoList(int pageno);
	
	
	
}
