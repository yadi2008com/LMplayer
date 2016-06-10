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
 * Servlet implementation class TypeUpdServlet
 */
@WebServlet("/TypeUpdServlet")
public class TypeUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeUpdServlet() {
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
		String typeIds = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		String typeParentIds = request.getParameter("typeParentId");
		int typeId =Integer.parseInt(typeIds.trim()) ;
		int typeParentId = Integer.parseInt(typeParentIds.trim());
		String message = "";
		String url = "/Admin/type_list.jsp";
		boolean typeIsExists = typeBiz.checkType(typeName);
		if(typeIsExists){
			message = "该类型已经存在，请重新更新";
			url = "/Admin/type_edit.jsp";
			response.sendRedirect("Admin/type_edit.jsp?error=yes");
		}else{
			TypeBean typeBean = new TypeBean();
			typeBean.setTypeId(typeId);
			typeBean.setTypeParentId(typeParentId);
			typeBean.setTypeName(typeName);
			//增加歌曲类型，如果成功返回ture，失败返回false
			boolean flag = typeBiz.updaType(typeBean);
			if(!flag){
				message = "未更新成功，请您重新试一下";
				System.out.println(message);
				url = "/Admin/type_edit.jsp";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher(url).forward(request, response);
		}
		
	}

}
