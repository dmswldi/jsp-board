package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	private MemberDao memberDao = new MemberDao();
	
	public User login(String id, String password) {
		try(Connection conn = ConnectionProvider.getConnection()){
			Member member = memberDao.selectById(conn, id);
			if(member == null) {// 해당 id 없거나
				throw new LoginFailException();
			}
			if(!member.matchPassword(password)) {// 비번 틀리면 login fail
				throw new LoginFailException();
			}
			return new User(member.getId(), member.getName());
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
