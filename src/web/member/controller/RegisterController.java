package web.member.controller;

import java.io.BufferedReader;
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

@WebServlet("/member/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// request & response 的編碼方式
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");

		// 讀入JSON格式的會員資料
		try (
				// 取得用來從前端讀入純文字資料的Reader
				BufferedReader br = request.getReader();
				PrintWriter pw = response.getWriter();) {
			// 讀入JSON格式的會員資料
			Member member = Member.getInstance();
			member = gson.fromJson(br, Member.class);

			if (MemberService.insert(member)) {
				String string = new Gson().toJson(member);
				pw.print(string);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
