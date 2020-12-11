package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auth.service.User;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import member.service.RemoveMemberService;
import mvc.command.CommandHandler;

public class RemoveMemberHandler implements CommandHandler {
	private static final String FORM_VIEW = "removeMemberForm";
	private RemoveMemberService removeMemberSvc = new RemoveMemberService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//
			return null;
		}
		
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String password = req.getParameter("password");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		if(password == null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}// 여기서 matchPassword 확인? X -> DB의 정보와 일치하는지 확인해야 하기 때문에 Service에서 !!
		if(! errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authUser");// filter에서 이미 null, empty 체크함
		
		try {
			removeMemberSvc.removeMember(user, password);
			session.invalidate();// 멤버 삭제하고 -> 세션 무효화 ..!!!!!
			
			return "removeMemberSuccess";
		} catch (InvalidPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW;
		} catch (MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		
	}
}
