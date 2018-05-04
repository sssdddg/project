package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;
import net.goods.db.GoodsDAO;

public class BasketAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BasketAdd execute()");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
		
		BasketBean bBean = new BasketBean();
		bBean.setB_m_id(id);
		bBean.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		bBean.setB_g_color(request.getParameter("color"));
		bBean.setB_g_size(request.getParameter("size"));
		bBean.setB_g_num(Integer.parseInt(request.getParameter("num")));
		
		BasketDAO bdao = new BasketDAO();
		int check = bdao.checkGoods(bBean);
		if(check != 1){
			bdao.basketAdd(bBean);
		}
		
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);

		return forward;
	}	
}