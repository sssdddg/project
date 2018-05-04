package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardContent implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardContent execute()");
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bdao = new BoardDAO();
		bdao.updateReadcount(num);
		BoardBean bb = bdao.getBoard(num);
		request.setAttribute("bb", bb);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./board/content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}