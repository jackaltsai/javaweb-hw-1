package web.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.member.bean.Member;
import web.member.service.MemberService;

@WebServlet("/member/update")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new Gson();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request & response 的編碼方式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		
		try (
				// 取得用來從前端讀入純文字資料的Reader
				BufferedReader br = request.getReader();
				PrintWriter pw = response.getWriter();
			){
			
			// 讀入JSON格式的會員資料
	        Member member = GSON.fromJson(br, Member.class);
			if (MemberService.update(member) > 0) {
				Member.getInstance().setNickname(member.getNickname());
				Member.getInstance().setPassword(member.getPassword());
				String string = GSON.toJson(member);
				System.out.println(string + "<-------------updateController");
				pw.print(string);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
