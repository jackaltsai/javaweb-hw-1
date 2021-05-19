package web.member.service;

import web.member.bean.Member;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;

public class MemberService {
    private static MemberDao dao;
    
    public MemberService() {
        // 父介面 <- 子實作類別
        dao = new MemberDaoImpl();
    }
    
    public static Boolean memberLogin(Member member) {
        // 
        if (dao.checkMember(member)) {
            return true;
        }
        return false;
    }
}
