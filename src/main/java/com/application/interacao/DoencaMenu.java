package com.application.interacao;

import java.sql.Connection;
import java.util.Scanner;

public class DoencaMenu {

public static void menu(Connection connection, Scanner scanner) {
		
		System.out.println("---------------- Menu Doença ----------------");
		
		System.out.println("1 - Consultar Todos as Doenças");
		System.out.println("2 - Consultar Vacina Referente");
		System.out.println("3 - Consultar Pacientes Infectados por Doença");
		System.out.println("4 - Cadastrar Nova Doença");
		System.out.println("5 - Atualizar Vacina Necessária");
		System.out.println("6 - Voltar ao Menu Inicial");
		
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
			 * Cada opção dessa acionará um metódo do nosso DoencaController.java
			 * Em que está depositado os serviços herdados pela nossa classe de 
			 * DoencaService.java
			 * 
			 * Alguns serviços ainda não estão disponíveis, necessário implantar.
			 * 
			 * Podemos ver que temos atividades mais complexas como o nosso
			 * Atualizar Vacina, esse metódo será mais complexo e exigirá um 
			 * maior número de processos SQL com o DriverConnection.java
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
				default:
					System.err.println("Código Inválido - Opções de 1 até 6");
					continue;
			}
		}
	}
}
