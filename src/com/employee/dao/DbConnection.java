package com.employee.dao;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DbConnection {
    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        // Database name to access
        String dbName = "emp_db";
        String dbUsername = "root";
        String dbPassword = "sathish@kotha09";
  
        Class.forName(dbDriver);
        Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
        return con;
    }
}