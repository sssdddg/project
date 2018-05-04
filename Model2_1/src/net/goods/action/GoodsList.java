package net.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.goods.db.GoodsDAO;

public class GoodsList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsList execute()");
		request.setCharacterEncoding("utf-8");
		String item = request.getParameter("item");
		if(item == null){
			item = "all";
		}
		
		GoodsDAO gdao = new GoodsDAO();
		List goodsList = gdao.getGoodsList(item);
		request.setAttribute("goodsList", goodsList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}	
}