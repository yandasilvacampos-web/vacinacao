package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.application.entities.Vacina;
import com.application.services.VacinaService;

public class VacinaController {
    
    private Connection connection;
    private VacinaService vacinaService;

    public VacinaController(Connection connection) {
        this.connection = connection;
        this.vacinaService = new VacinaService(connection);
    }
    
    public List<Vacina> findAllVacinas() {
        
        return vacinaService.listarTodas();
    }
    
    public List<Vacina> findVacinasByNome(String nome) {
     
        return vacinaService.buscarPorNome(nome);
    }
}