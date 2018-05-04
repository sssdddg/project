package net.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsList implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsList execute()");
		request.setCharacterEncoding("utf-8");
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		int count = agdao.getGoodsCount();
		
		int pageSize = 10;
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null){
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		List<GoodsBean> goodsList = null;
		
		if(count != 0){
			goodsList = agdao.getGoodsList(startRow, pageSize);
		}
		
		int pageCount = count/pageSize+(count%pageSize==0? 0:1);
		int pageBlock = 3;
		int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage+pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		
		request.setAttribute("count", count);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}