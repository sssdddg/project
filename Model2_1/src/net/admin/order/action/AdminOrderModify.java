package net.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;
import net.order.db.OrderBean;

public class AdminOrderModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderModify execute()");
		request.setCharacterEncoding("utf-8");
		
		OrderBean orderbean = new OrderBean();
		orderbean.setO_trade_num(request.getParameter("trade_num"));
		orderbean.setO_status(Integer.parseInt(request.getParameter("status")));
		orderbean.setO_trans_num(request.getParameter("trans_num"));
		
		AdminOrderDAO aoDAO = new AdminOrderDAO();
		aoDAO.updateOrder(orderbean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);
		return forward;
	}

}
