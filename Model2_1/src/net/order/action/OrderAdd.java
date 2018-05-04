package net.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.goods.db.GoodsBean;
import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;
import net.goods.db.GoodsDAO;
import net.member.db.MemberBean;
import net.member.db.MemberDAO;
import net.order.db.OrderBean;
import net.order.db.OrderDAO;

public class OrderAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("OrderAdd execute()");
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		OrderDAO odao = new OrderDAO();
		OrderBean orderbean = new OrderBean();
		BasketDAO bdao = new BasketDAO();
		MemberDAO mdao = new MemberDAO();
		
		Vector vector = bdao.getBasketList(id);
		MemberBean memberbean = mdao.info_member(id);
		
		List<BasketBean> basketList = (List<BasketBean>)vector.get(0);
		List<GoodsBean> goodsList = (List<GoodsBean>)vector.get(1);
		
		orderbean.setO_m_id(id);
		orderbean.setO_receive_name(request.getParameter("o_receive_name"));
		orderbean.setO_receive_phone(request.getParameter("o_receive_phone"));
		orderbean.setO_receive_mobile(request.getParameter("o_receive_mobile"));
		orderbean.setO_receive_addr1(request.getParameter("o_receive_addr1"));
		orderbean.setO_receive_addr2(request.getParameter("o_receive_addr2"));
		orderbean.setO_memo(request.getParameter("o_memo"));
		orderbean.setO_trade_payer(request.getParameter("o_trade_payer"));
		
		odao.addOrder(orderbean, basketList, goodsList);
		//	주문한 아이디 , 배송지 정보 , 결제정보 = > 주문테이블 저장
		
		GoodsDAO gdao = new GoodsDAO();
		gdao.updateAmount(basketList);
		// 상품테이블 총수량 - 주문수량 차감
		bdao.deleteBasket(id);
		//	주문완료시 사용자 장바구니 정보 삭제
		
		forward.setPath("./OrderList.or");
		forward.setRedirect(true);
		
		return forward;
	}

}
