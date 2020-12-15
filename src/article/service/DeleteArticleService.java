package article.service;

import java.sql.Connection;
import java.sql.SQLException;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import jdbc.ConnectionProvider;
import jdbc.jdbcUtil;

public class DeleteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();
	
	public void delete(DeleteRequest delReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Article article = articleDao.selectById(conn, delReq.getArticleNumber());
			String userId = delReq.getUserId();
			if(article == null) {
				throw new ArticleNotFoundException();// 얘는??? 던지냐 마냐는 처리 선택사항인가요
			}
			if(!canDelete(userId, article)) {
				throw new PermissionDeniedException();
			}
			
			articleDao.delete(conn, delReq.getArticleNumber());
			contentDao.delete(conn, delReq.getArticleNumber());
			conn.commit();
		} catch(SQLException e) {
			jdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch(PermissionDeniedException e) {// 왜 얘만 던져주나요??? 
			jdbcUtil.rollback(conn);
			throw e;
		} finally {
			jdbcUtil.close(conn);
		}
	}
	
	private boolean canDelete(String deletingUserId, Article article){
		return article.getWriter().getId().equals(deletingUserId);
	}
}
