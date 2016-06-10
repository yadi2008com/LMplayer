package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.bean.UsersBean;
import com.nuc.biz.UserBiz;
import com.nuc.biz.UserBizImpl;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
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
		String message = "注册成功，请登录";
		String url = "Admin/login.jsp";
		UserBiz userBiz=new UserBizImpl();
		UsersBean usersBean = new UsersBean();
		usersBean.setUserName(username);
		usersBean.setUserPwd(userpwd);
		
		boolean flag = userBiz.register(usersBean);
		if(!flag){
			message = "未注册成功，请您重新试一下";
			url = "Admin/register.jsp";
		}
		request.setAttribute("registerOk", message);
		request.getRequestDispatcher(url).forward(request, response);
	}

}
