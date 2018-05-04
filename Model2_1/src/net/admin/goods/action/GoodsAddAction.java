package net.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsAddAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// upload 폴더 만들기
		// MultipartRequest multi 객체생성
		// GoodsBean gbean 자바빈 객체생성
		// set 메서드 호출 <= 파라미터 가져오기
		// setImage <= 파일,파일,파일,파일
		// AdminGoodsDAO agdao 객체생성
		// insertGoods(gbean) 메서드
		// 이동 ./GoodsList.ag
		System.out.println("GoodsAddAction execute()");
		request.setCharacterEncoding("utf-8");
		
		String realPath = request.getRealPath("./upload");
		System.out.println("upload폴더 물리적 경로 : "+realPath);
		
		int maxSize = 5*1024*1024;
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		
		GoodsBean gbean = new GoodsBean();
		gbean.setName(multi.getParameter("name"));
		gbean.setPrice(Integer.parseInt(multi.getParameter("price")));
		gbean.setColor(multi.getParameter("color"));
		gbean.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gbean.setSize(multi.getParameter("size"));
		gbean.setContent(multi.getParameter("content"));
		gbean.setCategory(multi.getParameter("category"));
		gbean.setImage(multi.getFilesystemName("file1")+","+multi.getFilesystemName("file2")+","+multi.getFilesystemName("file3")+
				","+multi.getFilesystemName("file4"));
		
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.insertGoods(gbean);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
