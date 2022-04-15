package com.taotaole.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.taotaole.db.DButil;
import com.taotaole.model.User;

public class UserService {
	public User query(String username) throws Exception {
		Connection conn = DButil.getConnection();
		String sql = "select * from user where username = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, username);
		ResultSet rs = ptmt.executeQuery();
		User user = new User();
		user.setUsername("");
		if(rs.next()) {
			user.setId(rs.getInt("id"));
			user.setUsername(username);
			user.setPassword(rs.getString("password"));
			user.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
			user.setSex(rs.getString("sex"));
			user.setTelephoneNumber(rs.getString("telephoneNumber"));
			user.setPersonalitySignature(rs.getString("personalitySignature"));
		}
		return user;
	}
	public void addUser(User user) throws Exception {
		Connection conn = DButil.getConnection();
		String sql =""+
				"insert into user "+
				"(username,password,birthday,sex,telephoneNumber,personalitySignature)"+
				"value("+
				"?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user.getUsername());
		ptmt.setString(2, user.getPassword());
		ptmt.setDate(3, new java.sql.Date(user.getBirthday().getTime()));
		ptmt.setString(4, user.getSex());
		ptmt.setString(5, user.getTelephoneNumber());
		ptmt.setString(6, user.getPersonalitySignature());
		ptmt.execute();
	}
	
	public void updateUser(User user) throws Exception {
		Connection conn = DButil.getConnection();
		String sql =""+
				" update user "+
				" set username=?,password=?,sex=?,birthday=? ,telephoneNumber=?,personalitySignature=? "+
				" where id=?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, user.getUsername());
		ptmt.setString(2, user.getPassword());
		ptmt.setString(3, user.getSex());
		ptmt.setDate(4, new java.sql.Date(user.getBirthday().getTime()));
		ptmt.setString(5, user.getTelephoneNumber());
		ptmt.setString(6, user.getPersonalitySignature());
		ptmt.setInt(7, user.getId());
		ptmt.execute(); 
	}
}
