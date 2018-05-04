package net.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasketFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	/BasketAdd.ba => BasketAdd 자바파일
		//	BasketAdd(basketbean)
		//	세션값 가져오기. 없으면 "./MemberLogin.me"
		//	b_m_id <= 세션 자바빈 변수 저장
		//	같은 상품이면 수량증가
		//	checkGoods(basketbean)
		//	check 1이 아니면 새로운 상품 추가 basketAdd(basketbean)
		//	이동 ./BasketList.ba
		//
		//	/BasketList.ba => BasketList 자바
		//	=> getBasketList(id) aptjem
		//	./goods_order/goods_basket.jsp
		//	이동
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("뽑아온 가상주소 : "+command);
		
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/BasketAdd.ba")){
			action = new BasketAdd();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BasketList.ba")){
			action = new BasketList();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(command.equals("/BasketDelete.ba")){
			action = new BasketDelete();
			try{
				forward = action.execute(request, response);
			}catch(Exception e){
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
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}