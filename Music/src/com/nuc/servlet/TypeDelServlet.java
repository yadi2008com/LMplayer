package com.nuc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nuc.biz.SongBiz;
import com.nuc.biz.SongBizImpl;
import com.nuc.biz.TypeBiz;
import com.nuc.biz.TypeBizImpl;

/**
 * Servlet implementation class TypeDelServlet
 */
@WebServlet("/TypeDelServlet")
public class TypeDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TypeDelServlet() {
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
		String typeIdS = request.getParameter("typeId");
		if(null != typeIdS && !typeIdS.equals("")){
			int typeId =  Integer.parseInt(typeIdS);
			TypeBiz typeBiz=new TypeBizImpl();
			//无相关歌曲为false,有相关为true
			boolean checkRelsong = typeBiz.checkRelsong(typeId);
			System.out.println(checkRelsong);
			if(!checkRelsong){
				boolean res = typeBiz.deleteTypeById(typeId);
				if(!res){
					System.out.println("类型删除失败");
				}
				request.getRequestDispatcher("/Admin/type_list.jsp?success=yes").forward(request, response);
			}else{
				//response.sendRedirect("Admin/type_list.jsp?error=yes");
				request.getRequestDispatcher("/Admin/type_list.jsp?error=yes").forward(request, response);
			}
			
		}
		
	}

}
