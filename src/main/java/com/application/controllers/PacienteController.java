package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.application.dtos.HistoricoVacinasDto;
import com.application.entities.Paciente;
import com.application.services.PacienteService;
import com.application.services.VacinaService;

public class PacienteController {
	
	private Connection connection;
	private PacienteService pacienteService;
	private VacinaService vacinaService;
	
	public PacienteController(Connection connection) {
		this.connection = connection;
		this.pacienteService = new PacienteService(connection);
		this.vacinaService = new VacinaService(connection);
	}

	
	public List<Paciente> findAllPacientes(){
		List<Paciente> pacientes = new ArrayList<>();
		pacientes = pacienteService.findAll();
		return pacientes;
	}
	
	public List<Paciente> findPacientesByNome(String nome){
		List<Paciente> pacientes = new ArrayList<>();
		pacientes = pacienteService.findByNome(nome);
		return pacientes;
	}
	
	public List<Paciente> findPacientesByRegiao (int id_regiao) {
		
		List<Paciente> pacientes = new ArrayList<>();
		pacientes = pacienteService.findUsersByRegiao(id_regiao);
		return pacientes;
	}
	
	public List<HistoricoVacinasDto> findHistoricoVacinacaoByIdUser(int id_paciente){
		
		List<HistoricoVacinasDto> historico = new ArrayList<>();
		historico = vacinaService.listarHistoricoVacinasDoPaciente(id_paciente);
		return historico;
	}
	
	public void cadastrarPaciente(Paciente paciente) {
		
		pacienteService.cadastrar(paciente);
	}
}
