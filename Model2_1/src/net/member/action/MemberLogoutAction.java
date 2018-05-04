package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = (String)session.getAttribute("id");
		session.invalidate();
		
		out.println("<script>");
		out.println("alert('�α׾ƿ��Ǿ����ϴ�.')");
		out.println("location.href='./Main.me'");
		out.println("</script>");
		out.close();
		
		
		return forward;
	}

}
