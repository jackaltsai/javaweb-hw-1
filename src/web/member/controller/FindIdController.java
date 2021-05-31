package web.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.member.bean.Member;
import web.member.service.MemberService;


/**
 * Servlet implementation class FindIdController
 */
@WebServlet("/member/FindIdController")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson GSON = new Gson();  
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		//TODO session									//取得共享資料 
//		int id = (int) request.getSession().getAttribute("ID");
		
		Member memeberId = MemberService.selectById(1);
		
		//轉成json輸出到前端
		try (PrintWriter pw = response.getWriter()){
			String jsonStr = GSON.toJson(memeberId);
//			System.out.println(jsonStr);
			pw.print(jsonStr);
				
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}
