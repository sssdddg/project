package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsModify execute()");
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		GoodsBean gbean = agdao.getGoods(num);
		request.setAttribute("gbean", gbean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
