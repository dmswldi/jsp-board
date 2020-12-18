package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.ReplyAddService;

public class ReplyAddHandler implements CommandHandler {
	private ReplyAddService addService = new ReplyAddService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");
		
		String userId = user.getId();
		int articleNo = Integer.parseInt(req.getParameter("no"));
		String body = req.getParameter("body");
		addService.add(userId, articleNo, body);
		
		// 다시 readArticle로 forward, 댓글 보이게 테이블로!
		res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
		return null;
	}
}
