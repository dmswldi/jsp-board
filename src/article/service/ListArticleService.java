package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;

public class ListArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private int size = 10;// 한 페이지에 보이는 게시물 수
	
	public ArticlePage getArticlePage(int pageNum) {// : 현재 페이지
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			int total = articleDao.selectCount(conn);// 게시글 수
			List<Article> content = articleDao.select(conn, pageNum, size);// pageNum?? 1, 2, 3, ... ???
			return new ArticlePage(total, pageNum, size, content);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
