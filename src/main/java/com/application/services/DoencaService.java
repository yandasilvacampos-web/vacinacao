package com.application.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.application.entities.Doenca;
import com.application.entities.Paciente;
import com.application.entities.Vacina;

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
    
    public Vacina findVacinaReferente(int id_doenca){
    	
    	String sql = "select vacina.* from doenca "
    			+ "left join vacina on vacina.id_vacina = doenca.id_vacina where doenca.id_doenca = ?";
    	
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		
    		preparedStatement.setInt(1, id_doenca);
    		
    		Vacina vacina = new Vacina();
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while(resultSet.next()) {
    			vacina.setId_vacina(resultSet.getInt("id_vacina"));
    			vacina.setTipo(resultSet.getString("tipo"));
    			vacina.setFabricante(resultSet.getString("fabricante"));
    			vacina.setNome(resultSet.getString("nome"));
    		}
    		
    		return vacina;
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Um erro ao tentar procurar vacina referente");
    	}
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

    public List<Paciente> listarPacientesInfectados(int idDoenca) {
        List<Paciente> listaPacientes = new ArrayList<>();
        Statement statement;
        try {
            String query = String.format(
                "SELECT PACIENTE.* FROM REGISTRO_DOENCA "
                + "LEFT JOIN DOENCA ON DOENCA.ID_DOENCA = REGISTRO_DOENCA.ID_DOENCA "
                + "LEFT JOIN PACIENTE ON PACIENTE.ID_PACIENTE = REGISTRO_DOENCA.ID_PACIENTE "
                + "WHERE DOENCA.ID_DOENCA = %d", 
                idDoenca
            );
            
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                
            	Paciente paciente = new Paciente();
                paciente.setId_paciente(rs.getInt("id_paciente")); 
                paciente.setNome(rs.getString("nome"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setEndereco(rs.getString("endereco"));
                paciente.setTelefone(rs.getString("telefone"));
            	
                listaPacientes.add(paciente);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new RuntimeException("Erro ao tentar buscar registro de doença");
        }
        return listaPacientes;
    }
    
    public void atualizarVacinaReferente(int id_doenca, int id_vacina_referente) {
    	
    	String sql = "UPDATE DOENCA SET ID_VACINA = ? WHERE ID_DOENCA = ?";
    	
    	try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
    		
    		preparedStatement.setInt(1, id_vacina_referente);
    		preparedStatement.setInt(2, id_doenca);
    		
    		preparedStatement.executeUpdate();
    		System.out.println("Doença atualiza com sucesso!");
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    		throw new RuntimeException("Erro ao tentarmos atualizar a vacina referente");
    	}
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
        d.setNomedoenca(rs.getString("doenca"));
        return d;
    }
}