package br.edu.insper.arthurao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsers {
	private Connection connection = null;
	private final String dbName = "tecweb_projeto01", dbUsername = "dev01", dbPassword = "#D@m9Rjn?Gs_Sm5r";
	
	public DAOUsers() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, dbUsername, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Users> getUsers() {
		List<Users> usersList = new ArrayList<Users>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Users");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Users user = new Users();
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				usersList.add(user);
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersList;
	}
	
	public void addUser(Users user) {
		// Prepares MySQL statement to add data on database:
		String sql = "INSERT INTO Users" + "(email, password) values (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			// Sets values to statement:
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			// Executes and closes statement:
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
