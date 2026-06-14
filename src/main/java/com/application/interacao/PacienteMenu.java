package com.application.interacao;

import java.sql.Connection;
import java.util.Scanner;

public class PacienteMenu {

	public static void menu(Connection connection, Scanner scanner) {
		
		System.out.println("---------------- Menu Paciente ----------------");
		
		System.out.println("1 - Consultar Todos os Pacientes");
		System.out.println("2 - Consultar Paciente por Nome");
		System.out.println("3 - Consultar Escolaridade de Paciente");
		System.out.println("4 - Consultar Região de um Paciente");
		System.out.println("5 - Exibir Histórico de Vacinações");
		System.out.println("6 - Cadastrar um novo Paciente");
		System.out.println("7 - Voltar ao Menu Inicial");
		
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
			 * Cada opção dessa acionará um metódo do nosso PacienteController.java
			 * Em que está depositado os serviços herdados pela nossa classe de 
			 * PacienteService.java
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
				case 6:
					
				break;
				case 7:
					
				break;
				default:
					System.err.println("Código Inválido - Opções de 1 até 7");
					continue;
			}
		}
	}
}
