package net.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {
	
	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/Mysql");
		Connection con = ds.getConnection();
		
		return con;
	}
	
	public void insertGoods(GoodsBean gbean){
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		
		try{
			con = getConnection();
			
			sql = "select max(num) from goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}
			
			sql = "insert into goods(num,category,name,content,size,color,amount,price,image,best,date) values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, gbean.getCategory());
			pstmt.setString(3, gbean.getName());
			pstmt.setString(4, gbean.getContent());
			pstmt.setString(5, gbean.getSize());
			pstmt.setString(6, gbean.getColor());
			pstmt.setInt(7, gbean.getAmount());
			pstmt.setInt(8, gbean.getPrice());
			pstmt.setString(9, gbean.getImage());
			pstmt.setInt(10, gbean.getBest());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
	}
	
	public int getGoodsCount(){
		int count = 0;
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getConnection();
			
			sql = "select count(*) from goods";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
		
		return count;
	}
	
	public List<GoodsBean> getGoodsList(int startRow, int pageSize){
		List<GoodsBean> list = new ArrayList<GoodsBean>();
		String sql = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsBean gbean = null;
		
		try{
			con = getConnection();
			
			sql = "select * from goods order by num desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				gbean = new GoodsBean();
				gbean.setNum(rs.getInt("num"));
				gbean.setCategory(rs.getString("category"));
				gbean.setName(rs.getString("name"));
				gbean.setContent(rs.getString("content"));
				gbean.setSize(rs.getString("size"));
				gbean.setColor(rs.getString("color"));
				gbean.setAmount(rs.getInt("amount"));
				gbean.setPrice(rs.getInt("price"));
				gbean.setImage(rs.getString("image"));
				gbean.setBest(rs.getInt("best"));
				gbean.setDate(rs.getDate("date"));
				list.add(gbean);
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs!=null) try{rs.close();} catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();} catch(SQLException ex){}
			if(con!=null) try{con.close();} catch(SQLException ex){}
		}
		
		return list;
	}
	
	public void modifyGoods(GoodsBean goodsbean){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql  num 에 해당하는
			//category name price color amount size best content수정
			sql="update goods set category=?,name=?,price=?,color=?,amount=?,size=?,best=?,content=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, goodsbean.getCategory());
			pstmt.setString(2, goodsbean.getName());
			pstmt.setInt(3, goodsbean.getPrice());
			pstmt.setString(4, goodsbean.getColor());
			pstmt.setInt(5, goodsbean.getAmount());
			pstmt.setString(6, goodsbean.getSize());
			pstmt.setInt(7, goodsbean.getBest());
			pstmt.setString(8, goodsbean.getContent());
			pstmt.setInt(9, goodsbean.getNum());
			//4실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	//deleteGoods(int num)
	public void deleteGoods(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql num해당하는 상품 삭제
			sql="delete from goods where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	//getGoods(int num)
	public GoodsBean getGoods(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		GoodsBean goodsbean=null;
		try {
			//1,2 디비연결
			con=getConnection();
			//3 sql num에 해당하는 상품정보 가져오기
			sql="select * from goods where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			//4 rs 실행 저장
			rs=pstmt.executeQuery();
			//5 데이터 있으면 자바빈객체 생성
			// rs => 자바빈 저장 
			if(rs.next()){
				goodsbean=new GoodsBean();
				goodsbean.setAmount(rs.getInt("amount"));
				goodsbean.setBest(rs.getInt("best"));
				goodsbean.setCategory(rs.getString("category"));
				goodsbean.setColor(rs.getString("color"));
				goodsbean.setContent(rs.getString("content"));
				goodsbean.setDate(rs.getDate("date"));
				goodsbean.setImage(rs.getString("image"));
				goodsbean.setName(rs.getString("name"));
				goodsbean.setNum(rs.getInt("num"));
				goodsbean.setPrice(rs.getInt("price"));
				goodsbean.setSize(rs.getString("size"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return goodsbean;
	}
}
