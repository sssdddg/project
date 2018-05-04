package net.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberJoinAction execute()");
		//	�ѱ�ó��
		request.setCharacterEncoding("utf-8");
		//	JavaBean MemberBean mb ��ü ����
		MemberBean mb = new MemberBean();
		mb.setId(request.getParameter("id"));
		mb.setPass(request.getParameter("pass"));
		mb.setName(request.getParameter("name"));
		mb.setEmail(request.getParameter("email"));
		//	JavaBean �ɹ� ���� <= �� �Ķ���� �����ͼ� ����
		MemberDAO mdao = new MemberDAO();
		//	MemberDAO mdao ��ü ����
		mdao.insertMember(mb);
		//	InsertMember(mb) �żҵ� ȣ��()
		ActionForward forward = new ActionForward();
		//	�̸� ActionForward ��ü ����
		forward.setPath("./MemberLogin.me");
		forward.setRedirest(true);
		//	��� , �������
		return forward;
	}
	
}
