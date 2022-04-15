package com.taotaole.service;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.taotaole.db.DButil;
import com.taotaole.model.Book;

public class BookService {
	public void addBook(Book book) throws Exception {
		Connection conn = DButil.getConnection();
		String sql =""+
				"insert into book "+
				"(bookName,author,groundingTime,binding,originalCost,currentCost,press,"+
				"pressTime,Phase,bookSize,pageNum,grade,profession,ownerName)"+
				"value("+ 
				"?,?,current_date(),?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1,book.getBookName());
		ptmt.setString(2, book.getAuthor()); 
		ptmt.setString(3,book.getBinding());
		ptmt.setDouble(4, book.getOriginalCost());
		ptmt.setDouble(5, book.getCurrentCost());
		ptmt.setString(6,book.getPress());
		java.util.Date d = book.getPressTime();
		ptmt.setDate(7,new java.sql.Date(d.getTime()));
		ptmt.setString(8, book.getPhase());
		ptmt.setString(9,book.getBookSize());
		ptmt.setInt(10, book.getPageNum());
		ptmt.setString(11,book.getGrade());
		ptmt.setString(12, book.getProfession());
		ptmt.setString(13, book.getOwnerName());
		ptmt.execute();
	}
	public List<Book> serchBook(String content) throws Exception{
		System.out.println(content);
		Connection conn = DButil.getConnection();
		StringBuilder sb =new StringBuilder();
		sb.append("select * from book");
		sb.append(" where bookName like ? or grade like ? or profession like ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setString(1, content);
		ptmt.setString(2, content);
		ptmt.setString(3, content);
		ResultSet rs = ptmt.executeQuery();
		
		List<Book> books=new ArrayList<Book>();
		Book book=null;
		while(rs.next()){
			book=new Book();
			book.setId(rs.getInt("id"));
			book.setBookName(rs.getString("bookName"));
			book.setGroundingTime(new Date(rs.getDate("groundingTime").getTime()));
			book.setAuthor(rs.getString("author"));
			book.setCurrentCost(rs.getDouble("currentCost"));
			book.setPress(rs.getString("press"));
			book.setPressTime(new Date(rs.getDate("pressTime").getTime()));
			book.setPhase(rs.getString("Phase"));
			book.setImage(rs.getString("image"));
			books.add(book); 
		}
		return books;
	}
	public Book serchBookById(int id) throws Exception{
		
		Connection conn = DButil.getConnection();
		StringBuilder sb =new StringBuilder();
		sb.append("select * from book");
		sb.append(" where id = ?");
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
		ptmt.setInt(1, id);
		ResultSet rs = ptmt.executeQuery();
		Book book=new Book();
		if(rs.next()){
			book.setId(rs.getInt("id"));
			book.setBookName(rs.getString("bookName"));
			book.setAuthor(rs.getString("author"));
			Date d1 = new Date();
         	Date d2 = new Date();
         	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			book.setGroundingTime(new java.util.Date(rs.getDate("groundingTime").getTime()));
			book.setBinding(rs.getString("Binding"));
			book.setOriginalCost(rs.getDouble("originalCost"));
			book.setCurrentCost(rs.getDouble("currentCost"));
			book.setPress(rs.getString("press"));
			book.setPressTime(new java.util.Date(rs.getDate("pressTime").getTime()));
			book.setPhase(rs.getString("Phase"));
			book.setBookSize(rs.getString("bookSize"));
			book.setPageNum(rs.getInt("pageNum"));
			book.setGrade(rs.getString("grade"));
			book.setProfession(rs.getString("profession"));
			book.setOwnerName(rs.getString("ownerName"));
			book.setImage(rs.getString("image"));
		}
		return book;
	}
	public List<Book> query() throws Exception{
		
		Connection conn = DButil.getConnection();
		Statement stmt = conn.createStatement();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from book");
		ResultSet rs = stmt.executeQuery(sb.toString());
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		while(rs.next()) {
			book=new Book();
			book.setId(rs.getInt("id"));
			book.setBookName(rs.getString("bookName"));
			book.setAuthor(rs.getString("author"));
			book.setGroundingTime(new Date(rs.getDate("groundingTime").getTime()));
			book.setCurrentCost(rs.getDouble("currentCost"));
			book.setPress(rs.getString("press"));
			book.setPressTime(new Date(rs.getDate("pressTime").getTime()));
			book.setPhase(rs.getString("Phase"));
			book.setImage(rs.getString("image"));
			books.add(book);
		}
		return books;
	}
	public void deleteBook(int id) throws Exception {
		Connection conn = DButil.getConnection();
		String sql = "delete from book where id = ?";
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setInt(1, id);
		ptmt.execute();
	}
}
