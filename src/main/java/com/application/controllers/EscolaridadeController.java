package com.application.controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import com.application.entities.Escolaridade;
import com.application.services.EscolaridadeService;

public class EscolaridadeController {

    private Connection connection;
    private EscolaridadeService escolaridadeService; 
    
    public EscolaridadeController(Connection connection) {
        this.connection = connection;
       
        this.escolaridadeService = new EscolaridadeService(connection);
    }
    
    public List<Escolaridade> findEscolaridadesByPaciente(int idPaciente){
        List<Escolaridade> escolaridades = escolaridadeService.buscarPorIdPaciente(idPaciente);
        return escolaridades;
    }

    
    public void cadastrarEscolaridade(Escolaridade escolaridade) {
        escolaridadeService.cadastrar(escolaridade);
    }
}