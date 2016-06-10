package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.bean.SingersBean;
import com.nuc.bean.TypeBean;
import com.nuc.biz.SingerBiz;
import com.nuc.biz.SingerBizImpl;

/**
 * Servlet implementation class SingerAddServlet
 */
@WebServlet("/SingerAddServlet")
public class SingerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingerAddServlet() {
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
		String singerName = request.getParameter("singerName");
		String singerInfo = request.getParameter("singerInfo");
		String message = "";
		String url = "/SingerListServlet";
		SingerBiz singerBiz=new SingerBizImpl();
		boolean singerIsExists = singerBiz.checkSinger(singerName);
		if(singerIsExists){
			message = "该歌手已经存在，请重新添加";
			//url = "/Admin/singer_edit.jsp";
			//request.setAttribute("message", message);
			request.getRequestDispatcher("/Admin/singer_edit.jsp?error=yes").forward(request, response);
		}else{
			SingersBean singersBean = new SingersBean();
			singersBean.setSingerName(singerName);
			singersBean.setSingerInfo(singerInfo);
			//增加歌手，如果成功返回ture，失败返回false
			boolean flag = singerBiz.addSinger(singersBean);
			if(!flag){
				message = "未增加成功，请您重新试一下";
				url = "/Admin/singer_edit.jsp";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

}
