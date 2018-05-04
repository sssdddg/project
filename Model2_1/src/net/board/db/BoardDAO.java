package net.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO {
	
	private Connection getConnection() throws Exception {
//		Class.forName("com.mysql.jdbc.Driver");
//		
//		String dbUrl = "jdbc:mysql://localhost:3306/jspdb2";
//		String dbUser = "jspid";
//		String dbPass = "jsppass";
//		Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
//		
//		return con;
		
		//커넥션 풀(Connection Pool)
		//- 데이터베이스와 연결된 Connection 객체를 미리 생성하여 풀(Pool)속에 저장해 두고 필요할 때마다
		//	이 풀에 접근하여 Connection 객체를 사용하고, 작업이 끝나면 다시 반환하는 것
		//	커넥션 재사용 가능, 프로그램 효율.성능 증가
		//자카르타 DBCP API - 아파치톰캣 7버전 이상 내장되어있음
		//http://commons.apach.org
		//commons-collections.zip
		//commons-dbcp.zip
		//commons-pool.zip
		
		//1.WebContent/META-INF/context.xml
		//	디비연결 자원 저장
		//2.WebContent/WEB-INF/web.xml
		//	자원을 모든 페이지에 알려줌
		//3.BoardDAO 사용
		
		//context.xml 파일을 불러오기 위한 파일 객체 생성
		Context init = new InitialContext();
		//자원의 이름 불러오기 	자원위치:java:comp/env, 자원이름:jdbc/Mysql
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		Connection con = ds.getConnection();
		
		return con;
	}
	
	public void insertBoard(BoardBean bb) {
		
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		
		try{
			con = getConnection();
			
			sql = "select max(num) as num from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				num = rs.getInt("num")+1;
			}
			
			sql = "insert into board(num,name,pass,subject,content,file,re_ref,re_lev,re_seq,readcount,date) values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setString(6, bb.getFile());	//file
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);	//readcount
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();} catch(SQLException ex){}
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();} catch(SQLException ex){}
		}
	}
	
	public int getBoardCount(){
		int count = 0;
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			
			sql = "select count(*) as num from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt("num");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();} catch(SQLException ex){}
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();} catch(SQLException ex){}
		}
		
		return count;
	}
	
	public List<BoardBean> getBoardList(int startRow, int pageSize){
		List<BoardBean> list = new ArrayList<BoardBean>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean bb = null;
		
		try{
			con = getConnection();
			
			sql = "select * from board order by re_ref desc, re_seq limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setName(rs.getString("name"));
				bb.setPass(rs.getString("pass"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setFile(rs.getString("file"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setDate(rs.getDate("date"));
				list.add(bb);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();} catch(SQLException ex){}
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();} catch(SQLException ex){}
		}
		
		return list;
	}
	
	public BoardBean getBoard(int num){
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean bb = null;
		
		try{
			con = getConnection();
			
			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bb = new BoardBean();
				bb.setNum(rs.getInt("num"));
				bb.setPass(rs.getString("pass"));
				bb.setName(rs.getString("name"));
				bb.setContent(rs.getString("content"));
				bb.setDate(rs.getDate("date"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setSubject(rs.getString("subject"));
				bb.setFile(rs.getString("file"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null) try{rs.close();} catch(SQLException ex){}
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();} catch(SQLException ex){}
		}
		
		return bb;
	}
	
	public void updateReadcount(int num){
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getConnection();
			
			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null) try{pstmt.close();} catch(SQLException ex){}
			if(con != null) try{con.close();} catch(SQLException ex){}
		}
	}
	
	public int updateBoard(BoardBean bb){
		int check = 0;
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			
			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("pass").equals(bb.getPass())){
					check = 1;
					sql = "update board set name=?,subject=?,content=? where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, bb.getName());
					pstmt.setString(2, bb.getSubject());
					pstmt.setString(3, bb.getContent());
					pstmt.setInt(4, bb.getNum());
					pstmt.executeUpdate();
				}else{
					check = -1;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
		
		return check;
	}
	
	public int deleteBoard(int num, String pass){
		int resultNum = 0;
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			
			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			rs.next();
			if(rs.getString("pass").equals(pass)){
				sql = "delete from board where num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				resultNum = 1;
				
			}else{
				resultNum = -1;
			}
			
			return resultNum;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
		return resultNum;
	}
	
	public void reInsertBoard(BoardBean bb){
		int num = 0;
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt("max(num)")+1;
			}
			
			//답글순서 재배치
			//변경 re_seq+1 조건 같은 그룹이고 순서값 자기 자신값보다 큰 값
			sql = "update board set re_seq=re_seq+1 where re_ref=? and re_seq>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getRe_ref());
			pstmt.setInt(2, bb.getRe_seq());
			pstmt.executeUpdate();
			
			//insert re_ref re_lev+1 re_seq+1
			sql = "insert into board(num,name,pass,subject,content,file,re_ref,re_lev,re_seq,readcount,date) values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setString(6, bb.getFile());
			pstmt.setInt(7, bb.getRe_ref());
			pstmt.setInt(8, bb.getRe_lev()+1);
			pstmt.setInt(9, bb.getRe_seq()+1);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
	}
}