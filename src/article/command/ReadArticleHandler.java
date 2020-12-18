package article.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleContentNotFoundException;
import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.ReadArticleService;
import mvc.command.CommandHandler;
import reply.model.Reply;
import reply.service.ReplyService;

public class ReadArticleHandler implements CommandHandler {
	private ReadArticleService readService = new ReadArticleService();
	private ReplyService replyService = new ReplyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int articleNum = Integer.parseInt(noVal);
		
		try {// article 상세정보 읽을 때
			ArticleData articleData = readService.getArticle(articleNum, true);
			List<Reply> replyList = replyService.getReplyList(articleNum);// 해당 article의 댓글 얻어서
			req.setAttribute("articleData", articleData);
			req.setAttribute("replyList", replyList);// req에 담음
			return "readArticle";
		} catch(ArticleNotFoundException | ArticleContentNotFoundException e) {
			req.getServletContext().log("no article", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
}
}
