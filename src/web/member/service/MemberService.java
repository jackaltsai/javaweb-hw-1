package web.member.service;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;

public class MemberService {
    private MemberDao dao;
    
    public MemberService() {
        // 父介面 <- 子實作類別
        dao = new MemberDaoImpl();
    }
}
