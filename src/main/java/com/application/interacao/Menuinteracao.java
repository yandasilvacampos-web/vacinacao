package com.application.interacao;

import java.sql.Connection;
import java.util.Scanner;

public class Menuinteracao{
    
    private Connection connection;
    
    public Menuinteracao (Connection connection) {
    	this.connection = connection;
    }
    
    public void menudeinteracao(Scanner scanner) {
    	
    	int escolha = 0;
    	
    	boolean codigo_invalido = true;
    	
    	while (codigo_invalido) {
    	
    		codigo_invalido = false;
    	
	    	System.out.println("1 - Pacientes:");
	    	System.out.println("2 - Doenças:");
	    	System.out.println("3 - Vacinas: ");
	    	
	    	escolha = scanner.nextInt();
	    	
	    	switch (escolha) {
	    	
	    	case 1 :
	    		PacienteMenu.menu(connection, scanner);
	    	break;
	    	case 2 : 
	    		DoencaMenu.menu(connection, scanner);
	    	break;
	    	case 3 :
	    		VacinaMenu.menu(connection, scanner);
	    	break;
	    	
	    	default:
	    		System.out.println("Código Inválido, tente novamente");
	            codigo_invalido = true;
	    	}
    	}
    	
    	scanner.close();
    }

  

}
