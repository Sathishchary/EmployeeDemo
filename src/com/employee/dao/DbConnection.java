package com.employee.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Connection;

public class DbConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access
        String dbName = "emp_db";
        String dbUsername = "root";
        String dbPassword = "sathish@kotha09";
        Connection con = null;
        try
        {
	        try
	        {
	           Class.forName(dbDriver); //loading MySQL drivers. This differs for database servers 
	        } 
	        catch (ClassNotFoundException e)
	        {
	           e.printStackTrace();
	        }       
	        con = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword); //attempting to connect to MySQL database
	        System.out.println("Printing connection object "+con);
        } 
        catch (Exception e) 
        {
           e.printStackTrace();
        }   
        return con;
    }
    
    public static void closeConnections(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}