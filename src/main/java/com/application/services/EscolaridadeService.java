package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Escolaridade;

public class EscolaridadeService {

	private Connection connection;
	
	public EscolaridadeService(Connection connection) {
		
		this.connection = connection;
	}
	
	public List<Escolaridade> findEscolaridadesByIdUser(int id){
		
		String sql = "SELECT * FROM ESCOLARIDADE WHERE ID_PACIENTE = ?";
		
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setInt(1, id);
			
			ResultSet resultSet = preparedStatement.getResultSet();
			
			List<Escolaridade> escolaridades = new ArrayList<>();
			
			while(resultSet.next()) {
			
				Escolaridade escolaridade = new Escolaridade();
				
				escolaridade.setId_escolaridade(resultSet.getInt(0));
				escolaridade.setTurma(resultSet.getString(1));
				escolaridade.setEnsino(resultSet.getString(2));
				escolaridade.setAno(resultSet.getInt(3));
				escolaridade.setId_paciente(resultSet.getInt(4));
				
				escolaridades.add(escolaridade);
			}
			
			return escolaridades;
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar buscar escolaridade de usuário");
		}
	}
}
