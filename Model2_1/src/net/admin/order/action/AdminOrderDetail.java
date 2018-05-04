package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;
import net.order.db.OrderBean;

public class AdminOrderDetail implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrerDetail execute()");
		request.setCharacterEncoding("utf-8");
		String trade_num = request.getParameter("trade_num");
		
		AdminOrderDAO aoDAO = new AdminOrderDAO();
		List adminOrderDetail = aoDAO.getAdminOrderDetail(trade_num);
		
		request.setAttribute("adminOrderDetail", adminOrderDetail);
		ActionForward forward = new ActionForward();
		forward.setPath("./adminorder/admin_order_modify.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
