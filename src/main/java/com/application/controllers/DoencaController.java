package com.application.controllers;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import com.application.entities.Doenca;
import com.application.entities.Paciente;
import com.application.entities.Vacina;
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

    
    public List<Paciente> listarInfectados(int idDoenca) {
        return doencaService.listarPacientesInfectados(idDoenca);
    }
    
    public Vacina findVacinaReferente(int id_doenca) {
    	
    	Vacina vacina = doencaService.findVacinaReferente(id_doenca);
    	return vacina;
    }
    
    public void cadastrarNovaDoenca(String nomeDoenca, int vacina) {
    	
    	doencaService.cadastrar(nomeDoenca, vacina);
    }
    
    public void atualizarVacinaReferente(int doenca_id, int vacina_id_referente) {
    	
    	doencaService.atualizarVacinaReferente(doenca_id, vacina_id_referente);
    }
}