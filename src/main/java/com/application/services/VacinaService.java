package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.application.dtos.HistoricoVacinasDto;
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

   
    public List<HistoricoVacinasDto> listarHistoricoVacinasDoPaciente(int idPaciente) {
        Statement statement;
        
        try {
            String query = String.format(
            		"SELECT PACIENTE.NOME AS PACIENTE, VACINA.NOME AS VACINA FROM REGISTRO_VACINAS" +
            		"LEFT JOIN VACINA ON VACINA.ID_VACINA = REGISTRO_VACINAS.ID_VACINA" + 
            		"LEFT JOIN PACIENTE ON PACIENTE.ID_PACIENTE = REGISTRO_VACINAS.ID_PACIENTE" +
            		"WHERE REGISTRO_VACINAS.ID_PACIENTE = %d", 
                idPaciente
            );
            
            statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            
            List<HistoricoVacinasDto> historicoVacinasDtos = new ArrayList<>();
            
            while (resultset.next()) {
            
                HistoricoVacinasDto dto = new HistoricoVacinasDto();
                dto.setNome_paciente(resultset.getString("PACIENTE"));
                dto.setNome_vacina(resultset.getString("VACINA"));
                
                historicoVacinasDtos.add(dto);
            }
            
            return historicoVacinasDtos;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao tentar carregar o histórico de vacinação");
        }
    }
    
    public void deleteById(int vacina_id) {
    	
    	String sql = "DELETE FROM VACINA WHERE ID_VACINA = ?";
    	
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		preparedStatement.setInt(1, vacina_id);
    		preparedStatement.executeUpdate();
    		System.out.println("Vacina deletada com sucesso!");
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Erro ao tentar excluir a nossa vacina");
    	}
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