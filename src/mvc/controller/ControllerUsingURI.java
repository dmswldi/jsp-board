package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 */

public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String prefix = "/WEB-INF/view/";// 앞에 / 안 붙였다고 에러나냐 경로 똑같아 보이는데??????????
	private String suffix = ".jsp";
	private Map<String, CommandHandler> map;// 어차피 null, properties로 map 만들기<경로, 핸들러>
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerUsingURI() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {// properties에서 값 꺼내서 map 작성
    	map = new HashMap<>();
    	String configFilePath = getServletConfig().getInitParameter("configFile").trim();
    	
    	ServletContext application = getServletContext();
		String filePath = application.getRealPath(configFilePath);
		
		try (FileReader fr = new FileReader(filePath);){
			Properties properties = new Properties();
			properties.load(fr);
			
			Set<Object> keys =  properties.keySet();
			
			for(Object key : keys) {
				Object value = properties.get(key);
				String className = (String) value;
				
				try {
					Object o = Class.forName(className).newInstance();
					CommandHandler handler = (CommandHandler) o;
					
					map.put((String) key, handler);// key(/*.do)와 그 인스턴스 저장
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();// 요청 경로
		String root = request.getContextPath();
		
		String command = "";
		if(uri.startsWith(root)) {// /myboard 잘라내기
			command = uri.substring(root.length());			
		}
		
		CommandHandler handler = map.get(command);
		
		if(handler == null) {
			handler = new NullHandler();
		}
		
		/*
		if(command.equals("/join.do")) {
			handler = new JoinHandler();
		} else if(command.equals("/login.do")) {
			handler = new LoginHandler();
		} else if(command.equals("/logout.do")) {
			handler = new LogoutHandler();
		} else {
			handler = new NullHandler();
		}
		*/
		
		String view = null;
		try {
			view = handler.process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(view != null) {// method가 get | post일 때
			// 받은 view로 forwarding
			request.getRequestDispatcher(prefix + view + suffix).forward(request, response);			
		}
	}

}
