package com.application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.entities.Regiao;

public class RegiaoService {

    private Connection connection;
    
  
    public RegiaoService(Connection connection) {
        this.connection = connection;
    }

    public List<Regiao> findRegioesByIdUser(int idPaciente) {
        List<Regiao> listaDeRegioes = new ArrayList<>();
        Statement Statement;
        
        try {
       
            String query = String.format(
                "SELECT id_regiao, cidade, estado, bairro, setor, quadra, lote, id_paciente " +
                "FROM public.regiao WHERE id_paciente = %d;", 
                idPaciente
            );
            
            Statement = connection.createStatement();
            ResultSet ResultSet= Statement.executeQuery(query); 
            
            
            while (ResultSet.next()) {
                Regiao regiao = new Regiao();
    
                regiao.setCidade(ResultSet.getString("cidade"));
                regiao.setEstado(ResultSet.getString("estado"));
                regiao.setBairro(ResultSet.getString("bairro"));
                regiao.setQuadra(ResultSet.getString("quadra"));
                regiao.setLote(ResultSet.getString("lote"));              
            
                listaDeRegioes.add(regiao);
            }
            
        } catch (Exception e) {
           
            System.out.println("Erro buscar a região do paciente " + idPaciente + ": ");
        }
        
        return listaDeRegioes;
    }
}