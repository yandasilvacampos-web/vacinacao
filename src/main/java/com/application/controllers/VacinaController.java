package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Vacina;
import com.application.services.VacinaService;

public class VacinaController {

	private Connection connection;
	
	public VacinaController(Connection connection) {
		this.connection = connection;
	}
	
	private VacinaService vacinaService = new VacinaService(connection);
	
	public List<Vacina> findAllVacinas() {
		List<Vacina> vacinas = new ArrayList<>();
		vacinas = vacinaService.findAll();
		return vacinas;
	}
	
	public List<Vacina> findVacinasByNome(String nome){
		List<Vacina> vacinas = new ArrayList<>();
		vacinas = vacinaService.findByNome(nome);
		return vacinas;
	}
}
