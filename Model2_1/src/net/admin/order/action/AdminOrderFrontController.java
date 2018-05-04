package net.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.action.Action;
import net.admin.order.action.ActionForward;

public class AdminOrderFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소 : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/AdminOrderList.ao")) {
			action = new AdminOrderList();
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
 		}else if(command.equals("/AdminOrderDetail.ao")) {
 			action = new AdminOrderDetail();
 			try {
 				forward = action.execute(request, response);
 			} catch(Exception e) {
 				e.printStackTrace();
 			}
 		}else if(command.equals("/AdminOrderModify.ao")) {
 			action = new AdminOrderModify();
 			try {
 				forward = action.execute(request, response);
 			} catch(Exception e) {
 				e.printStackTrace();
 			}
 		}
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
