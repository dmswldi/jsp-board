package auth.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.command.CommandHandler;

public class LogoutHandler implements CommandHandler {
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(false);// session이 없으면 null 리턴
		if(session != null) {
			session.invalidate();// 세션 종료
		}
		res.sendRedirect(req.getContextPath() + "/index.jsp");// redirect 되고 forward 되면 에러남!!! 코드로 처리해주기
		return null;
	}
}
