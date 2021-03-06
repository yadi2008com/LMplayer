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
import com.nuc.bean.SongsVo;
import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;
import com.nuc.frameworkUtil.ResponseUtil;

/**
 * Servlet implementation class SongListServlet
 */
@WebServlet("/SongListServlet")
public class SongListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongListServlet() {
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
		SongBiz songBiz=new SongBizImpl();
		int pageno=1;
		int getSongInfoRows;
		int maxpage;
		List<SongsVo> songsVoInfoList;
		if(request.getParameter("pageno")!=null){
		String	pagenoString=request.getParameter("pageno");
		pageno=Integer.parseInt(pagenoString);
		}
		String songName = request.getParameter("songName");
		PageBean pageBean=new PageBean();
		if(songName!= null && !songName.equals("")){
			getSongInfoRows=songBiz.getSongInfoRows(songName);
			//得到歌曲的信息行数
			maxpage=getSongInfoRows%PageBean.ROWS_PRE_PAGE==0?getSongInfoRows/PageBean.ROWS_PRE_PAGE:(getSongInfoRows/PageBean.ROWS_PRE_PAGE+1);
			if(pageno<1){
										pageno=1;
			}else if(pageno>maxpage){
										pageno=maxpage;
			}
			songsVoInfoList =songBiz.fetchAllSongsInfoList(pageno,songName);
			
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("getSongInfoRows",getSongInfoRows);
			request.setAttribute("songsVoInfoList",songsVoInfoList);
			request.setAttribute("pageBean", pageBean);
		}else{
			getSongInfoRows=songBiz.getSongInfoRows();
			//得到歌曲的信息行数
			maxpage=getSongInfoRows%PageBean.ROWS_PRE_PAGE==0?getSongInfoRows/PageBean.ROWS_PRE_PAGE:(getSongInfoRows/PageBean.ROWS_PRE_PAGE+1);
			if(pageno<1){
										pageno=1;
			}else if(pageno>maxpage){
										pageno=maxpage;
			}
			songsVoInfoList =songBiz.fetchAllSongsInfoList(pageno);
			
			pageBean.setMaxpage(maxpage);
			pageBean.setPageno(pageno);
			request.setAttribute("getSongInfoRows",getSongInfoRows);
			request.setAttribute("songsVoInfoList",songsVoInfoList);
			request.setAttribute("pageBean", pageBean);
		}
		System.out.println(getSongInfoRows);
		request.getRequestDispatcher("Admin/music_list.jsp").forward(request, response);
		/*PageBean pageBean=new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);*/
		/*request.setAttribute("result", getSongInfoRows);
		request.setAttribute("songsVoInfoList",songsVoInfoList);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/Admin/music_list.jsp").forward(request, response);
		String songsjsonStr=JSONArray.toJSONString(songsVoInfoList);
		ResponseUtil.outJson(response,songsjsonStr);
		String getSongInfoRowsJson=JSONArray.toJSONString(getSongInfoRows);
		ResponseUtil.outJson(response,getSongInfoRowsJson);
		String pageBeanJson=JSONArray.toJSONString(pageBean);
		ResponseUtil.outJson(response,pageBeanJson);*/
		Map<String,Object> maps=new HashMap<String,Object>();
		maps.put("songsVoInfoList",songsVoInfoList);
		maps.put("pageBean",pageBean);
		maps.put("getSongInfoRows",getSongInfoRows);
		String  jsonStr=JSONArray.toJSONString(maps);
		System.out.println(jsonStr);
		ResponseUtil.outJson(response,jsonStr);
	}

}
