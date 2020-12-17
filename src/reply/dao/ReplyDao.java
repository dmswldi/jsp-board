package reply.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reply.model.Reply;

public class ReplyDao {

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

	public int delete(Connection conn, int articleNo) throws SQLException {
		String sql = "DELETE reply "
				+ "WHERE article_no = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, articleNo);
			return pstmt.executeUpdate();
		}
		
	}

}
