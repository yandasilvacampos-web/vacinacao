package com.application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.entities.Escolaridade; 

public class EscolaridadeService {
    private Connection connection;

    public EscolaridadeService(Connection connection) {
        this.connection = connection;
    }

    public List<Escolaridade> buscarPorIdPaciente(int idPaciente) {
        List<Escolaridade> lista = new ArrayList<>();
        Statement statement;
        
        try {
            String query = String.format(
                "SELECT id_escolaridade, turma, ensino, ano, id_paciente FROM public.escolaridade WHERE id_paciente = %d;", 
                idPaciente
            );
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
                lista.add(mapRowToEscolaridade(rs));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar escolaridade do paciente: " );
        }
        return lista;
    }

    public void cadastrar(Escolaridade escolaridade) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.escolaridade (id_escolaridade, turma, ensino, ano, id_paciente) VALUES (%d, '%s', '%s', %d, %d);",
                escolaridade.getId_escolaridade(),
                escolaridade.getTurma(),
                escolaridade.getEnsino(),
                escolaridade.getAno(),
                escolaridade.getId_paciente()
            );
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Escolaridade cadastrada com sucesso!");
            
        } catch (Exception e) {
            System.out.println("Erro ao inserir escolaridade: " );
        }
    }

    
    private Escolaridade mapRowToEscolaridade(ResultSet rs) throws Exception {
        Escolaridade esc = new Escolaridade();
        esc.setId_escolaridade(rs.getInt("id_escolaridade"));
        esc.setTurma(rs.getString("turma"));
        esc.setEnsino(rs.getString("ensino"));
        esc.setAno(rs.getInt("ano"));
        esc.setId_paciente(rs.getInt("id_paciente"));
        return esc;
    }
}