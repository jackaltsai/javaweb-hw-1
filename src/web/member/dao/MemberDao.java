package web.member.dao;

import java.util.List;

import web.member.bean.Member;

public interface MemberDao {
    
    int insert(Member member);
    
    int deleteById(Integer id);
    
    int update(Member member);
    
    Member selectById(Integer id);
    
    List<Member> selectAll();
}
