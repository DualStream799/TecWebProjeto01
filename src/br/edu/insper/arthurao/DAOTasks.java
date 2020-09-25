package br.edu.insper.arthurao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOTasks {
	private Connection connection = null;
	private final String dbName = "tecweb_projeto01", dbUsername = "dev01", dbPassword = "#D@m9Rjn?Gs_Sm5r";
	public DAOTasks() {
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
	
	public List<Tasks> getTasks(String userEmail) {
		List<Tasks> tasksList = new ArrayList<Tasks>();
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Tasks WHERE user_email='" + userEmail + "'");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Tasks task = new Tasks();
				task.setUserEmail(rs.getString("user_email"));
				task.setDescription(rs.getString("description"));
				task.setTag(Integer.parseInt(rs.getString("tag")));
				task.setId(Integer.parseInt(rs.getString("id")));
				tasksList.add(task);
			}
			
			rs.close();
			stmt.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasksList;
		
	}
	
	public void addTask(Tasks task) {
		// Prepares MySQL statement to add data on database:
		String sql = "INSERT INTO Tasks (user_email, description, tag) values (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			// Sets values to statement:
			stmt.setString(1, task.getUserEmail());
			stmt.setString(2, task.getDescription());
			stmt.setInt(3, task.getTag());
			// Executes and closes statement:
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void editTask(Tasks task, Integer taskId) {
		// Prepares MySQL statement to add data on database:
		String sql = "UPDATE Tasks SET description=?, tag=? WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			// Sets values to statement:
			stmt.setString(1, task.getDescription());
			stmt.setInt(2, task.getTag());
			stmt.setInt(3, taskId);
			// Executes and closes statement:
			stmt.execute();
			stmt.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeTask(Integer taskId) {	
		// Prepares MySQL statement to add data on database:
		String sql = "DELETE FROM Tasks WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			// Sets values to statement:
			stmt.setInt(1, taskId);
			// Executes and closes statement:
			stmt.execute();
			stmt.close();
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
