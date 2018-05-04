package net.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.GoodsBean;
import net.goods.db.GoodsDAO;

public class GoodsDetail implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GoodsDetail execute()");
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsBean gBean = gdao.getGoods(num);
		
		request.setAttribute("gBean", gBean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
