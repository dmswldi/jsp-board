package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.DuplicateIdException;
import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "joinForm";
	private JoinService joinService = new JoinService();
	
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	// get 방식일 때
	public String processForm(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return FORM_VIEW;
	}
	
	// post 방식일 때 : 가입 성공
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		JoinRequest joinReq = new JoinRequest();
		joinReq.setId(req.getParameter("id"));
		joinReq.setName(req.getParameter("name"));
		joinReq.setPassword(req.getParameter("password"));
		joinReq.setConfirmPassword(req.getParameter("confirmPassword"));
		
		// 파라미터에 값 잘 들어있는지 확인
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		joinReq.validate(errors);
		
		if(!errors.isEmpty()) {// 입력 사항에 에러 있으면
			return FORM_VIEW;
		}
		
		try {
			joinService.join(joinReq);
			return "joinSuccess";
		} catch(DuplicateIdException e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}
		
	}
	
	
	
}
