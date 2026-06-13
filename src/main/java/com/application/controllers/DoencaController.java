package com.application.controllers;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.application.entities.Doenca;
import com.application.services.DoencaService;

public class DoencaController {
    
    private Connection connection;
    private DoencaService doencaService; 

    public DoencaController(Connection connection) {
        this.connection = connection;
        
        this.doencaService = new DoencaService(connection);
    }
    
    public List<Doenca> findAllDoencas() {
        List<Doenca> doencas = doencaService.listarTodas();
        return doencas;
    }
    
    public void cadastrarDoenca(String nomeDoenca, int idVacina) {
        doencaService.cadastrar(nomeDoenca, idVacina);
    }

    
    public List<String> listarInfectados(int idDoenca) {
        return doencaService.listarPacientesInfectados(idDoenca);
    }
}