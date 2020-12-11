package article.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import article.model.Article;
import article.service.ArticlePage;
import jdbc.ConnectionProvider;

public class ListArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private int size = 5;// 한 페이지에 5쪽씩 보이게            수정 맞나 이거 10으로 해도 되는 건지 이해해보자
	
	public ArticlePage getArticlePage(int currentPage) {// : 현재 페이지
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			int total = articleDao.selectCount(conn);
			//List<Article> content = articleDao.select(conn, ??, size);
			//return new ArticlePage(total, currentPage, size, content);
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
