package com.nuc.biz;

import java.util.List;

import com.nuc.bean.UsersBean;

public interface UserBiz {
	/**
	 * @author chenyadi
	 * @function 注册*/
	boolean register(UsersBean usersBean);
	/**
	 * @author chenyadi
	 * @function 登录*/
	boolean login(String username, String userpwd);
	/**
	 * @function 所有管理员列表显示*/
	List<UsersBean> fetchAlluserInfoList();

}
