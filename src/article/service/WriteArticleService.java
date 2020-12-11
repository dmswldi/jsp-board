package article.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;

public class WriteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public Integer write(WriteRequest req) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = toArticle(req);
			Article savedArticle = articleDao.insert(conn, article);

			if(savedArticle == null) {
				throw new RuntimeException("fail to insert article");
			}
			
			ArticleContent content = new ArticleContent(
					savedArticle.getNumber(),// foreign key 역할
					req.getContent());
			ArticleContent savedContent = contentDao.insert(conn, content);
			
			if(savedContent == null) {
				throw new RuntimeException("fail to insert article_content");
			}
			
			conn.commit();
			return savedArticle.getNumber();
					
		} catch(SQLException e){
			jdbcUtil.rollback(conn);// 어떤 식으로든 여기서 throw하면 호출한 애가 잡거나 throw하거나 처리해줘야 하는 거 아닌가?
			throw new RuntimeException(e);// 여기서write() throw | RuntimeException 만들어서 throw
			// 이 메소드write() 부른 애는 RuntimeException을 처리해도 되고, 안 해도 됨. 안 하면 끝까지 자동 throw
		} catch(RuntimeException e) {
			jdbcUtil.rollback(conn);
			throw e;
		} finally {
			jdbcUtil.close(conn);
		}
		
	}
	
	private Article toArticle(WriteRequest req) {
		return new Article(null,
				req.getWriter(),
				req.getTitle(),
				null,
				null,
				0
				);
	}
	
}
