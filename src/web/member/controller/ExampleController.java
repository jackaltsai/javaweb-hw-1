package web.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.service.MemberService;

// @WebServlet("/ExampleController")
@WebServlet("member/example")
public class ExampleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Service層
    private MemberService memberService;

    // 實例化 (GenericServlet() 父代)
    @Override
    public void init() throws ServletException {
        super.init();
        
        memberService = new MemberService();
    }
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	}

}
