package com.nuc.biz;

import java.util.List;

import com.nuc.bean.TypeBean;
import com.nuc.dao.TypeDao;
import com.nuc.dao.TypeDaoImpl;

public class TypeBizImpl implements TypeBiz {
	TypeDao typeDao=new TypeDaoImpl();
	/***
	 * @function 添加歌曲类型
	 * @author 陈雅迪
	 */
	@Override
	public boolean addType(TypeBean typeBean) {
		boolean flag = false;
		try {
			flag = typeDao.addType(typeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/***
	 * @function 检查歌曲类型是否存在
	 * @author 陈雅迪
	 */
	@Override
	public boolean checkType(String typeName) {
		boolean flag = false;
		try {
			flag = typeDao.checkType(typeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @function 遍历所有歌曲类型*/
	@Override
	public List<TypeBean> fetchAlltypeInfoList() {
		// 创建堆内存空间并赋值为空
		List<TypeBean> typeInfoList = null;
		try {
			typeInfoList = typeDao.fetchAlltypeInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return typeInfoList;
		
	}
	/**@function 删除类型*/
	@Override
	public boolean deleteTypeById(int typeId) {
		boolean flag = false;
		try {
			flag = typeDao.deleteTypeById(typeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @funtion 查找类型信息*/
	@Override
	public TypeBean selType(int typeId) {
		TypeBean typeBean = null;
		try {
			typeBean = typeDao.selType(typeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return typeBean;
	}
	/**
	 * @function 更新类型信息*/
	@Override
	public boolean updaType(TypeBean typeBean) {
		boolean flag = false;
		try {
			flag = typeDao.updaType(typeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @function 检查歌曲类型是否被歌曲使用*/
	@Override
	public boolean checkRelsong(int typeId) {
		boolean flag = false;//无相关歌曲为false,有相关为true
		try {
			flag = typeDao.checkRelsong(typeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
