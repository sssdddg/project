package net.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.order.db.OrderBean;
import net.order.db.OrderDAO;

public class OrderList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderList execute()");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("id");
		OrderDAO odao = new OrderDAO();
		List<OrderBean> orderList = odao.getOrderList(id);
		request.setAttribute("orderList", orderList);
		
		forward.setPath("./goods_order/order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
