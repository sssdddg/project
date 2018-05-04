package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("MemberInfo execute()");
			request.setCharacterEncoding("utf-8");
			ActionForward forward = new ActionForward();
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			//	���ǰ� ��������
			MemberDAO mdao = new MemberDAO();
			MemberBean mb = mdao.info_member(id);
			//	MemberDAO mdao ��ü����
			//	MemberBean mb = getMember(���ǰ�) �żҵ� ȣ��
			
			//	request ����
			request.setAttribute("mb", mb);
			//	�̵� 	./member/info.jsp
			forward.setPath("./member/info.jsp");
			forward.setRedirest(false);
		
		return forward;
	}

}
