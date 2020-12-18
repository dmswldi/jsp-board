package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reply.model.Reply;

public class ReplyDao {
	
	public List<Reply> listReply(Connection conn, int articleNum) throws SQLException {
		String sql = "SELECT replyid, memberid, article_no, body, regdate "
				+ "FROM reply "
				+ "WHERE article_no = ? "
				+ "ORDER BY replyid DESC";
		
		List<Reply> list = new ArrayList<>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, articleNum);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Reply r = new Reply(rs.getInt("replyid"), 
						rs.getString("memberid"),
						rs.getInt("article_no"), 
						rs.getString("body"), 
						rs.getTimestamp("regdate"));
				list.add(r);
			}
		}
		return list;
	}

	
	public void insert(Connection conn, String userId, int articleNo, String body) throws SQLException {
		String sql = "INSERT INTO reply "
				+ "(memberid, article_no, body, regdate) "
				+ "VALUES (?, ?, ?, SYSDATE)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, userId);
			pstmt.setInt(2, articleNo);
			pstmt.setString(3, body);
			
			pstmt.executeUpdate();
		}
	}


	public int delete(Connection conn, int replyid) throws SQLException {
		String sql = "DELETE reply "
				+ "WHERE replyid = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, replyid);
			
			return pstmt.executeUpdate();
		}
		
	}
	
	public int update(Connection conn, int replyid, String body) throws SQLException {
		String sql = "UPDATE reply "
				+ "SET body = ? "
				+ "WHERE replyid = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, body);
			pstmt.setInt(2, replyid);
			
			return pstmt.executeUpdate();
		}
	}

}
