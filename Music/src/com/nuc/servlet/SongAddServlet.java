package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.nuc.bean.SongsBean;
import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;
import com.nuc.frameworkUtil.ResponseUtil;
import com.nuc.frameworkUtil.ResultMessageUtil;

/**
 * Servlet implementation class SongAddServlet
 */
@WebServlet("/SongAddServlet")
public class SongAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongAddServlet() {
        super();
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
		/*SmartUpload upload = new SmartUpload();
		upload.initialize(this.getServletConfig(), request, response);
		String songImg = null;
		try {
			upload.upload();
			Files files = upload.getFiles();
			File file_image = files.getFile(1);
			Request req = upload.getRequest();
			songImg = file_image.getFileName();
			file_image.saveAs("C:/Users/chenyadi/Desktop/Music/Music/WebContent/songImg/"
				+ songImg);
		} catch (SmartUploadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		}*/
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String songName=request.getParameter("songName");
		String songType=request.getParameter("typeId");
		String songer=request.getParameter("songerId");
		String songWord=request.getParameter("songWord");
		//String songAlbum=request.getParameter("songAlbum");
		//songImg=request.getParameter("songImg");
		String songHeat=request.getParameter("songHeat")==null?"0":request.getParameter("songHeat");
		SongBiz songBiz=new SongBizImpl();
		SongsBean songsBean=null;
		String jsonStr=null;
		try {
			//songsBean=songBiz.addBeforeSong(songName,songType,songer,songWord,songHeat,songAlbum,songImg);
			songsBean=songBiz.addBeforeSong(songName,songType,songer,songWord,songHeat);
			songBiz.addSong(songsBean,request);
			jsonStr=ResultMessageUtil.returnJsonMessage(true, "添加歌曲成功！");
		} catch (Exception e) {
			log("增加歌曲异常："+e.getMessage()+"，歌曲信息："+songName);
			e.printStackTrace();
			jsonStr=ResultMessageUtil.returnJsonMessage(false, "添加歌曲失败！");
		}
		ResponseUtil.outJson(response, jsonStr);
	}

}
