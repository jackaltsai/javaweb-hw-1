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
	private DataSource ds;
	
	public MemberDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Override
    public int insert(Member member) {
    	final String sql = "insert into MEMBER_Test(ACCOUNT,PASSWORD,NICKNAME) values (?,?,?)";
//		Member member2 = new Member();
		try (
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)
			){
//				pstmt.setString(1, member2.getAccount());
//				pstmt.setString(2, member2.getPassword());
//				pstmt.setString(3, member2.getNickname());
			pstmt.setString(1, "Emilia tan");
			pstmt.setString(2, "test123");
			pstmt.setString(3, "emt");
				return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
        // 錯誤代碼 -1 回傳
        return -1;
    }

    @Override
    public int deleteById(Integer id) {
    	final String sql = "delete from MEMBER where ID = ?";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				pstmt.setInt(1, id);
				return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
    	final String sql = "select * from MEMBER where ID = ?";
		try (
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				pstmt.setInt(1, id);
				try (
						ResultSet rs = pstmt.executeQuery()
					){
					if (rs.next()) {
						Member member = new Member();
						member.setId(rs.getInt("ID"));
						member.setAccount(rs.getString("ACCOUNT"));
						member.setPassword(rs.getString("PASSWORD"));
						member.setPass(rs.getBoolean("PASS"));
						member.setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
						return member;
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

    @Override
    public List<Member> selectAll() {
        
        return null;
    }

}
