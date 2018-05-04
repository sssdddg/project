package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsModifyAction execute()");
		request.setCharacterEncoding("utf-8");
		
		GoodsBean gbean = new GoodsBean();
		gbean.setNum(Integer.parseInt(request.getParameter("num")));
		gbean.setCategory(request.getParameter("category"));
		gbean.setName(request.getParameter("name"));
		gbean.setContent(request.getParameter("content"));
		gbean.setSize(request.getParameter("size"));
		gbean.setColor(request.getParameter("color"));
		gbean.setAmount(Integer.parseInt(request.getParameter("amount")));
		gbean.setPrice(Integer.parseInt(request.getParameter("price")));
		gbean.setBest(Integer.parseInt(request.getParameter("best")));
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.modifyGoods(gbean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
