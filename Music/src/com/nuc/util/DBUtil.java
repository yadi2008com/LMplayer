package com.nuc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @function: 封装数据库连接
 * @author 陈雅迪*/
public class DBUtil {
	/**
	 * @function 连接数据库*/
	public static Connection getConnection() throws Exception {
		Connection connection = null;
		String user = "root";
		String password = "";
        String url = "jdbc:mysql://localhost:3306/music?useUnicode=true&characterEncoding=utf8";
		String className = "com.mysql.jdbc.Driver";
		Class.forName(className);
		connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	/**
	 * @功能：数据库关闭的方法
	 * @param connection
	 * @param preparedStatement
	 * @param resultSet
	 * @throws Exception
	 */
	public static void closeDBSource(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet)
			throws Exception {
		if (resultSet != null) {
			resultSet.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
