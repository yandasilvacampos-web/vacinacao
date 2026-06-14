package com.application.interacao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.application.controllers.EscolaridadeController;
import com.application.controllers.PacienteController;
import com.application.controllers.RegiaoController;
import com.application.dtos.HistoricoVacinasDto;
import com.application.entities.Escolaridade;
import com.application.entities.Paciente;
import com.application.entities.Regiao;

public class PacienteMenu {
	
	public static void menu(Connection connection, Scanner scanner) {
		
		PacienteController pacienteController = new PacienteController(connection);
		EscolaridadeController escolaridadeController = new EscolaridadeController(connection);
		RegiaoController regiaoController = new RegiaoController(connection);
		
		System.out.println("---------------- Menu Paciente ----------------");
		
		System.out.println("1 - Consultar Todos os Pacientes");
		System.out.println("2 - Consultar Paciente por Nome");
		System.out.println("3 - Consultar Escolaridade de Paciente");
		System.out.println("4 - Consultar Pacientes por Região");
		System.out.println("5 - Exibir Histórico de Vacinações");
		System.out.println("6 - Cadastrar um novo Paciente");
		System.out.println("7 - Voltar ao Menu Inicial");
		
		int resposta;
		
		boolean continueWhile = true;
		
		while(continueWhile) {
			
			continueWhile = false;
			
			System.out.println("Insira o código: ");
			
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
					List<Paciente> pacientes = pacienteController.findAllPacientes();
					
					for (Paciente paciente : pacientes) {
						System.out.print(paciente);
					}
				break;
				case 2:
					
					System.out.print("Digite o nome do paciente: ");
					scanner.nextLine();
					String nome = scanner.nextLine();
					
					try {
						pacientes = pacienteController.findPacientesByNome(nome);

						for (Paciente paciente : pacientes) {
							System.out.print(paciente);
						}
					}catch(NullPointerException e) {
						System.out.println("Não existe usuários com esse nome");
					}
				break;
				case 3:
					pacientes = pacienteController.findAllPacientes();
					
					for (Paciente paciente : pacientes) {
						System.out.println(paciente.getId_paciente() + " - " + paciente.getNome());
					}
					
					System.out.println("Selecione um ID de paciente para buscar a escolaridade");
					
					try {
						int id_paciente = scanner.nextInt();
						
						List<Escolaridade> escolaridades = escolaridadeController.findEscolaridadesByPaciente(id_paciente);
						
						for (Escolaridade escolaridade : escolaridades) {
							System.out.println(escolaridade);
						}
					}catch(Exception e) {
						continueWhile = true;
						System.err.println("Código inválido, tente novamente");
					}
				break;
				case 4:
					
					List<Regiao> regioes = regiaoController.findAllRegioes();
					
					for (Regiao regiao : regioes) {
						System.out.println(regiao.getIdregiao() + " - " + regiao.getCidade() + " - " + regiao.getBairro() + " - Qd" + regiao.getQuadra() + " - Lt" + regiao.getLote());
					}
					
					System.out.print("Escolha uma região para buscar os pacientes próximos: ");
					int id_regiao = scanner.nextInt();
					
					pacientes = pacienteController.findPacientesByRegiao(id_regiao);
					
					System.out.println("Resultados Encontrados: ");
					
					for (Paciente paciente : pacientes) {
						System.out.println(paciente);
					}
				break;
				
				case 5:
					
					pacientes = pacienteController.findAllPacientes();
					
					for (Paciente paciente : pacientes) {
						System.out.println(paciente.getId_paciente() + " - " + paciente.getNome());
					}
					
					System.out.println("Selecione um ID de paciente para buscar o histórico");
					
					List<HistoricoVacinasDto> historicoVacinasDtos = new ArrayList<>();
					
					int paciente_id = scanner.nextInt();
					
					historicoVacinasDtos = pacienteController.findHistoricoVacinacaoByIdUser(paciente_id);
					
					for (HistoricoVacinasDto historicoVacinasDto : historicoVacinasDtos) {
						System.out.println(historicoVacinasDto);
					}
				break;
				case 6:
					
					System.out.println("Cadastro de usuário requisitado");
					
					scanner.nextLine();
					
					Paciente paciente = new Paciente();
					System.out.print("Nome do Paciente: ");
					paciente.setNome(scanner.nextLine());
					
					System.out.println("Idade do paciente: ");
					paciente.setIdade(scanner.nextInt());
					
					System.out.println("Endereço do paciente: ");
					paciente.setEndereco(scanner.nextLine());
					
					scanner.nextLine();
					
					System.out.println("Telefone do paciente: ");
					paciente.setTelefone(scanner.nextLine());
					
					regioes = regiaoController.findAllRegioes();
					
					for (Regiao regiao : regioes) {
						System.out.println(regiao.getIdregiao() + " - " + regiao.getCidade() + " - " + regiao.getBairro() + " - Qd" + regiao.getQuadra() + " - Lt" + regiao.getLote());
					}
					
					System.out.print("Qual a região que nosso paciente mora?");
					
					id_regiao = scanner.nextInt();
					
					paciente.setId_regiao(id_regiao);
					
					pacienteController.cadastrarPaciente(paciente);
				break;
				case 7:
					Menuinteracao menuinteracao = new Menuinteracao(connection);
					menuinteracao.menudeinteracao(scanner);
				break;
				default:
					continueWhile = true;
					System.err.println("Código Inválido - Opções de 1 até 7");
					continue;
			}
		}
	}
}
