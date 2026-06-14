package com.application.controllers;

import java.sql.Connection;
import java.util.List;

import com.application.entities.Regiao;
import com.application.services.RegiaoService;

public class RegiaoController {

	private Connection connection;
    private RegiaoService regiaoService; 
    
    public RegiaoController(Connection connection) {
        this.connection = connection;
       
        this.regiaoService = new RegiaoService(connection);
    }
    
    public List<Regiao> findAllRegioes(){
    	
    	List<Regiao> regiaos = regiaoService.regioes();
    	return regiaos;
    }
}
