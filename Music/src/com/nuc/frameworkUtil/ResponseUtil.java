package com.nuc.frameworkUtil;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * ajax请求返回数据
 * @author yadi
 *
 */
public class ResponseUtil {
	/**
	 * 
	 * @param response HttpServletResponse 对象实例
	 * @param jsonStr json字符串
	 * @throws IOException if hava a error
	 */
	public static void outJson(HttpServletResponse response,String jsonStr) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(jsonStr);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
