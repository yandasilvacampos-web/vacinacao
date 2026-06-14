package com.application.interacao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.application.controllers.VacinaController;
import com.application.entities.Vacina;

public class VacinaMenu {

public static void menu(Connection connection, Scanner scanner) {
	
		VacinaController vacinaController = new VacinaController(connection);
		
		System.out.println("---------------- Menu Paciente ----------------");
		
		System.out.println("1 - Consultar Todas as Vacinas");
		System.out.println("2 - Consultar Vacina por Nome");
		System.out.println("3 - Cadastrar Nova Vacina");
		System.out.println("4 - Excluir Vacina");
		System.out.println("5 - Voltar ao Menu Principal");
		
		
		int resposta;
		
		boolean continuaWhile = true;
		
		while(continuaWhile) {
			
			continuaWhile = false;
			
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
					
					List<Vacina> vacinas = new ArrayList<>();
					vacinas = vacinaController.findAllVacinas();
					
					for (Vacina vacina : vacinas) {
						System.out.println(vacina);
					}
				break;
				case 2:
				
					vacinas = new ArrayList<>();
					
					System.out.println("Digite o nome da vacina a ser procurada: ");
					String nome_vacina = scanner.nextLine();
					
					vacinas = vacinaController.findVacinasByNome(nome_vacina);
					
					for (Vacina vacina : vacinas) {
						System.out.println(vacina);
					}
				break;
				case 3:
					
					Vacina vacina = new Vacina();
					
					scanner.nextLine();
					System.out.println("Digite o nome da vacina: ");
					vacina.setNome(scanner.nextLine());
					
					System.out.println("Digite o tipo da vacina: ");
					vacina.setTipo(scanner.nextLine());
					
					System.out.println("Digite o Fabricante: ");
					vacina.setFabricante(scanner.nextLine());

					vacinaController.cadastrarVacina(vacina);
				break;
				case 4:
					
					vacinas = new ArrayList<>();
					vacinas = vacinaController.findAllVacinas();
					
					for (Vacina vacina2 : vacinas) {
						System.out.println(vacina2.getId_vacina() + " - " + vacina2.getNome());
					}
					
					System.out.println("Escolha uma vacina para fazer a exclusão: ");
					int vacina_id = scanner.nextInt();
					
					vacinaController.deleteVacinaById(vacina_id);
				break;
				case 5:
					Menuinteracao menuinteracao = new Menuinteracao(connection);
					menuinteracao.menudeinteracao(scanner);
				break;
				default:
					System.err.println("Código Inválido - Opções de 1 até 5");
					continuaWhile = true;
			}
		}
	}
}
