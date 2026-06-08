package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Vacina;

public class VacinaService {

	private Connection connection;
	
	public VacinaService(Connection connection) {
		
		this.connection = connection;
	}
	
	public List<Vacina> findAll(){
		
		String sql = "SELECT * FROM Vacina";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Vacina> vacinas = new ArrayList<>();
			
			while(resultSet.next()) {
				
				Vacina vacina = new Vacina();
				
				vacina.setId_vacina(resultSet.getInt(0));
				vacina.setTipo(resultSet.getString(1));
				vacina.setFabricante(resultSet.getString(2));
				vacina.setNome(resultSet.getString(3));
				
				vacinas.add(vacina);
			}
			
			return vacinas;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar procurar todas as doenças");
		}
	}
	
	public List<Vacina> findByNome(String nome) {
		
		String sql = "SELECT * FROM Vacina WHERE nome LIKE '%?%'";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, nome);
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Vacina> vacinas = new ArrayList<>();
			
			while(resultSet.next()) {
				
				Vacina vacina = new Vacina();
				
				vacina.setId_vacina(resultSet.getInt(0));
				vacina.setTipo(resultSet.getString(1));
				vacina.setFabricante(resultSet.getString(2));
				vacina.setNome(resultSet.getString(3));
				
				vacinas.add(vacina);
			}
			
			return vacinas;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar procurar a doença pelo nome");
		}
	}
}
