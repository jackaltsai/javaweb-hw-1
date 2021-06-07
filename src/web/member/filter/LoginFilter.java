package web.member.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.member.bean.Member;

@WebFilter("/member/html/login.html")
public class LoginFilter extends HttpFilter {
    private static final long serialVersionUID = 1L;

    public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 抓取Session
        HttpSession session = request.getSession();
	    // 一定撰寫此行，Servlet才會接續處理
        super.doFilter(request, response, chain);
        
        // 判斷是否已經登入 如果有就跳轉到index.html
        if (session.getAttribute("isLogin") != null && (Boolean)session.getAttribute("isLogin")) {
            
            // 讀取權限
            if (session.getAttribute("permission") == null) {
                return;
            }
            // 判斷權限
            switch ((String) session.getAttribute("permission")) {
            case "user":
                // 使用重新導向的方式轉發
                response.sendRedirect(request.getContextPath() + "/member/html/homeMember.html");
                break;
                
            case "admin":
                // 使用重新導向的方式轉發
                response.sendRedirect(request.getContextPath() + "/index.html");
                break;

            default:
                break;
            }
        }
    }
}
