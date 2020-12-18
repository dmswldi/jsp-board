package reply.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;
import reply.dao.ReplyDao;

public class ReplyModifyService {
	private ReplyDao replyDao = new ReplyDao();

	public void update(int replyid, String body) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int cnt = replyDao.update(conn, replyid, body);
			if(cnt == 0) {
				jdbcUtil.rollback(conn);
				throw new RuntimeException("fail to update");
			}
			
			conn.commit();
		} catch(SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
		
		
	}

}
