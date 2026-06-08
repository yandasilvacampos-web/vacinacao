package com.application.entities;

public class Doenca {
	
	private int id_doenca;
	private String nomedoenca;
	private int id_vacina;
	
	
	public int getId_vacina() {
		return id_vacina;
	}
	
	public void setId_vacina(int id_vacina) {
		this.id_vacina = id_vacina;
	}
	
	public int getId_doenca() {
		return id_doenca;
	}
	public void setId_doenca(int id_doenca) {
		this.id_doenca = id_doenca;
	}
	public String getNomedoenca() {
		return nomedoenca;
	}
	public void setNomedoenca(String nomedoenca) {
		this.nomedoenca = nomedoenca;
	}
	
}
