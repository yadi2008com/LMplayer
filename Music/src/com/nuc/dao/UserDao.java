package com.nuc.dao;

import java.util.List;

import com.nuc.bean.UsersBean;

public interface UserDao {
	/**
	 * @author chenyadi
	 * @function 注册*/
	boolean register(UsersBean usersBean)throws Exception;
	/**
	 * @author chenyadi
	 * @function 登录*/
	boolean login(String username, String userpwd)throws Exception;
	/**
	 * @function 所有管理员列表显示*/
	List<UsersBean> fetchAlluserInfoList()throws Exception;

}
