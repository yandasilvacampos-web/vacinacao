package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Doenca;

public class DoencaService {

	private Connection connection;
	
	public DoencaService(Connection connection) {
		
		this.connection = connection;
	}
	
	public List<Doenca> findAll(){
		
		String sql = "SELECT * FROM DOENCA";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Doenca> doencas = new ArrayList<>();
			
			while(resultSet.next()) {
				
				Doenca doenca = new Doenca();
				
				doenca.setId_doenca(resultSet.getInt(0));
				doenca.setNomedoenca(resultSet.getString(1));
				doenca.setId_vacina(resultSet.getInt(2));
				
				doencas.add(doenca);
			}
			
			return doencas;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar procurar todas as doenças");
		}
	}
	
	public List<Doenca> findByNome(String nome) {
		
		String sql = "SELECT * FROM DOENCA WHERE DOENCA LIKE '%?%'";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, nome);
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Doenca> doencas = new ArrayList<>();
			
			while(resultSet.next()) {
				
				Doenca doenca = new Doenca();
				doenca.setId_doenca(resultSet.getInt(0));
				doenca.setNomedoenca(resultSet.getString(1));
				doenca.setId_vacina(resultSet.getInt(2));
				
				doencas.add(doenca);
			}
			
			return doencas;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar procurar a doença pelo nome");
		}
	}
}
