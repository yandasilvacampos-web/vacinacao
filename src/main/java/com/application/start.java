package com.application;

import java.sql.Connection;
import java.util.Scanner;

import com.application.connection.ConnectionPostgre;
import com.application.interacao.Menuinteracao;

public class start {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Seja muito bem vindo:");
		
		Connection connection = ConnectionPostgre.getConnection();
		 
		Menuinteracao menuinteracao = new Menuinteracao(connection);
		menuinteracao.menudeinteracao(scanner);
		
		scanner.close();
	}
	

}
