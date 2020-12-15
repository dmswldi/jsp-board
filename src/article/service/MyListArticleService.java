package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import article.model.Writer;
import jdbc.ConnectionProvider;

public class MyListArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;// 한 페이지에 보이는 게시물 수
	
	public ArticlePage getArticlePage(int pageNum, Writer writer) {// : 현재 페이지
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			
			int total = articleDao.mySelectCount(conn, writer);// 내가 쓴 총 게시글 수
			List<Article> content = articleDao.mySelect(conn, pageNum, size, writer);
			return new ArticlePage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
