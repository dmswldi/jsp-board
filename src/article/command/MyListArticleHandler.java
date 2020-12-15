package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.ArticlePage;
import article.service.MyListArticleService;
import auth.service.User;
import mvc.command.CommandHandler;

public class MyListArticleHandler implements CommandHandler {
	MyListArticleService mylistArticleSvc = new MyListArticleService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		User authUser = (User) req.getSession().getAttribute("authUser");
		Writer writer = new Writer(authUser.getId(), authUser.getName());
		ArticlePage articlePage = mylistArticleSvc.getArticlePage(pageNo, writer);
		req.setAttribute("articlePage", articlePage);
		return "mylistArticle";
	}
}
