package article.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import article.model.Article;
import article.model.Writer;
import jdbc.jdbcUtil;

public class ArticleDao {
	
	public Article insert(Connection conn, Article article) throws SQLException {
		String sql = "INSERT INTO article "
				+ "(writer_id, writer_name, title, regdate, moddate, read_cnt) "
				+ "VALUES (?, ?, ?, SYSDATE, SYSDATE, 0)";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//pstmt = conn.prepareStatement(sql, 1);// 그냥 1 써도 되지 않나?
			
			pstmt = conn.prepareStatement(sql, new String[] {"article_no", "regdate", "moddate"});// 자동 생성 키 return 할 수 있는 pstmt 객체 생성
			// 내가 key 받아서 쓰지 않으면 걍 쿼리 실행하면 되고 db 조회하면 기본키 값이 있는 거지?
			// 이렇게 안 하면 그냥 기본키 안 받고 쿼리 실행하고 다시 select해서 조회해야 되고 
			pstmt.setString(1, article.getWriter().getId());
			pstmt.setString(2, article.getWriter().getName());
			pstmt.setString(3, article.getTitle());
			
			int cnt = pstmt.executeUpdate();
			//pstmt.executeUpdate();// 두 번 하면 두 개 만들어지네 근데 두 번째 결과 출력됨
			
			if(cnt == 1) {// insert가 됐으면
				rs = pstmt.getGeneratedKeys();// statement 실행된 결과로 만들어진 자동 생성 키 검색
				
				int key = 0;
				Date regDate = null;
				Date modDate = null;
				if(rs.next()) {
					key = rs.getInt(1);
					regDate = rs.getTimestamp(2);
					modDate = rs.getTimestamp(3);
				}
				return new Article(key,// 무조건 여기서 key 받아서 리턴해야 함. 그래야 게시물 분간 가능
						article.getWriter(),
						article.getTitle(),
						regDate, // 새로 만든 값 넣어줘야지
						modDate, 
						0);
			} else {
				return null;
			}
			
		} finally {
			jdbcUtil.close(rs, pstmt);
		}
		
	}
	
	
	public int selectCount(Connection conn) throws SQLException {// 게시글 수 조회
		String sql = "SELECT COUNT(*) FROM article";
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt  = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} finally {
			jdbcUtil.close(rs, stmt);
		}
	}
	
	
	public List<Article> select(Connection conn, int pageNum, int size) throws SQLException {// pageNum부터 size(5)개
		String sql = "SELECT "
				+ "rn, "
				+ "article_no, "
				+ "writer_id, "
				+ "writer_name, "
				+ "title, "
				+ "regdate, "
				+ "moddate, "
				+ "read_cnt "
				+ "FROM ("
				+ "	SELECT article_no, "
				+ " 	   writer_id, "
				+ "        writer_name, "
				+ "        title, "
				+ "        regdate, "
				+ "        moddate, "
				+ "        read_cnt, "
				+ "        ROW_NUMBER() "
				+ "          OVER ("
				+ "            ORDER BY "
				+ "            article_no "
				+ "            DESC)"
				+ "        rn "
				+ "  FROM article "
				+ ") WHERE rn "
				+ "    BETWEEN ? AND ?";// 최신 글부터 보기
		/*
		String mysql = "SELECT * "
				+ "FROM article "
				+ "ORDER BY article_no DESC "
				+ "LIMIT ?, ? ";// ?부터 ?개
		*/
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNum-1) * size + 1);// mysql: 0번 base, oracle: 1번 base	1 6 11 / 1 11 
			pstmt.setInt(2, pageNum * size);// pageNum부터 size개	5 10 15 / 10 20
			
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			
			return result;
		} finally {
			jdbcUtil.close(rs, pstmt);
		}
	}
	
	private Article convertArticle(ResultSet rs) throws SQLException {
		return new Article(rs.getInt("article_no"),
				new Writer(
						rs.getString("writer_id"), 
						rs.getString("writer_name")
						),
						rs.getString("title"),
						rs.getTimestamp("regdate"),
						rs.getTimestamp("moddate"),
						rs.getInt("read_cnt"));
	}
	
	public Article selectById(Connection conn, int no) throws SQLException {
		String sql = "SELECT * FROM article "
				+ "WHERE article_no = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Article article = null;
			if(rs.next()) {
				article = convertArticle(rs);
			}
			return article;
		} finally {
			jdbcUtil.close(rs, pstmt);
		}
	}
	
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		String sql = "UPDATE article SET read_cnt = read_cnt + 1 "
				+ "WHERE article_no = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}
	}
	
	public int update(Connection conn, int no, String title) throws SQLException {
		String sql = "UPDATE article "
				+ "SET title = ?, moddate = SYSDATE "
				+ "WHERE article_no = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, title);
			pstmt.setInt(2, no);
			
			int cnt = pstmt.executeUpdate();
			return cnt;
		}
	}


	public int delete(Connection conn, int articleNumber) throws SQLException {
		String sql = "DELETE article "
				+ "WHERE article_no = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setInt(1, articleNumber);
			// sql에 작성하지 않고 PreparedStatement 쓰는 이유가 뭐라고 하셨죠. 악용 금지...?
			return pstmt.executeUpdate();
		}
		
	}
	
	
	// 추가
	public int mySelectCount(Connection conn, Writer writer) throws SQLException {// 내가 쓴 총 게시글 수
		String sql = "SELECT COUNT(*) FROM article "
				+ "WHERE writer_id = ? "
				+ "AND writer_name = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, writer.getId());
			pstmt.setString(2, writer.getName());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			} else {
				return 0;
			}
		} finally {
			jdbcUtil.close(rs, pstmt);
		}
	}
	
	public List<Article> mySelect(Connection conn, int pageNum, int size, Writer writer) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "article_no, "
				+ "writer_id, "
				+ "writer_name, "
				+ "title, "
				+ "regdate, "
				+ "moddate, "
				+ "read_cnt "
				+ "FROM ("
				+ "	SELECT article_no, "
				+ " 	   writer_id, "
				+ "        writer_name, "
				+ "        title, "
				+ "        regdate, "
				+ "        moddate, "
				+ "        read_cnt, "
				+ "        ROW_NUMBER() "
				+ "          OVER ("
				+ "            ORDER BY "
				+ "            article_no "
				+ "            DESC)"
				+ "        rn "
				+ "  FROM article "
				+ "	WHERE writer_id = ? "
				+ "	AND writer_name = ?"
				+ ") WHERE rn "
				+ "    BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer.getId());
			pstmt.setString(2, writer.getName());
			pstmt.setInt(3, (pageNum-1) * size + 1);// 1 11 
			pstmt.setInt(4, pageNum * size);// pageNum부터 size개	10 20
			
			rs = pstmt.executeQuery();
			List<Article> result = new ArrayList<>();
			while(rs.next()) {
				result.add(convertArticle(rs));
			}
			
			return result;
		} finally {
			jdbcUtil.close(rs, pstmt);
		}
	}
	
}
