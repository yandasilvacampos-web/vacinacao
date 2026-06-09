package com.application.interacao;

import java.sql.Connection;
import java.util.Scanner;

public class VacinaMenu {

public static void menu(Connection connection, Scanner scanner) {
		
		System.out.println("---------------- Menu Paciente ----------------");
		
		System.out.println("1 - Consultar Todas as Vacinas");
		System.out.println("2 - Consultar Vacina por Nome");
		System.out.println("3 - Consultar Doença por Vacina");
		System.out.println("4 - Cadastrar Nova Vacina");
		System.out.println("5 - Excluir Vacina");
		
		int resposta;
		
		while(true) {
			
			System.out.print("Insira o código: ");
			
			try {
				resposta = scanner.nextInt();
			}catch(Exception e) {
				System.err.println("Código Inválido - Tipo: Inteiro");
				continue;
			}
			
			/**
			 * Cada opção dessa acionará um metódo do nosso VacinaController.java
			 * Em que está depositado os serviços herdados pela nossa classe de 
			 * VacinaService.java
			 * 
			 * Alguns serviços ainda não estão disponíveis, necessário implantar
			 */
			switch (resposta) {
				case 1:
					
				break;
				case 2:
				
				break;
				case 3:
				
				break;
				case 4:
					
				break;
				
				case 5:
					
				break;
				default:
					System.err.println("Código Inválido - Opções de 1 até 5");
					continue;
			}
		}
	}
}
