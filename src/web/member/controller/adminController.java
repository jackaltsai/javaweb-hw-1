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

@WebServlet("/admin")
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		// 讀入JSON格式的會員資料 驗證會員
		try (
				// 取得用來從前端讀入純文字資料的Reader
				PrintWriter pw = resp.getWriter();
			){
				// JSON格式寫出
				String stringMember = new Gson().toJson(Member.getInstance());
				pw.print(stringMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
