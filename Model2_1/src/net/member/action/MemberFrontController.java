package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oracle.jrockit.jfr.RequestableEvent;

public class MemberFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doProcess()");
		//		http://localhost:8080/Model2/MemberJoin.me
		//		URI			/Model2/MemberJoin.me
		//		URI			/Model2/MemberJoinAction.me
		String requestURI = request.getRequestURI();
		System.out.println("URI�ּ� : "+requestURI);
		String contextPath = request.getContextPath();
		System.out.println("���ؽ�Ʈ��� : "+contextPath);
		System.out.println("���ؽ�Ʈ��� ���� : "+contextPath.length());
		//								/MemberJoin.me
		String command = requestURI.substring(contextPath.length());
		System.out.println("�̾ƿ� �����ּ� ��� : "+command);
		//	�ּ� ��
		ActionForward forward = null;
		Action action = null;
		if(command.equals("/MemberJoin.me")) {
				//	�����ּ���ġ(������ġ)	./member/insertForm.jsp �̵�
				//	1.	response �̵�
				//	response.sendRedirect("./member/insertForm.jsp");
				//	2.	RequestDispatcher forword() �̵�
				//	�ּ��� A -> ȭ�� B , A request ���� ������ -> B�̵�
				//	RequestDispatcher dispatcher 
				//	= request.getRequestDispatcher("./member/insertForm.jsp");
				//	dispatcher.forward(request, response);
			forward = new ActionForward();
			//	�̵���� ���� / ��� true - respnse , false - forward
			forward.setRedirest(false);
			forward.setPath("./member/insertForm.jsp");
			//	�̵���� ����
		} else if(command.equals("/MemberJoinAction.me")) {
			//	insertPro ó���۾� => Java���� �żҵ�()
			//	Java���� MemberJoinAction ��ü ���� , �żҵ�ȣ��
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setRedirest(false);
			forward.setPath("./member/Login.jsp");
		} else if(command.equals("/MemberLoginAction.me")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/Main.me")) {
			forward = new ActionForward();
			forward.setRedirest(false);
			forward.setPath("./member/Main.jsp");
		} else if(command.equals("/MemberLogoutAction.me")) {
			action = new MemberLogoutAction();
			try {
			forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberInfoAction.me")) {
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	else if(command.equals("/MemberUpdateAction.me")) {
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberUpdate.me")) {
			action = new MemberUpdate();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberDelete.me")) {
			forward = new ActionForward();
			forward.setRedirest(false);
			forward.setPath("./member/delete.jsp");
		} else if(command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//	�̵�
		if(forward != null) {
		if(forward.isRedirest()) {
			response.sendRedirect(forward.getPath());
		} else {
			RequestDispatcher dispatcher 
			= request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
			} // if-else end
		} // if end
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doGet()");
		doProcess(request , response);
	}

	@Override
	protected void doPost(HttpServletRequest  request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController doPost()");
		doProcess(request , response);
	}

}
