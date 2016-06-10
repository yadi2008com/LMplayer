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
import com.nuc.bean.SingersBean;
import com.nuc.biz.SingerBiz;
import com.nuc.biz.SingerBizImpl;
import com.nuc.frameworkUtil.ResponseUtil;

/**
 * Servlet implementation class SingerListJsonServlet
 */
@WebServlet("/SingerListJsonServlet")
public class SingerListJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingerListJsonServlet() {
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
		SingerBiz singerBiz=new SingerBizImpl();
		/*int pageno=1;
		if(request.getParameter("pageno")!=null){
		String	pagenoString=request.getParameter("pageno");
		pageno=Integer.parseInt(pagenoString);
		}
		//得到歌手的信息行数
		int getSingerInfoRows=singerBiz.getSingerInfoRows();
		int maxpage=getSingerInfoRows%PageBean.ROWS_PRE_PAGE==0?getSingerInfoRows/PageBean.ROWS_PRE_PAGE:(getSingerInfoRows/PageBean.ROWS_PRE_PAGE+1);
		if(pageno<1){
									pageno=1;
		}else if(pageno>maxpage){
									pageno=maxpage;
		}*/
		List<SingersBean> singersBeanInfoList =singerBiz.fetchAllSingersInfoList();
		/*PageBean pageBean=new PageBean();
		pageBean.setMaxpage(maxpage);
		pageBean.setPageno(pageno);*/
		/*request.setAttribute("result", getSingerInfoRows);
		request.setAttribute("singersBeanInfoList",singersBeanInfoList);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/Admin/singer_list.jsp").forward(request, response);*/
		Map<String,Object> maps=new HashMap<String,Object>();
		/*maps.put("getSingerInfoRows",getSingerInfoRows);
		maps.put("pageBean",pageBean);*/
		maps.put("singersBeanInfoList",singersBeanInfoList);
		String  jsonStr=JSONArray.toJSONString(maps);
		ResponseUtil.outJson(response,jsonStr);
	}

}
