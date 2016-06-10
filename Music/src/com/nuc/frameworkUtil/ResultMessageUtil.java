package com.nuc.frameworkUtil;

import com.alibaba.fastjson.JSONArray;

/**
 * 封装相应消息工具类
 * @author yadi
 *
 */
public class ResultMessageUtil {
	/**
	 * 有返回数据
	 * @param message
	 * @param data
	 * @return
	 */
	public static String returnJsonData(String message,Object data){
		return null;
		
	}
	/**
	 * 相应消息
	 * @param message
	 * @return
	 */
	public static String returnJsonMessage(boolean flag,String message){
		ResultMessage resultMessage=new ResultMessage();
		if(flag){
			resultMessage.setSuccess("success");
		}else{
			resultMessage.setSuccess("error");
		}
		resultMessage.setMessage(message);
		return JSONArray.toJSONString(resultMessage);
	}
}
