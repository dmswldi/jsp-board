package reply.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import reply.service.ReplyDeleteService;

public class ReplyDeleteHandler implements CommandHandler {
	private ReplyDeleteService replyDelSvc = new ReplyDeleteService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String no = req.getParameter("no");
		int articleNo = Integer.parseInt(no);
		
		replyDelSvc.delete(articleNo);
		
		req.getRequestDispatcher("${root }/article/read.do?no=" + articleNo).forward(req, res);
		return null;
	}
}
