package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Doenca;
import com.application.services.DoencaService;

public class DoencaController {
	
	private Connection connection;
	
	public DoencaController(Connection connection) {
		this.connection = connection;
	}

	private DoencaService doencaService = new DoencaService(connection);
	
	public List<Doenca> findAllDoencas() {
		List<Doenca> doencas = new ArrayList<>();
		doencas = doencaService.findAll();
		return doencas;
	}
	
	public List<Doenca> findDoencaByNome(String nome) {
		List<Doenca> doencas = new ArrayList<>();
		doencas = doencaService.findByNome(nome);
		return doencas;
	}
}
