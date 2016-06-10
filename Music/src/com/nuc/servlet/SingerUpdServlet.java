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
 * Servlet implementation class SingerUpdServlet
 */
@WebServlet("/SingerUpdServlet")
public class SingerUpdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingerUpdServlet() {
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
		String message ="修改成功";
		String url = "/SingerListServlet";
		String singerIdS = request.getParameter("singerId");
		String singerName = request.getParameter("singerName");
		String singerInfo = request.getParameter("singerInfo");
		int singerId =  Integer.parseInt(singerIdS);
		SingerBiz singerBiz=new SingerBizImpl();
		boolean singerIsExists = singerBiz.checkSinger(singerName,singerInfo);
		if(singerIsExists){
			response.sendRedirect("Admin/singer_edit.jsp?error=yes");
		}else{
			SingersBean singersBean = new SingersBean();
			singersBean.setSingerId(singerId);
			singersBean.setSingerName(singerName);
			singersBean.setSingerInfo(singerInfo);
			//修改歌手，如果成功返回ture，失败返回false
			boolean flag = singerBiz.updaSinger(singersBean);
			if(!flag){
				message = "未更新成功，请您重新试一下";
				System.out.println(message);
				url = "/Admin/singer_edit.jsp";
			}
			request.setAttribute("message", message);
			request.getRequestDispatcher(url).forward(request, response);
		}
		
		
	}

}
