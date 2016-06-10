package com.nuc.biz;

import java.util.List;

import com.nuc.bean.SingersBean;

public interface SingerBiz {
	/**
	 * @function 检查歌手名是否存在*/
	boolean checkSinger(String singerName);
	
	boolean checkSinger(String singerName, String singerInfo);
	/**
	 * @function 增加歌手*/
	boolean addSinger(SingersBean singersBean);
	/**
	 * @function 得到歌手列表行数*/
	int getSingerInfoRows();
	/**
	 * @function 歌手列表*/
	List<SingersBean> fetchAllSingersInfoList();
	
	List<SingersBean> fetchAllSingersInfoList(int pageno);
	/**@function 删除歌手*/
	boolean  deleteSingerById(int singerId);
	/**@fucntion 查找歌手信息*/
	SingersBean selSinger(int singerId);
	/**@function 修改歌手信息*/
	boolean updaSinger(SingersBean singersBean);
	/**@fucntion 检查歌曲表内是否含此歌手*/
	public boolean checkRelSongSinger(int singerId);
	


}
