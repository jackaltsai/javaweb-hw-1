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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import web.member.bean.Member;
import web.member.service.MemberService;

@WebServlet("/member/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
			
			Member member = Member.getInstance();
			
			JsonObject obj = new Gson().fromJson(br, JsonObject.class);
			JsonElement element = obj.get("account");

			// 讀入JSON格式的會員資料
			System.out.println(element.getAsString());
			
			if (MemberService.register(obj)) {
				obj.addProperty("pass",true);
				String string = new Gson().toJson(obj);
				pw.print(string);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
