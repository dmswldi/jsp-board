package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import auth.service.User;
import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class RemoveMemberService {// 하나의 트랜잭션
	private MemberDao memberDao = new MemberDao();

	public void removeMember(User user, String password) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao.selectById(conn, user.getId());
			if(member == null) {
				throw new MemberNotFoundException();
			}
			if(!member.matchPassword(password)) {
				throw new InvalidPasswordException();
			}
			
			memberDao.delete(conn, user.getId());
			conn.commit();
			
		} catch (SQLException e) {
			jdbcUtil.rollback(conn);
		} finally {
			jdbcUtil.close(conn);
		}
	}
}
