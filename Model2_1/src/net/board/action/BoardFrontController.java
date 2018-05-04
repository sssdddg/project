package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//가상주소 뽑아오기	http://localhost:8080/Model2/BoardWrite.bo
		String requestURI = request.getRequestURI();		//  /Model2/BoardWrite.bo 뽑아옴
		String contextPath = request.getContextPath();		//	/Model2	뽑아옴
		String command = requestURI.substring(contextPath.length());	//	contextPath길이 이후부터 뽑아옴
		System.out.println("뽑아온 가상주소 : "+command);
		
		//뽑아온 주소 비교
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/BoardWrite.bo")){
			//	./board/writeForm.jsp
			forward = new ActionForward();
			forward.setPath("./board/writeForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardWriteAction.bo")){
			//	BoardWriteAction 파일 만들기, Action 상속
			//	부모 = 자식 객체생성
			action = new BoardWriteAction();
			
			//	메서드 호출
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardList.bo")){
			action = new BoardList();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardContent.bo")){
			action = new BoardContent();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardUpdate.bo")){
			action = new BoardUpdate();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardUpdateAction.bo")){
			action = new BoardUpdateAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardDelete.bo")){
			forward = new ActionForward();
			forward.setPath("./board/delete.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardFileWrite.bo")){
			forward = new ActionForward();
			forward.setPath("./board/fwriteForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardFileWriteAction.bo")){
			action = new BoardFileWriteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardReWrite.bo")){
			forward = new ActionForward();
			forward.setPath("./board/reWriteForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardReWriteAction.bo")){
			action = new BoardReWriteAction();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		//이동
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}