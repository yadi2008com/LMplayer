package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.bean.TypeBean;
import com.nuc.biz.TypeBiz;
import com.nuc.biz.TypeBizImpl;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/TypeServlet")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
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
		
		String typeIds = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		int typeId = Integer.parseInt(typeIds);
		String message = "";
		String url = "/Admin/type_list.jsp";
		TypeBiz typeBiz=new TypeBizImpl();
		boolean typeIsExists = typeBiz.checkType(typeName);
		if(typeIsExists){
			message = "该类型已经存在，请重新添加";
			url = "/Admin/type_edit.jsp";
			response.sendRedirect("Admin/type_edit.jsp?error=yes");
		}else{
			TypeBean typeBean = new TypeBean();
			typeBean.setTypeParentId(typeId);
			typeBean.setTypeName(typeName);
			//增加歌曲类型，如果成功返回ture，失败返回false
			boolean flag = typeBiz.addType(typeBean);
			if(!flag){
				message = "未增加成功，请您重新试一下";
				url = "/Admin/type_edit.jsp";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}

}
