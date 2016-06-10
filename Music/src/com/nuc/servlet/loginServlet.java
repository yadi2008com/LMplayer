package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.biz.UserBiz;
import com.nuc.biz.UserBizImpl;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		String username = request.getParameter("username");
		String userpwd = request.getParameter("userpwd");
		UserBiz userBiz=new UserBizImpl();
		boolean flag = userBiz.login(username,userpwd);
		String message;
		String url = "/Admin/back.jsp";
		/*if(!flag){
			url = "/Admin/login.jsp";
			message = "用户名或密码错误，请您重新输入";
			System.out.println(message);
		}
		request.getRequestDispatcher(url).forward(request, response);*/
		if (flag) {
			//验证成功，转向登录成功后的界面
			   request.getRequestDispatcher(url).forward(request, response);
		} else {
			//验证失败，转向登录界面，并传递一个参数error，其值为yes
			//response.sendRedirect("/Admin/login.jsp?error=yes");
			response.sendRedirect("Admin/login.jsp?error=yes");
	    }


	}

}
