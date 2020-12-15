package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.ReadArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "deleteArticleSuccess";
			
	private ReadArticleService readService = new ReadArticleService();
	private DeleteArticleService deleteArticleSvc = new DeleteArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);
			
			ArticleData articleData = readService.getArticle(no, false);
			User user = (User) req.getSession().getAttribute("authUser");
			if(!canDelete(user, articleData)) {// 내가 쓴 게 맞는지
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			DeleteRequest delReq = new DeleteRequest(user.getId(), no);
			deleteArticleSvc.delete(delReq);
			return FORM_VIEW ;
		} catch(ArticleNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	} 
		
	private boolean canDelete(User authUser, ArticleData articleData) {
		return articleData.getArticle().getWriter().getId().equals(authUser.getId());
	}
}
