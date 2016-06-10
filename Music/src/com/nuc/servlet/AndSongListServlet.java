package com.nuc.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.nuc.bean.PageBean;
import com.nuc.bean.SongsBean;
import com.nuc.bean.SongsVo;
import com.nuc.bean.TypeBean;
import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;
import com.nuc.biz.TypeBiz;
import com.nuc.biz.TypeBizImpl;
import com.nuc.frameworkUtil.ResponseUtil;

/**
 * Servlet implementation class AndSongListServlet
 */
@WebServlet("/AndSongListServlet")
public class AndSongListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AndSongListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		SongBiz songBiz=new SongBizImpl();
		//歌曲类型列表
		List<SongsBean> songsInfoList = songBiz.fetchAllSongForAnd();
		String jsonStr=JSONArray.toJSONString(songsInfoList);
		System.out.println(jsonStr);
		ResponseUtil.outJson(response, jsonStr);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
