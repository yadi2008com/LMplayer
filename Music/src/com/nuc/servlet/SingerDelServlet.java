package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.biz.SingerBiz;
import com.nuc.biz.SingerBizImpl;
import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;

/**
 * Servlet implementation class SingerDelServlet
 */
@WebServlet("/SingerDelServlet")
public class SingerDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingerDelServlet() {
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
		if(null != singerIdS && !singerIdS.equals("")){
			int singerId =  Integer.parseInt(singerIdS);
			SingerBiz singerBiz=new SingerBizImpl();
			
			boolean checkRelSongSinger = singerBiz.checkRelSongSinger(singerId);
			if(!checkRelSongSinger){
				boolean res = singerBiz.deleteSingerById(singerId);
				if(!res){
					System.out.println("歌手删除失败");
				}
				request.getRequestDispatcher("/SingerListServlet").forward(request, response);
			}else{
				request.getRequestDispatcher("/SingerListServlet").forward(request, response);
			}
			
		}
		
	}

}
