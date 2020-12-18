package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;
import reply.dao.ReplyDao;

public class ReplyAddService {
	private ReplyDao replyDao = new ReplyDao();
	
	public void add(String userId, int articleNo, String body) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			replyDao.insert(conn, userId, articleNo, body);
		
			conn.commit();
		} catch(SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
		
	}

}
