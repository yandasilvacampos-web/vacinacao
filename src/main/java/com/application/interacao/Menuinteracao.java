package com.application.interacao;

import java.sql.Connection;
import java.util.Scanner;
import com.application.controllers.DoencaController;
import com.application.controllers.PacienteController;
import com.application.controllers.VacinaController;
import com.application.controllers.EscolaridadeController;

public class Menuinteracao{
    

    private  Connection connection;
    
    
    private DoencaController doencaController;
    private PacienteController pacienteController;
    private VacinaController vacinaController;
    private EscolaridadeController escolaridadeController;
    
 
    public Menuinteracao () {
    	
      	
    	this.doencaController = new DoencaController(this.connection);
        this.pacienteController = new PacienteController(this.connection);
        this.vacinaController = new VacinaController(this.connection);
        this.escolaridadeController = new EscolaridadeController(this.connection);    	
    }
    
    public void menudeinteracao() {
    	Scanner scanner = new Scanner(System.in);
    	int escolha = 0;
    	
    	while (escolha !=6);
    	
    	System.out.println("Menu principal :");
    	System.out.println("Doença :");
    	System.out.println("Paciente :");
    	System.out.println("Vacina : ");
    	System.out.println("Escolariedade :");
    
    	
    	switch (escolha) {
    	
    	case 1 :
    		System.out.println("Paciente :");
    	break;
    	case 2 : 
    		System.out.println("Doença : ");
    	break;
    	case 3 :
    		System.out.println("Vacinaçao : ");
    	break;
    	case 4 : 
    		System.out.println("Escolariedade :");
    	break;
    	case 5 : 
    		System.out.println("Sair do Sistema?");
    	break;
    	
    	default:
    		System.out.println("Invalido");
            
    	}
    	
    	scanner.close();
    }

  

}
