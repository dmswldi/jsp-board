package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;
import reply.dao.ReplyDao;

public class ReplyDeleteService {
	private ReplyDao replyDao = new ReplyDao();
	
	public void delete(int replyid) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
	
			int cnt = replyDao.delete(conn, replyid);
			if(cnt == 0) {
				throw new RuntimeException("fail to delete");
			}
			
			conn.commit();
		} catch(SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}
	}
}
