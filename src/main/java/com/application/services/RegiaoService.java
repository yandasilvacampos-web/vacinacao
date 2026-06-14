package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public List<Regiao> regioes() {
    	
    	String sql = "SELECT * FROM REGIAO";
    	
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		
    		List<Regiao> regioes = new ArrayList<>();
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			
    			Regiao regiao = new Regiao();
    			
    			regiao.setIdregiao(resultSet.getInt("id_regiao"));
    			regiao.setCidade(resultSet.getString("cidade"));
    			regiao.setEstado(resultSet.getString("estado"));
    			regiao.setBairro(resultSet.getString("setor"));
    			regiao.setQuadra(resultSet.getString("quadra"));
    			regiao.setLote(resultSet.getString("lote"));
    			
    			regioes.add(regiao);
    		}
    		
    		return regioes;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Um erro ao tentar buscar todas as regiões");
    	}
    }
}