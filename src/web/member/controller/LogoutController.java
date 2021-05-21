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

@WebServlet("/member/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // request & response 的編碼方式
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        
        // 清除 Member 物件
        System.out.println("登出前 帳號: " + Member.getInstance().getAccount());
        if (MemberService.memberLogout()) {
            System.out.println("登出成功");
        }
        System.out.println("登出後 帳號: " + Member.getInstance().getAccount());
        // 
        try (
                // 取得用來從前端讀入純文字資料的Reader
                BufferedReader br = request.getReader();
                PrintWriter pw = response.getWriter();
        ) {
            // JSON格式寫出(測試)
            //String string = new Gson().toJson(member);
            //pw.print(string);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
