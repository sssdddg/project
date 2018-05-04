package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.basket.db.BasketDAO;

public class BasketDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketDelete execute()");
		request.setCharacterEncoding("utf-8");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		
		BasketDAO bdao = new BasketDAO();
		bdao.basketDelete(b_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		return forward;
	}	
}