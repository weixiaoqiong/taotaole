package com.taotaole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taotaole.db.DButil;
import com.taotaole.model.Collect;

public class CollectService {
	
	public void addCollect(Collect collect) throws Exception {
		Connection conn = DButil.getConnection();
		String sql ="insert into collect"+ 
					"(sellerName,buyerName,phase,amount,bookName)"+
					"value(?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, collect.getSellerName());
		ptmt.setString(2, collect.getBuyerName());
		ptmt.setString(3, collect.getPhase());
		ptmt.setDouble(4, collect.getAmount());
		ptmt.setString(5, collect.getBookName());
		ptmt.execute();
	}
	
	public List<Collect> serchCollect(String buyerName) throws Exception{
		Connection conn = DButil.getConnection();
		StringBuilder sb =new StringBuilder();
		sb.append("select * from collect");
		sb.append(" where buyername = ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1,buyerName);
		ResultSet rs = ptmt.executeQuery();
		
		List<Collect> collects=new ArrayList<Collect>();
		Collect collect=null;
		while(rs.next()){
			collect = new Collect();
			collect.setId(rs.getInt("id"));
			collect.setBuyerName(rs.getString("buyerName"));
			collect.setSellerName(rs.getString("SellerName"));
			collect.setBookName(rs.getString("bookName"));
			collect.setPhase(rs.getString("phase"));
			collect.setAmount(rs.getDouble("amount"));
			collects.add(collect) ;
		}
		return collects;
	}
	public void deleteCollect(int id) throws Exception {
		Connection conn = DButil.getConnection();
		String sql = "delete from collect where id = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
}
