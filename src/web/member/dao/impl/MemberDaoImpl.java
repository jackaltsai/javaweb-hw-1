package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import web.member.bean.Member;
import web.member.dao.MemberDao;

public class MemberDaoImpl implements MemberDao {
    private DataSource dataSource;
    
    public MemberDaoImpl() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int insert(Member member) {
        
        // 錯誤代碼 -1 回傳
        return -1;
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
    
    @Override
    public Boolean checkMember(Member member) {
        String sql = "select * from MEMBER where ACCOUNT = ? and PASSWORD = ?";
        
        try (
                // dataSource -> 由資料源產生的連線物件，在web環境下較常使用
                Connection connection = dataSource.getConnection();
                // sql: 欲對資料庫執行的SQL敘述. 可帶有多個問號，表示佔位符(placeholder)
                PreparedStatement pstm = connection.prepareStatement(sql);
        ) {
            // ?站位符 -> 塞入對應的型態, 值
            pstm.setString(1, member.getAccount());
            pstm.setString(2, member.getPassword());
            // 進行DQL
            ResultSet rs = pstm.executeQuery();
            // rs -> 結果集
            while (rs.next()) {
                // rs.get型態(“欄名”);
                member.setId(rs.getInt("ID"));
                member.setAccount(rs.getString("ACCOUNT"));
                member.setPassword(rs.getString("PASSWORD"));
                member.setNickname(rs.getString("NICKNAME"));
                member.setPass(rs.getBoolean("PASS"));
                member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
