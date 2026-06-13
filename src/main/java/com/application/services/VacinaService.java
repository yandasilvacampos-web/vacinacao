package com.application.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.entities.Vacina;

public class VacinaService {
    private Connection connection;

    
    public VacinaService(Connection connection) {
        this.connection = connection;
    }

 
    public List<Vacina> listarTodas() {
        List<Vacina> vacinas = new ArrayList<>();
        String query = "SELECT id_vacina, tipo, fabricante, nome FROM public.vacina;";
        Statement statement;
        
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vacinas.add(mapRowToVacina(rs));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar as vacinas: " );
        }
        return vacinas;
    }

  
    public List<Vacina> buscarPorNome(String nome) {
        List<Vacina> vacinas = new ArrayList<>();
        Statement statement;
        
        try {
            String query = String.format(
                "SELECT id_vacina, tipo, fabricante, nome FROM public.vacina WHERE nome  's';", 
                nome
            );
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vacinas.add(mapRowToVacina(rs));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar a vacina '" + nome + "': ");
        }
        return vacinas;
    }


    public void cadastrar(Vacina vacina) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.vacina (tipo, fabricante, nome) VALUES ('%s', '%s', '%s');",
                vacina.getTipo(), vacina.getFabricante(), vacina.getNome()
            );
            
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Vacina inserida com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao tentar cadastrar a vacina: ");
        }
    }


    public void registrarVacinacao(int idPaciente, int idVacina) {
        Statement statement;
        try {
            String query = String.format(
                "INSERT INTO public.registro_vacinas (id_paciente, id_vacina) VALUES (%d, %d);",
                idPaciente, idVacina
                
                
            );
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Vacinação registrada no histórico.");
        } catch (Exception e) {
            System.out.println("Erro ao salvar o registro de vacinação:");
        }
    }

   
    public List<String> listarVacinasDoPaciente(int idPaciente) {
        List<String> historico = new ArrayList<>();
        Statement statement;
        
        try {
            String query = String.format(
                "SELECT p.nome AS paciente_nome, v.nome AS vacina_nome FROM public.registro_vacinas rv " +
                "INNER JOIN public.paciente p ON p.id_paciente = rv.id_paciente " +
                "INNER JOIN public.vacina v ON v.id_vacina = rv.id_vacina " +
                "WHERE rv.id_paciente = %d;", 
                idPaciente
            );
            
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            
            while (resultset.next()) {
            
                String linhaRelatorio = "Paciente: " + resultset.getString("paciente_nome") + 
               "Vacina Aplicada: " + resultset.getString("vacina_nome");
                historico.add(linhaRelatorio);
            }
        } catch (Exception e) {
            System.out.println("Erro  histórico do paciente: " );
        }
        return historico;
    }

  
    private Vacina mapRowToVacina(ResultSet rs) throws Exception {
        Vacina v = new Vacina();
        v.setId_vacina(rs.getInt("id_vacina"));
        v.setTipo(rs.getString("tipo"));
        v.setFabricante(rs.getString("fabricante"));
        v.setNome(rs.getString("nome"));
        return v;
    }
}