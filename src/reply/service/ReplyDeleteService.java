package reply.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import reply.dao.ReplyDao;

public class ReplyDeleteService {
	private ReplyDao replyDao = new ReplyDao();
	
	public void delete(int articleNo) {
		try(Connection conn = ConnectionProvider.getConnection()){
			int cnt = replyDao.delete(conn, articleNo);
			if(cnt == 0) {
				throw new RuntimeException("fail to delete");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
