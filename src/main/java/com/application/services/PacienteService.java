package com.application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.entities.Paciente;

public class PacienteService {
      
    private Connection connection;
    
    public PacienteService(Connection connection) {
        this.connection = connection;
    }
    
  
    public List<Paciente> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
        String query = "SELECT id_paciente, nome, idade, endereco, telefone FROM public.paciente;";
        Statement statement;
        
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
            
                pacientes.add(mapRowToPaciente(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar os pacientes: " );
        }     
        return pacientes;
    }

    public List<Paciente> findByNome(String nome) {
        List<Paciente> pacientes = new ArrayList<>();
        Statement statement;
        
        try {
            String query = String.format(
                "SELECT id_paciente, nome, idade, endereco, telefone FROM public.paciente WHERE nome ILIKE 's';", 
                nome
            );
            
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
               
                pacientes.add(mapRowToPaciente(resultSet));
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar buscar paciente por nome: " );
        }
        return pacientes;
    }

    public void cadastrar(Paciente paciente) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.paciente (nome, idade, endereco, telefone) VALUES ('%s', %d, '%s', '%s');",
                paciente.getNome(),
                paciente.getIdade(),
                paciente.getEndereco(),
                paciente.getTelefone()
            );
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Paciente cadastrado com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao tentar cadastrar novo paciente: " );
        }
    }

    private Paciente mapRowToPaciente(ResultSet rs) throws Exception {
        Paciente paciente = new Paciente();
        paciente.setId_paciente(rs.getInt("id_paciente")); 
        paciente.setNome(rs.getString("nome"));
        paciente.setIdade(rs.getInt("idade"));
        paciente.setEndereco(rs.getString("endereco"));
        paciente.setTelefone(rs.getString("telefone"));
        return paciente;
    }
}