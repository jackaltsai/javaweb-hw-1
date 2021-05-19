package web.member.service;

import web.member.bean.Member;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;

public class MemberService {
    // 父介面 <- 子實作類別
    private static MemberDao dao = new MemberDaoImpl();
    
    public static Boolean memberLogin(Member member) {
        // 
        if (dao.checkMember(member)) {
            return true;
        }
        return false;
    }
    
    public int insert(Member member) {
    	return dao.insert(member);
    }
}
