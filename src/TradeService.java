package com.taotaole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taotaole.db.DButil;
import com.taotaole.model.Trade;

public class TradeService {
	public void addTrade(Trade trade) throws Exception {
		Connection conn = DButil.getConnection();
		String sql ="insert into trade"+ 
					"(bookName,sellerName,buyerName,amount,time)"+
					"value(?,?,?,?,current_date())";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, trade.getBookName());
		ptmt.setString(2, trade.getSellerName());
		ptmt.setString(3, trade.getBuyerName());
		ptmt.setDouble(4, trade.getAmount());
		ptmt.execute();
	}
	
	public List<Trade> serchTrade(String buyerName) throws Exception{
		Connection conn = DButil.getConnection();
		StringBuilder sb =new StringBuilder();
		sb.append("select * from trade");
		sb.append(" where buyername = ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1,buyerName);
		ResultSet rs = ptmt.executeQuery();
		
		List<Trade> trades=new ArrayList<Trade>();
		Trade trade=null;
		while(rs.next()){
			trade = new Trade();
			trade.setId(rs.getInt("id"));
			trade.setBuyerName(rs.getString("buyerName"));
			trade.setSellerName(rs.getString("SellerName"));
			trade.setAmount(rs.getDouble("amount"));
			trade.setBookName(rs.getString("bookName"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date d = sdf.parse(rs.getDate("time").toString());
			trade.setTime(d);
			trades.add(trade) ;
		}
		return trades;
	}
	
	public void deleteTrade(int id) throws Exception {
		Connection conn = DButil.getConnection();
		String sql = "delete from trade where id = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
}
