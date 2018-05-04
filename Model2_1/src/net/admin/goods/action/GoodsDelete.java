package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;

public class GoodsDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsDelete execute()");
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.deleteGoods(num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}	
}