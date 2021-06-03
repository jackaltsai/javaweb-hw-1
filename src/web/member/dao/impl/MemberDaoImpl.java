package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.google.gson.JsonObject;
import web.member.bean.Member;
import web.member.dao.MemberDao;

public class MemberDaoImpl implements MemberDao {
    private DataSource dataSource;
    public static String SQLerror = null;
    public MemberDaoImpl() {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    @Override
    public Boolean insert(JsonObject obj) {
    	final String sql = "insert into MEMBER (ACCOUNT,PASSWORD,NICKNAME) values (?,?,?)";
    	String[] generatedColumns = {"ID"};
		try (
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql,generatedColumns);
			){
			
			pstmt.setString(1, obj.get("account").getAsString());
			pstmt.setString(2, obj.get("password").getAsString());
			pstmt.setString(3, obj.get("nickname").getAsString());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			while (rs.next()) {
				Member.getInstance().setId(rs.getInt(1));
			}
			
		return true;
		} catch (Exception e) {
			//e.printStackTrace();
			SQLerror = "帳號已經存在";
		}
        // 錯誤代碼 -1 回傳
        return false;
    }

    @Override
    public int deleteById(Integer id) {
    	final String sql = "delete from MEMBER where ID = ?";
		try (
				Connection conn = dataSource.getConnection();
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
    
    public int update(Member member) {
		final String sql = "update MEMBER set PASSWORD = ?, NICKNAME = ?,LAST_UPDATE_DATE = ? where ID = ?";
		try(
			Connection conn = dataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getNickname());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			//TODO session
			pstmt.setInt(4, 1);
			System.out.println(member.getId());
			return pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

    @Override
    public Member selectById(Integer id) {
    	final String sql = "select * from MEMBER where ID = ?";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				pstmt.setInt(1, id);
				try (
						ResultSet rs = pstmt.executeQuery()
					){
					if (rs.next()) {
						Member.getInstance().setId(rs.getInt("ID"));
						Member.getInstance().setAccount(rs.getString("ACCOUNT"));
						Member.getInstance().setPassword(rs.getString("PASSWORD"));
						Member.getInstance().setPass(rs.getBoolean("PASS"));
						Member.getInstance().setNickname(rs.getString("NICKNAME"));
						Member.getInstance().setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
						return Member.getInstance();
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
    	final String sql = "select * from MEMBER";
		try (
				Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
			){
				List<Member> list = new ArrayList<Member>();	
				while (rs.next()) {
					Member.getInstance().setId(rs.getInt("ID"));
					Member.getInstance().setAccount(rs.getString("ACCOUNT"));
					Member.getInstance().setPassword(rs.getString("PASSWORD"));
					Member.getInstance().setPass(rs.getBoolean("PASS"));
					Member.getInstance().setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
					list.add(Member.getInstance());
				}
				return list;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
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
                Member.getInstance().setId(rs.getInt("ID"));
                Member.getInstance().setAccount(rs.getString("ACCOUNT"));
                Member.getInstance().setPassword(rs.getString("PASSWORD"));
                Member.getInstance().setNickname(rs.getString("NICKNAME"));
                Member.getInstance().setPass(rs.getBoolean("PASS"));
                Member.getInstance().setLastUpdateDate(rs.getTimestamp("LAST_UPDATE_DATE"));
                Member.getInstance().setPermission(rs.getString("PERMISSION"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
