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
 * Servlet implementation class SongDelServlet
 */
@WebServlet("/SongDelServlet")
public class SongDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongDelServlet() {
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
		String songIdS=request.getParameter("songId");
		/*if(null != songIdS){
			int songId =  Integer.parseInt(songIdS);
			SongBiz songBiz=new SongBizImpl();
			
			try {
				songBiz.deleteSonnById(songId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		if(null != songIdS && !songIdS.equals("")){
			int songId =  Integer.parseInt(songIdS);
			SongBiz songBiz=new SongBizImpl();
			boolean res = songBiz.deleteSongById(songId);
			if(!res){
				System.out.println("歌曲删除失败");
			}
		}
		request.getRequestDispatcher("/Admin/music_list.jsp").forward(request, response);
	}

	

}
