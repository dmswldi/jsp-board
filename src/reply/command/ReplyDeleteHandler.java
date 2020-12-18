package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import reply.service.ReplyDeleteService;

public class ReplyDeleteHandler implements CommandHandler {
	private ReplyDeleteService replyDelSvc = new ReplyDeleteService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {// 추가 exception 처리
		String no = req.getParameter("no");
		int articleNo = Integer.parseInt(no);// 안 위험? exception 처리 필요?
		String id = req.getParameter("id");
		int replyid = Integer.parseInt(id);
		
		replyDelSvc.delete(replyid);
		
		res.sendRedirect(req.getContextPath() + "/article/read.do?no=" + articleNo);
		return null;
	}
}
