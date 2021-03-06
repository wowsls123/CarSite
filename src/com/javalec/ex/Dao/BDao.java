package com.javalec.ex.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.ex.Dto.BDto;
import com.javalec.ex.Dto.BDto2;
import com.javalec.ex.Dto.BDto3;
import com.javalec.ex.Dto.BDto4;
import com.javalec.ex.Dto.BDto5;
import com.javalec.ex.Dto.BDto6;



public class BDao {
	
	DataSource datasource;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public BDao() {
		try {
			Context ctx = new InitialContext();
			datasource = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int login(String member_id, String member_pw) {
		
	
		
		
		
		try {
			conn = datasource.getConnection();
			String query = "select member_pw from Car_member where member_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			
			if(rs.next())
			{
				if(rs.getString("member_pw").equals(member_pw)) {
					System.out.println("pw="+member_pw);
					return 1;
				} else {
					
					return -1;
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		return 0;
}
	
	
	public void join (String member_id, String member_pw, String member_name, String member_address,
			String member_address_detail1, String member_address_detail2, String member_address_num, String member_email,
			String member_gender, String member_car)
	{
		
		
		try {
			conn = datasource.getConnection();
			String query = "insert into Car_member(member_id, member_pw, member_name, member_address, member_address_detail1,member_address_detail2, member_address_num"
					+ ",member_email, member_gender, member_car) value(?,?,?,?,?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setString(2, member_pw);
			pstmt.setString(3, member_name);
			pstmt.setString(4, member_address);
			pstmt.setString(5, member_address_detail1);
			pstmt.setString(6, member_address_detail2);
			pstmt.setInt(7,  Integer.parseInt(member_address_num));
			pstmt.setString(8, member_email);
			pstmt.setString(9, member_gender);
			pstmt.setString(10, member_car);
			pstmt.executeUpdate();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto2> Notice() {

		ArrayList<BDto2> dtos = new ArrayList<BDto2>();
		
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select bNum, bTitle, bContent, bName, bDate, bHit from Car_Notice order by bNum asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int bNum = rs.getInt("bNum");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				String bName = rs.getString("bName");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				
				BDto2 dto = new BDto2(bNum, bTitle, bContent,bName, bDate, bHit);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos;
	}
    
	public BDto2 Notice_view(String strId) {
		upHit(strId);
		
		BDto2 dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_Notice where bNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId)); //?????????
			rs = pstmt.executeQuery();
			//?????? ?????? ????????? ???????????? dto??? ?????? ??????
			if(rs.next()) {
				int bNum = rs.getInt("bNum");
				String bName =  rs.getString("bName");
				String bTitle =  rs.getString("bTitle");
				String bContent =  rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				System.out.println(bNum);
				
				dto = new BDto2(bNum, bName, bTitle, bContent, bDate, bHit);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	private void upHit(String bNum) {
	     try {
	    	 conn = datasource.getConnection();
	    	 String sql = "update Car_Notice set bHit = bHit+1 where bNum=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1,bNum);
	    	 pstmt.executeUpdate();
	    	 
	     }catch(Exception e){
	    	 e.printStackTrace();
	    	 
	     }finally {
	    	 try {
	    		 pstmt.close();
	    		 conn.close();
	    	 }catch(Exception e2) {
	    		 e2.printStackTrace();
	    	 }
	     }
	     
	    
	
	     
	     
	}
	
	public ArrayList<BDto3> SellReview() {

		ArrayList<BDto3> dtos = new ArrayList<BDto3	>();
		
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select sRNum, sRTitle, sRContent, sRId, sRDate, sRHit, sRCar from Sell_Review order by sRNum asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				int sRNum = rs.getInt("sRNum");
				String sRTitle = rs.getString("sRTitle");
				String sRContent = rs.getString("sRContent");
				String sRId = rs.getString("sRId");
				Timestamp sRDate = rs.getTimestamp("sRDate");
				int sRHit = rs.getInt("sRHit");
				String sRCar = rs.getString("sRCar");
				
				BDto3 dto = new BDto3(sRNum, sRTitle, sRContent,sRDate, sRHit, sRId, sRCar);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos;
	}
	public BDto3 SellReview_view(String strId) {
		upHit1(strId);
		
		BDto3 dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Sell_Review where sRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId)); //?????????
			rs = pstmt.executeQuery();
			//?????? ?????? ????????? ???????????? dto??? ?????? ??????
			if(rs.next()) {
				int sRNum = rs.getInt("sRNum");
				String sRTitle = rs.getString("sRTitle");
				String sRContent = rs.getString("sRContent");
				String sRId = rs.getString("sRId");
				Timestamp sRDate = rs.getTimestamp("sRDate");
				int sRHit = rs.getInt("sRHit");
				String sRCar = rs.getString("sRCar");
				
				dto = new BDto3(sRNum, sRTitle, sRContent,sRDate, sRHit, sRId, sRCar);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	private void upHit1(String sRNum) {
	     try {
	    	 conn = datasource.getConnection();
	    	 String sql = "update Sell_Review set sRHit = sRHit+1 where sRNum=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1,sRNum);
	    	 pstmt.executeUpdate();
	    	 
	     }catch(Exception e){
	    	 e.printStackTrace();
	    	 
	     }finally {
	    	 try {
	    		 pstmt.close();
	    		 conn.close();
	    	 }catch(Exception e2) {
	    		 e2.printStackTrace();
	    	 }
	     }
	     
	    
	
	     
	     
	}
	
	public BDto3 SellReview_Modify_view(String strId) {
		upHit1(strId);
		
		BDto3 dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Sell_Review where sRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId)); //?????????
			rs = pstmt.executeQuery();
			//?????? ?????? ????????? ???????????? dto??? ?????? ??????
			if(rs.next()) {
				int sRNum = rs.getInt("sRNum");
				String sRTitle = rs.getString("sRTitle");
				String sRContent = rs.getString("sRContent");
				String sRId = rs.getString("sRId");
				Timestamp sRDate = rs.getTimestamp("sRDate");
				int sRHit = rs.getInt("sRHit");
				String sRCar = rs.getString("sRCar");
				
				dto = new BDto3(sRNum, sRTitle, sRContent,sRDate, sRHit, sRId, sRCar);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
	public void SellReview_modify(String sRNum, String sRId, String sRTitle, String sRContent) {
		try {
			conn = datasource.getConnection();
			
			String sql = "update Sell_Review set sRId=?, sRTitle=?, sRContent=? where sRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("sRId="+sRId);
			System.out.println("sRTitle="+sRTitle);
			System.out.println("sRContent="+sRContent);
			System.out.println("sRNum="+sRNum);
			
			pstmt.setString(1,sRId);
			pstmt.setString(2,sRTitle);
			pstmt.setString(3,sRContent);
			pstmt.setInt(4,Integer.parseInt(sRNum));
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void SellReview_delete(String sRNum) {
		try {
			conn = datasource.getConnection();
			String sql = "delete from Sell_Review where sRNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(sRNum));
			System.out.println("sRNum="+sRNum);
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void SellReview_write(String sRId, String sRTitle, String sRContent , String sRCar) {
		try {
			conn=datasource.getConnection();
			String sql = "insert into Sell_Review(sRNum,sRTitle,sRContent,sRHit,sRCar,sRId) values(nextval('Sell_Review'),?,?,0,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sRTitle);
			pstmt.setString(2, sRContent);
			pstmt.setString(3, sRCar);
			pstmt.setString(4, sRId);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto4> BuyReview() {

		ArrayList<BDto4> dtos = new ArrayList<BDto4>();
		
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select bRNum, bRTitle, bRContent, bRId, bRDate, bRHit, bRCar from Buy_Review order by bRNum asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				
				String bRId = rs.getString("bRId");
				String bRTitle = rs.getString("bRTitle");
				String bRContent = rs.getString("bRContent");
				String bRCar = rs.getString("bRCar");
				int bRNum = rs.getInt("bRNum");
				int bRHit = rs.getInt("bRHit");
				Timestamp bRDate = rs.getTimestamp("bRDate");
				System.out.println("bRId="+bRId);
				
				
				
				BDto4 dto = new BDto4(bRId, bRTitle, bRContent, bRCar, bRNum, bRHit, bRDate);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos;
	}
	public BDto4 BuyReview_view(String strId) {
		upHit2(strId);
		
		BDto4 dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Buy_Review where bRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId)); //?????????
			rs = pstmt.executeQuery();
			//?????? ?????? ????????? ???????????? dto??? ?????? ??????
			if(rs.next()) {
				int bRNum = rs.getInt("bRNum");
				String bRTitle = rs.getString("bRTitle");
				String bRContent = rs.getString("bRContent");
				String bRId = rs.getString("bRId");
				Timestamp bRDate = rs.getTimestamp("bRDate");
				int bRHit = rs.getInt("bRHit");
				String bRCar = rs.getString("bRCar");
				
				dto = new BDto4(bRId, bRTitle, bRContent, bRCar, bRNum, bRHit, bRDate);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	private void upHit2(String bRNum) {
	     try {
	    	 conn = datasource.getConnection();
	    	 String sql = "update Buy_Review set bRHit = bRHit+1 where bRNum=?";
	    	 pstmt = conn.prepareStatement(sql);
	    	 pstmt.setString(1,bRNum);
	    	 pstmt.executeUpdate();
	    	 
	     }catch(Exception e){
	    	 e.printStackTrace();
	    	 
	     }finally {
	    	 try {
	    		 pstmt.close();
	    		 conn.close();
	    	 }catch(Exception e2) {
	    		 e2.printStackTrace();
	    	 }
	     }
	     
	    
	
	     
	     
	}
	
	public BDto4 BuyReview_Modify_view(String strId) {
		upHit2(strId);
		
		BDto4 dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Buy_Review where bRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strId)); //?????????
			rs = pstmt.executeQuery();
			//?????? ?????? ????????? ???????????? dto??? ?????? ??????
			if(rs.next()) {
				int bRNum = rs.getInt("bRNum");
				String bRTitle = rs.getString("bRTitle");
				String bRContent = rs.getString("bRContent");
				String bRId = rs.getString("bRId");
				Timestamp bRDate = rs.getTimestamp("bRDate");
				int bRHit = rs.getInt("bRHit");
				String bRCar = rs.getString("bRCar");
				
				dto = new BDto4(bRId, bRTitle, bRContent, bRCar, bRNum, bRHit, bRDate);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
	public void BuyReview_modify(String bRId, String bRTitle, String bRContent, String bRNum) {
		try {
			conn = datasource.getConnection();
			
			String sql = "update Buy_Review set bRId=?, bRTitle=?, bRContent=? where bRNum=?";
			
			pstmt = conn.prepareStatement(sql);
			
			
			
			pstmt.setString(1,bRId);
			pstmt.setString(2,bRTitle);
			pstmt.setString(3,bRContent);
			pstmt.setInt(4,Integer.parseInt(bRNum));
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void BuyReview_delete(String bRNum) {
		try {
			conn = datasource.getConnection();
			String sql = "delete from Buyy_Review where bRNum=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(bRNum));
			
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void BuyReview_write(String bRId, String bRTitle, String bRContent , String bRCar) {
		try {
			conn=datasource.getConnection();
			String sql = "insert into Buy_Review(bRNum,bRTitle,bRContent,bRHit,bRCar,bRId) values(nextval('Buy_Review'),?,?,0,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bRTitle);
			pstmt.setString(2, bRContent);
			pstmt.setString(3, bRCar);
			pstmt.setString(4, bRId);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BDto4> BuyReview_search(String bRSearchT,String bRSearchW){
        ArrayList<BDto4> dtos21 = new ArrayList<BDto4>();
		
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select bRNum, bRTitle, bRContent, bRId, bRDate, bRHit, bRCar from Buy_Review where bRTitle like ? or bRId like ?";
			pstmt = conn.prepareStatement(sql);
			bRSearchT = '%'+bRSearchT+'%';
			bRSearchW = '%'+bRSearchW+'%';
			pstmt.setString(1, bRSearchT );
			pstmt.setString(2, bRSearchW);
			System.out.println("sql="+sql);
			System.out.println("bRSearchT="+bRSearchT);
			System.out.println("bRSearchW="+bRSearchW);
			rs = pstmt.executeQuery(); 
			System.out.println("rs="+rs);
			while(rs.next()) {
				
				String bRId = rs.getString("bRId");
				String bRTitle = rs.getString("bRTitle");
				String bRContent = rs.getString("bRContent");
				String bRCar = rs.getString("bRCar");
				int bRNum = rs.getInt("bRNum");
				int bRHit = rs.getInt("bRHit");
				Timestamp bRDate = rs.getTimestamp("bRDate");
				System.out.println("bRId="+bRId);
				System.out.println("bRTitle="+bRTitle);
				System.out.println("bRContent="+bRContent);
				System.out.println("bRCar="+bRCar);
				
				
				
				BDto4 dto = new BDto4(bRId, bRTitle, bRContent, bRCar, bRNum, bRHit, bRDate);
				dtos21.add(dto);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos21;
		
	}
    
	public ArrayList<BDto> info(){
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select*from Car_member";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				
				String member_id = rs.getString("member_id");
				String member_pw = rs.getString("member_pw");
				String member_name = rs.getString("member_name");
				String member_address = rs.getString("member_address");
				String member_address_detail1 = rs.getString("member_address_detail1");
				String member_address_detail2 = rs.getString("member_address_detail2");
				int member_address_num = rs.getInt("member_address_num");
				String member_email = rs.getString("member_email");
				String member_gender = rs.getString("member_gender");
				String member_car = rs.getString("member_car");
				
				
				
				BDto dto = new BDto(member_id, member_pw, member_name, member_address, member_address_detail1, member_address_detail2, member_address_num,member_email,member_gender,member_car);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos;
		
	}
	
	
	public BDto info_view(String member_id) {
		
		BDto dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_member where member_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String member_id1 = rs.getString("member_id");
				String member_pw = rs.getString("member_pw");
				String member_name = rs.getString("member_name");
				String member_address = rs.getString("member_address");
				String member_address_detail1 = rs.getString("member_address_detail1");
				String member_address_detail2 = rs.getString("member_address_detail2");
				int member_address_num = rs.getInt("member_address_num");
				String member_email = rs.getString("member_email");
				String member_gender = rs.getString("member_gender");
				String member_car = rs.getString("member_car");
				System.out.println("member_address_detail1="+member_address_detail1);
				System.out.println("member_address_detail2="+member_address_detail2);
				System.out.println("member_address_num="+member_address_num);
				
				dto = new BDto(member_id1, member_pw, member_name, member_address, member_address_detail1, member_address_detail2, member_address_num,member_email,member_gender,member_car);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
public BDto info_modify_view(String member_id) {
		
		BDto dto = null;
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_member where member_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String member_id1 = rs.getString("member_id");
				String member_pw = rs.getString("member_pw");
				String member_name = rs.getString("member_name");
				String member_address = rs.getString("member_address");
				String member_address_detail1 = rs.getString("member_address_detail1");
				String member_address_detail2 = rs.getString("member_address_detail2");
				int member_address_num = rs.getInt("member_address_num");
				String member_email = rs.getString("member_email");
				String member_gender = rs.getString("member_gender");
				String member_car = rs.getString("member_car");
				System.out.println("member_address_detail1="+member_address_detail1);
				System.out.println("member_address_detail2="+member_address_detail2);
				System.out.println("member_address_num="+member_address_num);
				System.out.println("member_gender="+member_gender);
				
				dto = new BDto(member_id1, member_pw, member_name, member_address, member_address_detail1, member_address_detail2, member_address_num,member_email,member_gender,member_car);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
		
	public void info_modify(String member_pw, String member_name, String member_address,
			String member_address_detail1, String member_address_detail2, String member_address_num, String member_email,
			String member_gender, String member_car,String member_id) {
		try {
			conn=datasource.getConnection();
			String sql = "update Car_member set member_pw=?, member_name=?, member_address=?, member_address_detail1=?,member_address_detail2=?, member_address_num=?, member_email=?,member_gender=?,member_car=? where member_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member_pw);
			pstmt.setString(2, member_name);
			pstmt.setString(3, member_address);
			pstmt.setString(4, member_address_detail1);
			pstmt.setString(5, member_address_detail2);
			pstmt.setString(6,member_address_num);
			pstmt.setString(7, member_email);
			pstmt.setString(8, member_gender);
			pstmt.setString(9, member_car);
			pstmt.setString(10, member_id);
			System.out.println("member_address_detail1="+member_address_detail1);
			System.out.println("member_address_detail2="+member_address_detail2);
			System.out.println("member_address_num="+member_address_num);
			System.out.println("member_gender="+member_gender);
			
			
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		
		
	}
	public ArrayList<BDto5> Carinfo(){
		ArrayList<BDto5> dtos = new ArrayList<BDto5>();
		
		try {

			conn = datasource.getConnection();
			
			String sql = "select*from Car_info";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				
				String member_id = rs.getString("member_id");
				String member_car = rs.getString("member_car");
				String Car_kM = rs.getString("Car_kM");
				String Car_Num = rs.getString("Car_Num");
				int Car_ProductY = rs.getInt("Car_ProductY");
				String Car_color = rs.getString("Car_color");
				String Car_brand = rs.getString("Car_brand");
				String Car_type = rs.getString("Car_type");
				String Car_Accident = rs.getString("Car_Accident");
				
				
				
				
				BDto5 dto = new BDto5(member_car, Car_kM, Car_Num, Car_ProductY, Car_color, Car_brand,Car_type,Car_Accident, member_id);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		
		

		
		
		
		return dtos;
		
	}
	public void Carinfo_insert(String member_car, String car_kM, String car_Num, String car_ProductY, String car_color, String car_brand, String car_type, String car_Accident, String member_id  ){
		try {
			conn=datasource.getConnection();
			String sql = "insert into Car_info(member_car,car_kM,car_Num,car_ProductY,car_color,car_brand,car_type,car_Accident,member_id) values(?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_car);
			pstmt.setString(2, car_kM);
			pstmt.setString(3, car_Num);
			pstmt.setString(4, car_ProductY);
			pstmt.setString(5, car_color);
			pstmt.setString(6, car_brand);
			pstmt.setString(7, car_type);
			pstmt.setString(8, car_Accident);
			pstmt.setString(9, member_id);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public BDto5 Carinfo_view(String strID) {
	
		BDto5 dto = null;
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_info where member_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strID); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String member_car = rs.getString("member_car");
				String car_kM = rs.getString("car_kM");
				String car_Num = rs.getString("car_Num");
				int car_ProductY = rs.getInt("car_ProductY");
				String car_color = rs.getString("car_color");
				String car_brand = rs.getString("car_brand");
				String car_type = rs.getString("car_type");
				String car_Accident = rs.getString("car_Accident");
				String member_id = rs.getString("member_id");
				System.out.println("member_id="+member_id);
				System.out.println("car_Accident="+car_Accident);
				System.out.println("end!");
				
				
				dto = new BDto5(member_car, car_kM, car_Num, car_ProductY, car_color, car_brand, car_type,car_Accident, member_id);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
	public void Carinfo_modify(String member_car, String car_kM, String car_Num, String car_ProductY, String car_color, String car_brand, String car_type, String car_Accident, String member_id ) {
		try {
			conn=datasource.getConnection();
			String sql = "update Car_info set member_car=?,car_kM =?,car_Num = ?,car_ProductY =?,car_color= ?,car_brand= ?,car_type= ?,car_Accident= ? where member_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_car);
			pstmt.setString(2, car_kM);
			pstmt.setString(3, car_Num);
			pstmt.setString(4, car_ProductY);
			pstmt.setString(5, car_color);
			pstmt.setString(6, car_brand);
			pstmt.setString(7, car_type);
			pstmt.setString(8, car_Accident);
			pstmt.setString(9, member_id);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BDto6> SellList() {
		ArrayList<BDto6> dtos = new ArrayList<BDto6>();
		try {

			conn = datasource.getConnection();
			
			String sql = "select*from Car_SellList";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); 
			while(rs.next()) {
				
				int sNum = rs.getInt("sNum");
				String sCar = rs.getString("sCar");
				String sCar_Num = rs.getString("sCar_Num");
				String sCar_kM = rs.getString("sCar_kM");
				int sCar_ProductY = rs.getInt("sCar_ProductY");
				String sCar_color = rs.getString("sCar_color");
				String sCar_brand = rs.getString("sCar_brand");
				String sCar_type = rs.getString("sCar_type");
				String sCar_Accident = rs.getString("sCar_Accident");
				int sCar_Price = rs.getInt("sCar_Price");
				int sCar_salePrice = rs.getInt("sCar_salePrice");
				
				
				
				
				BDto6 dto = new BDto6(sNum,sCar,sCar_Num,sCar_kM,sCar_ProductY,sCar_color,sCar_brand,sCar_type,sCar_Accident,sCar_Price,sCar_salePrice);
				dtos.add(dto);
			}
			
			System.out.println(dtos);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
		}
		}
		return dtos;
		
	}
	
	public void SellList_insert(String sCar, String sCar_Num, String sCar_kM, String sCar_ProductY, String sCar_color, String sCar_brand, String sCar_type, String sCar_Accident) {
		
		try {
			conn=datasource.getConnection();
			String sql = "insert into Car_SellList(sNum,sCar,sCar_Num,sCar_kM,sCar_ProductY,sCar_color,sCar_brand,sCar_type,sCar_Accident) values(nextval('Car_SellList'),?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, sCar);
			pstmt.setString(2, sCar_Num);
			pstmt.setString(3, sCar_kM);
			pstmt.setString(4, sCar_ProductY);
			pstmt.setString(5, sCar_color);
			pstmt.setString(6, sCar_brand);
			pstmt.setString(7, sCar_type);
			pstmt.setString(8, sCar_Accident);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	public BDto6 SellList_detail(String strID) {
		
		BDto6 dto = null;
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_SellList where sNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(strID)); 
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int sNum = rs.getInt("sNum");
				String sCar = rs.getString("sCar");
				String sCar_Num = rs.getString("sCar_Num");
				String sCar_kM = rs.getString("sCar_kM");
				int sCar_ProductY = rs.getInt("sCar_ProductY");
				String sCar_color = rs.getString("sCar_color");
				String sCar_brand = rs.getString("sCar_brand");
				String sCar_type = rs.getString("sCar_type");
				String sCar_Accident = rs.getString("sCar_Accident");
				int sCar_Price = rs.getInt("sCar_Price");
				int sCar_salePrice = rs.getInt("sCar_salePrice");
				System.out.println("sNum="+sNum);
				
				
				dto = new BDto6(sNum, sCar, sCar_Num, sCar_kM, sCar_ProductY, sCar_color, sCar_brand,sCar_type, sCar_Accident,sCar_Price,sCar_salePrice);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		
		
		return dto;
	}
	
	public void SellList_Update(String sCar_Price, String sCar_salePrice, String sNum) {
		try {
			conn=datasource.getConnection();
			String sql = "update Car_SellList set sCar_Price=?,sCar_salePrice =? where sNum=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sCar_Price);
			pstmt.setString(2, sCar_salePrice);
			pstmt.setString(3, sNum);
			System.out.println("sNum="+sNum);
			System.out.println("sCar_Price="+sCar_Price);
			System.out.println("sCar_salePrice="+sCar_salePrice);

			
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<BDto6> SellList_select(String Car_brand, String Car_type, String Car_color) {

		ArrayList<BDto6> dtos1 = new ArrayList<BDto6>();
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_SellList where sCar_brand=? or sCar_type=? or sCar_color=? order by sCar_brand asc" ;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,Car_brand);
			pstmt.setString(2,Car_type);
			pstmt.setString(3,Car_color);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				
				
				int sNum = rs.getInt("sNum");
				String sCar = rs.getString("sCar");
				String sCar_Num = rs.getString("sCar_Num");
				String sCar_kM = rs.getString("sCar_kM");
				int sCar_ProductY = rs.getInt("sCar_ProductY");
				String sCar_color = rs.getString("sCar_color");
				String sCar_brand = rs.getString("sCar_brand");
				String sCar_type = rs.getString("sCar_type");
				String sCar_Accident = rs.getString("sCar_Accident");
				int sCar_Price = rs.getInt("sCar_Price");
				int sCar_salePrice = rs.getInt("sCar_salePrice");
				System.out.println("sCar="+sCar);
				System.out.println("sNum="+sNum);
				System.out.println("sCar_Price="+sCar_Price);
				System.out.println("sCar_salePrice="+sCar_salePrice);
				System.out.println("sCar_color="+sCar_color);
				
				
				
				
				BDto6 dto = new BDto6(sNum, sCar, sCar_Num, sCar_kM, sCar_ProductY, sCar_color, sCar_brand,sCar_type, sCar_Accident,sCar_Price,sCar_salePrice);
				dtos1.add(dto);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		System.out.println(dtos1);
		
		
		return dtos1;
		
	}
	
	public ArrayList<BDto6> SellList_selectPrice(String Car_Price1, String Car_Price2) {

		ArrayList<BDto6> dtos1 = new ArrayList<BDto6>();
		
		try {
			conn = datasource.getConnection();
			String sql="select*from Car_SellList where sCar_salePrice between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(Car_Price1));
			pstmt.setInt(2,Integer.parseInt(Car_Price2));
			rs = pstmt.executeQuery();
			System.out.println("rs="+rs);
			
			
			while(rs.next()) {
				
				
				
				int sNum = rs.getInt("sNum");
				String sCar = rs.getString("sCar");
				String sCar_Num = rs.getString("sCar_Num");
				String sCar_kM = rs.getString("sCar_kM");
				int sCar_ProductY = rs.getInt("sCar_ProductY");
				String sCar_color = rs.getString("sCar_color");
				String sCar_brand = rs.getString("sCar_brand");
				String sCar_type = rs.getString("sCar_type");
				String sCar_Accident = rs.getString("sCar_Accident");
				int sCar_Price = rs.getInt("sCar_Price");
				int sCar_salePrice = rs.getInt("sCar_salePrice");
				System.out.println("sCar="+sCar);
				System.out.println("sNum="+sNum);
				System.out.println("sCar_Price="+sCar_Price);
				System.out.println("sCar_salePrice="+sCar_salePrice);
				System.out.println("sCar_color="+sCar_color);
				
				
			
				
				BDto6 dto = new BDto6(sNum, sCar, sCar_Num, sCar_kM, sCar_ProductY, sCar_color, sCar_brand,sCar_type, sCar_Accident,sCar_Price,sCar_salePrice);
				dtos1.add(dto);
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {

				pstmt.close();
				conn.close();
				
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}
		System.out.println(dtos1);
		
		
		return dtos1;
		
	}
		
	
	     		

}

