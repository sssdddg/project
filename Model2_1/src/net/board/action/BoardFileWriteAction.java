package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardFileWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardFileWriteAction execute()");
		request.setCharacterEncoding("utf-8");
		
		//파일업로드 방식
		//1.특정 폴더에 업로드 파일 저장, 디비 업로드된 파일이름 저장
		//2.DB에 파일을 바로 저장

		//파일업로드할 프로그램 설치
		//www.servlets.com	com.oreilly.servlet
		//cos-26Dec2008.zip		cos.jar => lib 넣기

		//파일업로드 프로그램 MultipartRequest객체 생성 => 파일업로드, request정보를 저장
		//(request,업로드할 폴더 물리적경로,업로드할 파일크기,한글처리,업로드할 파일이름이 동일할 경우 파일이름 변경)
		//폴더만들기 WebContent/upload
		String realPath = request.getRealPath("/upload");
		System.out.println("upload폴더 물리적경로 : "+realPath);
		//업로드할 파일크기
		int maxSize = 5*1024*1024;	//5Mb
		MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		BoardBean bb = new BoardBean();		//객체생성 BoardBean bb
		//폼 파라미터 => 자바빈 변수 저장
		bb.setContent(multi.getParameter("content"));
		bb.setName(multi.getParameter("name"));
		bb.setPass(multi.getParameter("pass"));
		bb.setSubject(multi.getParameter("subject"));
		//시스템에 업로드된 파일의 이름을 저장
		bb.setFile(multi.getFilesystemName("file"));

		//DB작업 파일 만들기 => 패키지 board, 파일이름 BoardDAO
		BoardDAO bdao = new BoardDAO();	//객체생성 BoardDAO bdao
		bdao.insertBoard(bb);	//메서드호출 insertBoard(bb)		max(num) => 넘버값중에 가장 큰 값 구해서 +1한 값을 insert	날짜는 now()함수 이용
				
		ActionForward forward = new ActionForward();
		forward.setPath("./BoardList.bo");
		forward.setRedirect(true);
		
		return forward;
	}
}