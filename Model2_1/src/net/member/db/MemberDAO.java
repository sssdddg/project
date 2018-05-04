package net.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	Connection con = null;
	String sql = "";
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private Connection getConnection() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		String dbid = "jspid";
		String dbpass = "jsppass";
		String dburl = "jdbc:mysql://localhost:3306/jspdb2";
		Connection con = DriverManager.getConnection(dburl , dbid , dbpass);
		
		return con;
		}
	
	
	public void insertMember(MemberBean mb) throws SQLException {
		
		try {
			
			con = getConnection();
			sql = "insert into member values (? , ? , ? , now() , ? , ? , ? , ? , ?) ";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getName());
			pstmt.setString(4, mb.getEmail());
			pstmt.setString(5, null);
			pstmt.setString(6, null);
			pstmt.setString(7, null);
			pstmt.setInt(8, 0);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ex) {	}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ex) {	}
			}
		}
		
	} //	insertMember End
	
	public MemberBean info_member(String id) throws SQLException {

		MemberBean mb = null;
		
		try {
			
			con = getConnection();
			sql = "select * from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setEmail(rs.getString("email"));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	
			if(rs != null) { try { rs.close(); } catch(SQLException ex) {	}}
			if(pstmt != null) {try {	pstmt.close();	} catch(SQLException ex) {	}}
			if(con != null) {try { con.close(); } catch(SQLException ex) {	}}
		}
		return mb;
		
	} // info_member End
	
	public void Update_Member(MemberBean mb) throws SQLException {
		
		try {
			
				con = getConnection();
			
				sql = "update member set name = ? , email = ? where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getName());
				pstmt.setString(2, mb.getEmail());
				pstmt.setString(3, mb.getId());
				pstmt.executeUpdate();
						
			
		} catch (Exception e) {
			
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ex) {	}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ex) {	}
			}
		}
		
	} // Update_Member End
	
	public void Delete_Member(String id) throws SQLException {
		
		try {
			
			con = getConnection();
			
			sql = "delete from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {

		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ex) {	}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ex) {	}
			}
		}
	} //Delete_Member End
	
	public List<MemberBean> ListMember() throws SQLException {
		
		List<MemberBean> list = new ArrayList<MemberBean>();
		
		try {
			
			con = getConnection();
			sql = "select * from member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setName(rs.getString("name"));
				mb.setReg_date(rs.getTimestamp("reg_date"));
				mb.setAge(rs.getInt("age"));
				mb.setGender(rs.getString("gender"));
				mb.setEmail(rs.getString("email"));
				list.add(mb);
			}
			
		} catch(Exception e) {
			
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch(SQLException ex) {	}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch(SQLException ex) {	}
			}
			if(con != null) {
				try {
					con.close();
				} catch(SQLException ex) {	}
			}
		}
		
		return list;
		
	}
	
	public int userCheck(String id , String pass) throws SQLException {

		int check = 1;

		try {

			con = getConnection();
			sql = "select pass from member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("pass"))){
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1 ;
			}	
			
		} catch (Exception e) {
			System.out.println("�������");
			e.printStackTrace();
		} finally {
			if(pstmt != null) { try { pstmt.close();	} catch(SQLException ex) {	}}
			if(con != null) { try {	con.close();	} catch(SQLException ex) {	}}
		}
		
		return check;
		
	} // userCheck End

}
