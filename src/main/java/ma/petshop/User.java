package ma.petshop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class User {
	private String usename;
	private String password;
	private String email;
	private Integer phone;
	private String company;
	private String adress;
	
	
	
	
	public User(String usename, String password, String email) {
		this.usename = usename;
		this.password = password;
		this.email = email;
		this.phone = 0;
		this.company ="";
		this.adress = "";
	}
	public User(String usename, String password, String email, Integer phone, String company, String adress) {
		this.usename = usename;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.company = company;
		this.adress = adress;
	}
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPhone() {
		return phone;
	}
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
		
	public String getAdress() {
		return this.adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public void insertToDB(Connection connection) {
		String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(sql)) {
	      statement.setString(1, usename);
	      statement.setString(2, email);
	      statement.setString(3, password);
	      statement.executeUpdate();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    
	  }
	}
}
