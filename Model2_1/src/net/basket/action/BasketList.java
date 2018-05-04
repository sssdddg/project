package net.basket.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.goods.db.GoodsBean;
import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketList execute()");
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
			
		BasketDAO bdao = new BasketDAO();
		
		Vector vector = bdao.getBasketList(id);
		List<BasketBean> basketList = (List<BasketBean>)vector.get(0);
		List<GoodsBean> goodsList = (List<GoodsBean>)vector.get(1);
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		
		
		
		forward.setPath("./goods_order/goods_basket.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}