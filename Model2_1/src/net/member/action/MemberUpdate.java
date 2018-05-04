package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String Email = request.getParameter("email");
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.userCheck(id, pass);
		
		MemberBean mb =  new MemberBean();
		mb.setId(id);
		mb.setPass(pass);
		mb.setName(name);
		mb.setEmail(Email);
		
		switch(check) {
		case 1:
			mdao.Update_Member(mb);
			forward.setPath("./Main.me");
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
