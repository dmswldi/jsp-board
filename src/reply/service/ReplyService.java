package reply.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import reply.dao.ReplyDao;
import reply.model.Reply;

public class ReplyService {
	private ReplyDao replyDao = new ReplyDao();
	
	public List<Reply> getReplyList(int articleNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			List<Reply> list = replyDao.listReply(conn, articleNum);
			return list;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
