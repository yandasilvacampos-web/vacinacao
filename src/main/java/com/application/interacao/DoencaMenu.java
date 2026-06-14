package com.application.interacao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.application.controllers.VacinaController;
import com.application.entities.Doenca;
import com.application.entities.Paciente;
import com.application.entities.Vacina;
import com.application.services.DoencaService;

public class DoencaMenu {

public static void menu(Connection connection, Scanner scanner) {
		
		DoencaService doencaService = new DoencaService(connection);
		VacinaController vacinaController = new VacinaController(connection);
		
		System.out.println("---------------- Menu Doença ----------------");
		
		System.out.println("1 - Consultar Todos as Doenças");
		System.out.println("2 - Consultar Vacina Referente");
		System.out.println("3 - Consultar Pacientes Infectados por Doença");
		System.out.println("4 - Cadastrar Nova Doença");
		System.out.println("5 - Atualizar Vacina Referente");
		System.out.println("6 - Voltar ao Menu Inicial");
		
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
					
					List<Doenca> doencas = new ArrayList<>();
					doencas = doencaService.listarTodas();
					
					for (Doenca doenca : doencas) {
						System.out.println(doenca);
					}
					
				break;
				case 2:
					
					doencas = new ArrayList<>();
					doencas = doencaService.listarTodas();
					
					for (Doenca doenca : doencas) {
						System.out.println(doenca.getId_doenca() + " - " + doenca.getNomedoenca());
					}
					
					System.out.println("Escolha uma doença para fazer a consulta de vacina referente");
					int doenca_id = scanner.nextInt();
					
					Vacina vacina = doencaService.findVacinaReferente(doenca_id);
					System.out.println(vacina);
				break;
				case 3:
					
					doencas = new ArrayList<>();
					doencas = doencaService.listarTodas();
					
					for (Doenca doenca : doencas) {
						System.out.println(doenca.getId_doenca() + " - " + doenca.getNomedoenca());
					}
					
					System.out.println("Escolha uma doença para fazer a consulta de pacientes infectados: ");
					doenca_id = scanner.nextInt();
					
					List<Paciente> pacientes = doencaService.listarPacientesInfectados(doenca_id);
					
					for (Paciente paciente : pacientes) {
						System.out.println(paciente);
					}
				
				break;
				case 4:
					
					List<Vacina> vacinas = vacinaController.findAllVacinas();
					
					System.out.println("Nome da doença: ");
					String nomeDoenca = scanner.nextLine();
					
					for (Vacina vacina2 : vacinas) {
						System.out.println(vacina2.getId_vacina() + " - " + vacina2.getNome());
					}
					
					System.out.print("Escolha a vacina referente a cura: ");
					int vacina_id = scanner.nextInt();
					
					doencaService.cadastrar(nomeDoenca, vacina_id);
					
				break;
				case 5: 
					
					doencas = new ArrayList<>();
					doencas = doencaService.listarTodas();
					
					for (Doenca doenca : doencas) {
						System.out.println(doenca.getId_doenca() + " - " + doenca.getNomedoenca());
					}
					
					System.out.println("Escolha uma doença para fazer a atualização de vacina referente: ");
					doenca_id = scanner.nextInt();
					
					vacinas = vacinaController.findAllVacinas();
					
					for (Vacina vacina2 : vacinas) {
						System.out.println(vacina2.getId_vacina() + " - " + vacina2.getNome());
					}
					
					System.out.println("Escolha a vacina que fará o tratamento dessa doença: ");
					vacina_id = scanner.nextInt();
					
					doencaService.atualizarVacinaReferente(doenca_id, vacina_id);
					
				break;
				case 6:
					Menuinteracao menuinteracao = new Menuinteracao(connection);
					menuinteracao.menudeinteracao(scanner);
				break;
				default:
					System.err.println("Código Inválido - Opções de 1 até 6");
					continuaWhile = true;
			}
		}
	}
}
