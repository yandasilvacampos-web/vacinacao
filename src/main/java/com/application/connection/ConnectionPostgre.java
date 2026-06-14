package com.application.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPostgre {
	private static final String URL = "jdbc:postgresql://localhost:5432/vacinacao";
	private static final String USER = "postgres";
	private static final String SENHA = "123";

	 public static Connection getConnection() {
	        try {
	            return DriverManager.getConnection(URL, USER, SENHA);
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
	        }
	 }
}
