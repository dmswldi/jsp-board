package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	private MemberDao memberDao = new MemberDao();
	
	public void join(JoinRequest joinReq) {// 하나의 트랜잭션
		Connection con = null; // 다른 블럭? try with resource에서 () {}는 다른 블럭?
		// 같은 커넥션 내에서 commmit | rollback -> connection 공유
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);// autoCommit : DDL, DML 끝나거나 | DQL resultSet이 닫히면 commit
			
			// 같은 아이디 멤버 있는지 확인, DB 조회(select)
			Member m = memberDao.selectById(con, joinReq.getId());
			
			if(m != null) {// 같은 id 존재
				// jdbcUtil.rollback(con); select는 rollback 할 필요 X
				throw new DuplicateIdException();// 사용자 정의 익셉션
			}
			Member member = new Member();
			member.setId(joinReq.getId());
			member.setName(joinReq.getName());
			member.setPassword(joinReq.getPassword());
			
			memberDao.insert(con, member);
			
			con.commit();// 커밋해주기!!
			
		} catch(SQLException e) {
			jdbcUtil.rollback(con);
			throw new RuntimeException(e);
		} finally {
			jdbcUtil.close(con);
		}
	}
}
