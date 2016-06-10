package com.nuc.frameworkUtil;


/**
 * 
 * @author yadi
 * @todo 做一些基础的验证及数据类型操作
 *
 */
public class DataUtil {
	/**
	 * 验证字符串是否为空,参数必验证
	 * @param params 参数集合
	 * @return 验证结果
	 */
	public static boolean mustValidateString(String...params){
		if(null==params || params.length==0){
			return false;
		}
		for (String string : params) {
			if(null==string || "".equals(string)){
				return false;
			}
		}
		return true;
	} 
	
}
