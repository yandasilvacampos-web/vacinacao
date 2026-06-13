package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.application.entities.Paciente;
import com.application.services.PacienteService;

public class PacienteController {
	
	private Connection connection;
	
	public PacienteController(Connection connection) {
		this.connection = connection;
	}

	private PacienteService pacienteService = new PacienteService(connection);
	
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
}
