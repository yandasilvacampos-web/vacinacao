package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Escolaridade;
import com.application.services.EscolaridadeService;

public class EscolaridadeController {

	private Connection connection;
	
	public EscolaridadeController(Connection connection) {
		this.connection  = connection;
	}
	
	private EscolaridadeService escolaridadeService = new EscolaridadeService(connection);
	
	public List<Escolaridade> findEscolairdadesByPaciente(int idPaciente){
		List<Escolaridade> escolaridades = new ArrayList<>();
		escolaridades = escolaridadeService.findEscolaridadesByIdUser(idPaciente);
		return escolaridades;
	}
}
