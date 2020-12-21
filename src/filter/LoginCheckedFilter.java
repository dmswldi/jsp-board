package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginCheckedFilter
 */
public class LoginCheckedFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckedFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;// 강제 형변환
		HttpServletResponse response = (HttpServletResponse) res;
		
		HttpSession session = request.getSession(false);// 세션 있으면 얻어와
		
		// 로그아웃 상태
		if(session == null || session.getAttribute("authUser") == null) {
			session.setAttribute("link", request.getRequestURI());
			response.sendRedirect(request.getContextPath() + "/login.do");
		} else {
			// 로그인 상태
			chain.doFilter(request, response);
		}				
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
