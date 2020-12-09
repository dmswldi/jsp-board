package jdbc;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCInitListener
 *
 */
@WebListener// 자동으로 리스너로 등록됨
public class JDBCInitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JDBCInitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         ServletContext application = sce.getServletContext();
         
         String url = application.getInitParameter("jdbcUrl");
         String user = application.getInitParameter("jdbcUser");
         String pw = application.getInitParameter("jdbcPassword");

         // 1. 클래스 로딩: 1번만 하면 됨 !!!!!
         try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
         
         // 2. driverManager에서 connection
         // 3. close();
         /* Just test!
         try (Connection con = DriverManager.getConnection(url, user, pw);){
        	 System.out.println("연결 잘 됨");
         } catch(Exception e) {
        	 e.printStackTrace();
         }
         */
         
         // 미리 url, user, pw 설정해놓기 -> 나중에 편하게 메소드로 커넥션 얻기 위해!
         // ConnectionProvider.getConnection(); 이제 db 얻을 때 커넥션부터 얻으면 됨
         ConnectionProvider.setUrl(url);
         ConnectionProvider.setUser(user);
         ConnectionProvider.setPassword(pw);
         
         // context root 경로
         String contextPath = application.getContextPath();
         application.setAttribute("root", contextPath);
    }
	
}
