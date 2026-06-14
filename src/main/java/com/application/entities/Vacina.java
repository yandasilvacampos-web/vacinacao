package com.application.entities;

public class Vacina {
	
	private Integer id_vacina;
	private String tipo;
	private String fabricante;
	private String nome;
	
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
	@Override
	public String toString() {
		return "Vacina [id_vacina=" + id_vacina + ", tipo=" + tipo + ", fabricante=" + fabricante + ", nome=" + nome
				+ "]";
	}
}
