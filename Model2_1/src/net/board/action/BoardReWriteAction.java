package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardReWriteAction execute()");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int re_ref = Integer.parseInt(request.getParameter("re_ref"));
		int re_lev = Integer.parseInt(request.getParameter("re_lev"));
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		
		BoardBean bb = new BoardBean();
		bb.setName(name);
		bb.setPass(pass);
		bb.setSubject(subject);
		bb.setContent(content);
		bb.setRe_ref(re_ref);
		bb.setRe_lev(re_lev);
		bb.setRe_seq(re_seq);
		
		BoardDAO bdao = new BoardDAO();
		bdao.reInsertBoard(bb);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}	
}