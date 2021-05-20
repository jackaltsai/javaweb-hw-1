package web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import web.member.bean.Member;
import web.member.service.MemberService;

@WebServlet("/member/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request & response 的編碼方式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new Gson();
		// 讀入JSON格式的會員資料
		try (
				// 取得用來從前端讀入純文字資料的Reader
				BufferedReader br = request.getReader();
				PrintWriter pw = response.getWriter();
			){
			// 讀入JSON格式的會員資料
			
	        Member member = gson.fromJson(br, Member.class);
	        
			if (MemberService.update(member) > 0) {
				String string = gson.toJson(member);
				pw.print(string);
			}else {
				System.out.println("null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
