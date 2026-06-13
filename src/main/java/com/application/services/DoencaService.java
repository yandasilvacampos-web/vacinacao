package com.application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.entities.Doenca;

public class DoencaService {
    
    private Connection connection;

    public DoencaService(Connection connection) {
        this.connection = connection;
    }

    public List<Doenca> listarTodas() {
        List<Doenca> doencas = new ArrayList<>();
        String query = "SELECT id_doenca, doenca, id_vacina FROM public.doenca;";
        Statement statement;
        
        try {
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            
            while (resultset.next()) {
                
                doencas.add(mapRowToDoenca(resultset));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar doenças: ");
        }
        return doencas;
    }

    public void cadastrar(String nomeDoenca, int idVacina) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.doenca (doenca, id_vacina) VALUES ('%s', %d);",
                nomeDoenca, idVacina
            );
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Doença inserida com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir doença: " );
        }
    }

    public List<String> listarPacientesInfectados(int idDoenca) {
        List<String> listadedoenca = new ArrayList<>();
        Statement statement;
        try {
            String query = String.format(
                "SELECT p.nome, d.doenca FROM public.registro_doenca rd " +
                "INNER JOIN public.paciente p ON p.id_paciente = rd.id_paciente " +
                "INNER JOIN public.doenca d ON d.id_doenca = rd.id_doenca " +
                "WHERE rd.id_doenca = %d;", 
                idDoenca
            );
            
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            
            while (resultset.next()) {
                listadedoenca.add("Paciente: " + resultset.getString("nome") + " | Doença: " + resultset.getString("doenca"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar pacientes doentes: " );
        }
        return listadedoenca;
    }

    public void registrarDoencaNoPaciente(int idPaciente, int idDoenca) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.registro_doenca (id_paciente, id_doenca) VALUES (%d, %d);",
                idPaciente, idDoenca
            );
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Doença registrada para o paciente com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao registrar doença no paciente: " );
        }
    }

 
    private Doenca mapRowToDoenca(ResultSet rs) throws Exception {
        Doenca d = new Doenca();
        d.setId_doenca(rs.getInt("id_doenca")); 
        d.setId_vacina(rs.getInt("id_vacina"));
        return d;
    }
}