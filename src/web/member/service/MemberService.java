package web.member.service;

import com.google.gson.JsonObject;

import web.member.bean.Member;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;

public class MemberService {
	// 父介面 <- 子實作類別
    private static MemberDao dao = new MemberDaoImpl();

    public static Boolean memberLogin(Member member) {
        // 檢查 member狀態(登出中) & 帳號存在是否DB
        if (!Member.getInstance().getStatus() && dao.checkMember(member)) {
            // 改變登入狀態
            Member.getInstance().setStatus(true);
            return true;
        }
        return false;
    }
    
    public static Boolean memberLogout() {
        // 檢查 member狀態(登入中)
        if (Member.getInstance().getStatus()) {
            // 登出
            Member.getInstance().clear();
            return true;
        }
        return false;
    }
    
    public static Boolean register(JsonObject obj) {
    	
    	if (dao.insert(obj)) {
			return true;
		}
    	return false;
    }
    
    public static int update(Member member) {
		return dao.update(member);
	}
    
    public static Member selectById(Integer id) {
    	return dao.selectById(id);
    }
}
