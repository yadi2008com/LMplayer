package com.nuc.dao;

import java.util.List;

import com.nuc.bean.TypeBean;

public interface TypeDao {
	/***
	 * @function 添加歌曲类型
	 * @author 陈雅迪
	 */
	boolean addType(TypeBean typeBean) throws Exception;
	/***
	 * @function 检查歌曲类型是否存在
	 * @author 陈雅迪
	 */
	boolean checkType(String typeName)throws Exception;
	/**
	 * @function 遍历所有歌曲类型*/
	List<TypeBean> fetchAlltypeInfoList()throws Exception;
	/**@function 删除类型*/
	boolean deleteTypeById(int typeId)throws Exception;
	/**
	 * @funtion 查找类型信息*/
	TypeBean selType(int typeId)throws Exception;
	/**
	 * @function 更新类型信息*/
	boolean updaType(TypeBean typeBean)throws Exception;
	/**
	 * @function 检查歌曲类型是否被歌曲使用*/
	public boolean checkRelsong(int typeId) throws Exception;
}
