package com.nuc.biz;

import java.util.List;

import com.nuc.bean.TypeBean;
import com.nuc.bean.UsersBean;
import com.nuc.dao.UserDao;
import com.nuc.dao.UserDaoImpl;



public class UserBizImpl implements UserBiz{
	UserDao userDao=new UserDaoImpl();
	/***
	 * @function 注册
	 * @author 陈雅迪
	 */
	public boolean register(UsersBean usersBean){
		boolean flag = false;
		UserDao userDao=new UserDaoImpl();
		try {
			flag = userDao.register(usersBean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @author chenyadi
	 * @function 登录*/
	@Override
	public boolean login(String username, String userpwd) {
		boolean flag = false;
		
		try {
			flag = userDao.login(username,userpwd);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * @function 所有管理员列表显示*/
	@Override
	public List<UsersBean> fetchAlluserInfoList() {
		// 创建堆内存空间并赋值为空
		List<UsersBean> userInfoList = null;
		try {
			userInfoList = userDao.fetchAlluserInfoList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfoList;
	}
}
