package net.admin.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.order.db.AdminOrderDAO;

public class AdminOrderList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("AdminOrderList execute()");
		request.setCharacterEncoding("utf-8");
		
		AdminOrderDAO aoDAO = new AdminOrderDAO();
		List adminOrderList = aoDAO.getAdminOrderList();
		request.setAttribute("adminOrderList", adminOrderList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./adminorder/admin_order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
