package com.nuc.biz;

import java.util.List;

import com.nuc.bean.SingersBean;
import com.nuc.dao.SingerDao;
import com.nuc.dao.SingerDaoImpl;

public class SingerBizImpl implements SingerBiz {
	SingerDao singerDao=new SingerDaoImpl();
	/**
	 * @function 检查歌手名是否存在*/
	@Override
	public boolean checkSinger(String singerName) {
		boolean flag = false;
		try {
			flag = singerDao.checkSinger(singerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean checkSinger(String singerName, String singerInfo) {
		boolean flag = false;
		try {
			flag = singerDao.checkSinger(singerName,singerInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @function 增加歌手*/
	@Override
	public boolean addSinger(SingersBean singersBean) {
		boolean flag = false;
		try {
			flag = singerDao.addSinger(singersBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @function 得到歌手列表行数*/
	@Override
	public int getSingerInfoRows() {
		int result = 0;
		try {
			result = singerDao.getSingerInfoRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @function 歌手列表*/
	@Override
	public List<SingersBean> fetchAllSingersInfoList(int pageno) {
		List<SingersBean> singersBeanInfoList = null;
		try {
			singersBeanInfoList = singerDao
					.fetchAllSingersInfoList(pageno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singersBeanInfoList;
	}
	
	@Override
	public List<SingersBean> fetchAllSingersInfoList() {
		List<SingersBean> singersBeanInfoList = null;
		try {
			singersBeanInfoList = singerDao.fetchAllSingersInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singersBeanInfoList;
	}
	/**@function 删除歌手*/
	@Override
	public boolean deleteSingerById(int singerId) {
		boolean flag = false;
		try {
			flag = singerDao.deleteSingerById(singerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**@fucntion 查找歌手信息*/
	@Override
	public SingersBean selSinger(int singerId) {
		SingersBean singersBean= null;
		try {
			singersBean = singerDao.selSinger(singerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singersBean;
	}
	/**@function 修改歌手信息*/
	@Override
	public boolean updaSinger(SingersBean singersBean) {
		boolean flag = false;
		try {
			flag =singerDao.updaSinger(singersBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**@fucntion 检查歌曲表内是否含此歌手*/
	@Override
	public boolean checkRelSongSinger(int singerId) {
		boolean flag = false;
		try {
			flag = singerDao.checkRelSongSinger(singerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
