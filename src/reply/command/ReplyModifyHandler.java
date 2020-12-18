package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import mvc.command.CommandHandler;
import reply.service.ReplyModifyService;

public class ReplyModifyHandler implements CommandHandler {
	private ReplyModifyService replyModSvc = new ReplyModifyService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {// 추가 exception 처리
		String id = req.getParameter("id");
		int replyid = Integer.parseInt(id);
		String no = req.getParameter("no");
		int articleNo = Integer.parseInt(no);
		String body = req.getParameter("body");
		
		replyModSvc.update(replyid, body);
		
		res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
		return null;
	}
}
