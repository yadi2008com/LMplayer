package com.nuc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.nuc.bean.TypeBean;
import com.nuc.biz.TypeBiz;
import com.nuc.biz.TypeBizImpl;
import com.nuc.frameworkUtil.ResponseUtil;

/**
 * Servlet implementation class TypeListServlet
 */
@WebServlet("/TypeListServlet")
public class TypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		TypeBiz typeBiz=new TypeBizImpl();
		//歌曲类型列表
		List<TypeBean> typeInfoList = typeBiz.fetchAlltypeInfoList();
		String jsonStr=JSONArray.toJSONString(typeInfoList);
		ResponseUtil.outJson(response, jsonStr);
		System.out.println("删除后返回类型值为："+jsonStr);
	}

}
