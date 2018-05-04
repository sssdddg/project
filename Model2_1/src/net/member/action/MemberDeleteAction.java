package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.userCheck(id, pass);
		
		switch(check) {
		case 1:
			mdao.Delete_Member(id);
			session.invalidate();
			forward.setPath("./MemberLogin.me");
			forward.setRedirest(true);
			break;
		default :
			out.println("<script>");
			out.println("alert('��й�ȣ ����ġ')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		return forward;
	}

}
