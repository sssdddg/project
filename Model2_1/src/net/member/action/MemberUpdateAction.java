package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("MemberUpdateAction execute()");
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.info_member(id);
		
		request.setAttribute("mb", mb);
		
		forward.setPath("./member/update.jsp");
		forward.setRedirest(false);
		
		return forward;
	}

}
