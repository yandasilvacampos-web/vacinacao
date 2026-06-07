package com.application.entities;

public class Vacina {
	
	private Integer id_vacina;
	private String tipo;
	private String fabricante;
	private String nome;
	private Integer id_doenca;
	
	public Integer getId_vacina() {
		return id_vacina;
	}
	public void setId_vacina(Integer id_vacina) {
		this.id_vacina = id_vacina;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getId_doenca() {
		return id_doenca;
	}
	public void setId_doenca(Integer id_doenca) {
		this.id_doenca = id_doenca;
	}
	
		

}
