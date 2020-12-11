package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.dao.ArticleContentDao;
import article.dao.ArticleDao;
import article.model.Article;
import article.model.ArticleContent;
import article.model.Writer;
import jdbc.ConnectionProvider;

/**
 * Servlet implementation class ArticleInsertTestServlet
 */
@WebServlet("/testArticleInsert1")
public class ArticleInsertTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleInsertTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = new Article(
				0,
				new Writer("a", "b"),
				"title1",
				null,
				null,
				0
				);
		ArticleDao dao = new ArticleDao();
		
		try(Connection conn = ConnectionProvider.getConnection()){
			Article n = dao.insert(conn, article);
			if(n != null) {
				System.out.println("key: " + n.getNumber());				
				System.out.println("reg: " + n.getRegDate());				
				System.out.println("mod: " + n.getModifiedDate());				
			} else {
				System.out.println("article null임");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
