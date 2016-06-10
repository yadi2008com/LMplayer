package com.nuc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * Servlet implementation class TypeSelServlet
 */
@WebServlet("/TypeSelServlet")
public class TypeSelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeSelServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String typeIdS = request.getParameter("typeId");
		 
		int typeId =  Integer.parseInt(typeIdS);
		TypeBean typeBean = typeBiz.selType(typeId);
		request.setAttribute("typeBean",typeBean);
//		String error = request.getParameter("error");
//		if(error=="yes"){
//			request.getRequestDispatcher("/Admin/type_upda.jsp?error=yes").forward(request, response);
//		}
		request.getRequestDispatcher("/Admin/type_upda.jsp").forward(request, response);
		
	}

}
