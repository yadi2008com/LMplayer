package com.nuc.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nuc.bean.TypeBean;
import com.nuc.bean.UsersBean;
import com.nuc.util.DBUtil;



public class UserDaoImpl implements UserDao {
	DBUtil dbUtil=new DBUtil();
	Connection connection =null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	@Override
	public boolean register(UsersBean usersBean) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "insert into users (userName,userPwd) values (?,?)";

		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, usersBean.getUserName());
		preparedStatement.setString(2, usersBean.getUserPwd());
		int rows = preparedStatement.executeUpdate();
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		if(rows==1){
			flag = true;
		}
		return flag;
	}
	/**
	 * @author chenyadi
	 * @function 登录*/
	@Override
	public boolean login(String username, String userpwd) throws Exception {
		boolean flag = false;
		connection = dbUtil.getConnection();
		String sql = "select * from users where userName like ? and userPwd like ?";
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,username);
		preparedStatement.setString(2,userpwd);
		resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			flag = true;
			break;
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return flag;
	}
	/**
	 * @function 所有管理员列表显示*/
	@Override
	public List<UsersBean> fetchAlluserInfoList() throws Exception {
		List<UsersBean> userInfoList = null;
		connection = dbUtil.getConnection();
		String sql = "select * from users";
		preparedStatement = connection.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		userInfoList = new ArrayList<UsersBean>();
		while (resultSet.next()) {
			UsersBean usersBean = new UsersBean();
			usersBean.setUserId(resultSet.getInt("userId"));
			usersBean.setUserName(resultSet.getString("userName"));
			String userPwdMD5 = encodeByMD5(resultSet.getString("userPwd"));   
			usersBean.setUserPwd(userPwdMD5);
			userInfoList.add(usersBean);
		}
		dbUtil.closeDBSource(connection, preparedStatement, resultSet);
		return userInfoList;
	}
	/**
	 * @funtion 密码加密*/
	private static String encodeByMD5(String originString){   
        if (originString != null){   
            try{   
                //创建具有指定算法名称的信息摘要   
                 MessageDigest md = MessageDigest.getInstance("MD5");   
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算   
                byte[] results = md.digest(originString.getBytes());   
                //将得到的字节数组变成字符串返回   
                 String resultString = byteArrayToHexString(results);   
                return resultString.toUpperCase();   
             } catch(Exception ex){   
                 ex.printStackTrace();   
             }   
         }   
        return null;   
     }   
	 private static String byteArrayToHexString(byte[] b){   
        StringBuffer resultSb = new StringBuffer();   
       for (int i = 0; i < b.length; i++){   
            resultSb.append(byteToHexString(b[i]));   
        }   
        return resultSb.toString();   
     }  
	 private static String byteToHexString(byte b){   
	        int n = b;   
	        if (n < 0)   
	             n = 256 + n;   
	        int d1 = n / 16;   
	        int d2 = n % 16;   
	        return hexDigits[d1] + hexDigits[d2];   
	 }
	//十六进制下数字到字符的映射数组   
     private final static String[] hexDigits = {"0", "1", "2", "3", "4",   
	        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};   
}
