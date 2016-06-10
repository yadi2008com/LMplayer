package com.nuc.dao;

import java.util.List;

import com.nuc.bean.SingersBean;

public interface SingerDao {
	/**
	 * @function 检查歌手名是否存在*/
	boolean checkSinger(String singerName) throws Exception;
	
	boolean checkSinger(String singerName, String singerInfo)throws Exception;
	/**
	 * @function 增加歌手*/
	boolean addSinger(SingersBean singersBean)throws Exception;
	/**
	 * @function 得到歌手列表行数*/
	int getSingerInfoRows()throws Exception;
	/**
	 * @function 歌手列表*/
	List<SingersBean> fetchAllSingersInfoList(int pageno)throws Exception;
	
	List<SingersBean> fetchAllSingersInfoList()throws Exception;
	/**@function 删除歌手*/
	boolean deleteSingerById(int singerId)throws Exception;
	/**@fucntion 查找歌手信息*/
	SingersBean selSinger(int singerId)throws Exception;
	/**@function 修改歌手信息*/
	boolean updaSinger(SingersBean singersBean)throws Exception;
	/**@fucntion 检查歌曲表内是否含此歌手*/
	public boolean checkRelSongSinger(int singerId)throws Exception;
	
	

}
