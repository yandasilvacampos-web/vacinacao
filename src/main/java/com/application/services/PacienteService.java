package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import com.application.entities.Paciente;

public class PacienteService {
      
	private Connection connection;
	
	public PacienteService(Connection connection) {
		this.connection = connection;
	}
	public List<Paciente> findAll(){
		String sql = "SELECT * FROM PACIENTE";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			ResultSet resultSet = preparedStatement.executeQuery();
			List<Paciente> pacientes = new ArrayList<Paciente>();
			
			while(resultSet.next()) {
				Paciente paciente = new Paciente();
				paciente.setId_paciente(resultSet.getInt(0));
				paciente.setNome(resultSet.getString(1));
				paciente.setIdade(resultSet.getInt(2));
				paciente.setEndereco(resultSet.getString(3));
				paciente.setTelefone(resultSet.getString(4));
				
				pacientes.add(paciente);
									
			}
			
			return pacientes;
					
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar as Strings");
		}		
	}
	public Paciente findByNome(String nome) {
		
		String sql = "SELECT * FROM PACIENTE WHERE NOME LIKE '%?%'";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, nome);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			Paciente paciente = new Paciente();
			
			while(resultSet.next()) {
				paciente.setId_paciente(resultSet.getInt(0));
				paciente.setNome(resultSet.getString(1));
				paciente.setIdade(resultSet.getInt(2));
				paciente.setEndereco(resultSet.getString(3));
				paciente.setTelefone(resultSet.getString(4));
				
			}
			
			return paciente;
							
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Um erro ao tentar as Strings");
		}
				
	}
}
