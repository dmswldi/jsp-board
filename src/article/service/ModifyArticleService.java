package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;

public class ModifyArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, modReq.getArticleNumber());
			if(article == null) {
				throw new ArticleNotFoundException();
			}
			if(!canModify(modReq.getUserId(), article)) {
				throw new PermissionDeniedException();
			}
			// update 함수 실행된 쿼리 row수 int로 리턴, 사용 x ...?
			// update()를 void로 바꾸거나 여기서 리턴된 int 활용해서 에러 날리는 게 낫겠죠..?
			articleDao.update(conn, modReq.getArticleNumber(), modReq.getTitle());
			contentDao.update(conn, modReq.getArticleNumber(), modReq.getContent());
			conn.commit();
		} catch(SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(PermissionDeniedException e) {
			jdbcUtil.rollback(conn);
			throw e;
		} finally {
			jdbcUtil.close(conn);
		}
	}
	
	private boolean canModify(String modifyingUserId, Article article) {
		return article.getWriter().getId().equals(modifyingUserId);
	}
}
