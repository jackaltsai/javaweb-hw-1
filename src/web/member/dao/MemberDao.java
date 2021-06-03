package web.member.dao;


import java.util.List;

import com.google.gson.JsonObject;

import web.member.bean.Member;

public interface MemberDao {
    
    Boolean insert(JsonObject obj);
    
    int deleteById(Integer id);
    
    int update(Member member);
    
    Member selectById(Integer id);
    
    List<Member> selectAll();
    
    Boolean checkMember(Member member);
}
