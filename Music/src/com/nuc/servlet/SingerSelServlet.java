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
import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;
import com.nuc.biz.TypeBiz;
import com.nuc.biz.TypeBizImpl;

/**
 * Servlet implementation class SingerSelServlet
 */
@WebServlet("/SingerSelServlet")
public class SingerSelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingerSelServlet() {
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
		String singerIdS = request.getParameter("singerId");
		int singerId =  Integer.parseInt(singerIdS);
		SingerBiz singerBiz=new SingerBizImpl();
		SingersBean singersBean = singerBiz.selSinger(singerId);
		
		System.out.println(singersBean);
		request.setAttribute("singersBean",singersBean);
		request.getRequestDispatcher("/Admin/singer_upda.jsp").forward(request, response);
	}

}
