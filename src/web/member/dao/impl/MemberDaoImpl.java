package web.member.dao.impl;

import java.util.List;

import web.member.bean.Member;
import web.member.dao.MemberDao;

public class MemberDaoImpl implements MemberDao {

    @Override
    public int insert(Member member) {
        
        // 錯誤代碼 -1 回傳
        return 0;
    }

    @Override
    public int deleteById(Integer id) {
        
        // 錯誤代碼 -1 回傳
        return -1;
    }

    @Override
    public int update(Member member) {
        
        // 錯誤代碼 -1 回傳
        return -1;
    }

    @Override
    public Member selectById(Integer id) {
        
        return null;
    }

    @Override
    public List<Member> selectAll() {
        
        return null;
    }

}
