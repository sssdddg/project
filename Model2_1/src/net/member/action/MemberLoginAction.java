package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction login()");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.userCheck(id, pass);
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check == 1) {
			session.setAttribute("id", id);
			forward.setPath("./Main.me");
			forward.setRedirest(true);
		} else {
			out.println("<script>");
			out.println("alert('�α��ν���')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}
	
}
