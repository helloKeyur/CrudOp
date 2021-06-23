package com.crudop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.crudop.model.Author;

public class AuthorDAO {
	
	private Connection con;
	
	private static final String INSERT_AUTHOR_SQL = "insert into DB_AUTHOR_DEMO(username, email, phone, qualification, profile, createdat) values (?,?,?,?,?,?)";
	private static final String UPDATE_AUTHOR_SQL = "update DB_AUTHOR_DEMO set username=?, email=?, phone=?, qualification=?, profile=? where authorid=?";
	private static final String DELETE_AUTHOR_SQL = "delete from DB_AUTHOR_DEMO where authorid=?";
	private static final String SELECT_AUTHOR_SQL = "select * from DB_AUTHOR_DEMO where authorid=?";
	private static final String SELECT_ALL_AUTHOR_SQL = "select * from DB_AUTHOR_DEMO";
	
	public AuthorDAO(Connection con) {
		this.con = con;
	}
	
	/**
	 * @return boolean
	 * param Author author
	 */
	public boolean store(Author author) {
		boolean status = false;
		try{
			PreparedStatement ps = con.prepareStatement(INSERT_AUTHOR_SQL);
			ps.setString(1, author.getUsername());
			ps.setString(2, author.getEmail());
			ps.setString(3, author.getPhone());
			ps.setString(4, author.getQualification());
			ps.setString(5, author.getProfile());
			ps.setString(6, author.getCreatedAt());
			ps.executeUpdate();
			status = true;
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return status;
	}
	
	/**
	 * @return boolean
	 * param Author author
	 */
	public boolean update(Author author) {
		boolean status = false;
		try{
			PreparedStatement ps = con.prepareStatement(UPDATE_AUTHOR_SQL);
			ps.setString(1, author.getUsername());
			ps.setString(2, author.getEmail());
			ps.setString(3, author.getPhone());
			ps.setString(4, author.getQualification());
			ps.setString(5, author.getProfile());
			ps.setInt(6, author.getAuthorId());
			ps.executeUpdate();
			status = true;
		}catch(SQLException e) {
			System.out.print("update ex : "+e.getMessage());
		}
		return status;
	}
	
	/**
	 * @return boolean
	 * param int authorId
	 */
	public boolean delete(int authorId) {
		boolean status = false;
		try{
			PreparedStatement ps = con.prepareStatement(DELETE_AUTHOR_SQL);
			ps.setInt(1, authorId);
			ps.executeUpdate();
			status = true;
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return status;
	}
	
	/**
	 * @return Author author
	 * param int authorId
	 */
	public Author getOne(int authorId) {
		Author author = null;
		try{
			PreparedStatement ps = con.prepareStatement(SELECT_AUTHOR_SQL);
			ps.setInt(1, authorId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				author = new Author();
				author.setAuthorId(authorId);
				author.setUsername(rs.getString("username"));
				author.setEmail(rs.getString("email"));
				author.setPhone(rs.getString("phone"));
				author.setQualification(rs.getString("qualification"));
				author.setProfile(rs.getString("profile"));
				author.setCreatedAt(rs.getString("createdat"));
			}
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return author;
	}
	
	/**
	 * @return Author author
	 * param int authorId
	 */
	public List<Author> getAll() {
		List<Author> listOfAuthor = new ArrayList<Author>();
		Author author = null;
		try{
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_AUTHOR_SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				author = new Author();
				author.setAuthorId(rs.getInt("authorid"));
				author.setUsername(rs.getString("username"));
				author.setEmail(rs.getString("email"));
				author.setPhone(rs.getString("phone"));
				author.setQualification(rs.getString("qualification"));
				author.setProfile(rs.getString("profile"));
				author.setCreatedAt(rs.getString("createdat"));
				listOfAuthor.add(author);
			}
		}catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		return listOfAuthor;
	}
}
