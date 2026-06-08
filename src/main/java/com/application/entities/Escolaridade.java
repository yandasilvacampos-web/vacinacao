package com.application.entities;

public class Escolaridade {
	
	private Integer id_escolaridade;
	private String turma;
	private String ensino;
	private int ano;
	private Integer id_paciente;
	
	public Integer getId_escolaridade() {
		return id_escolaridade;
	}
	public void setId_escolaridade(Integer id_escolaridade) {
		this.id_escolaridade = id_escolaridade;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public String getEnsino() {
		return ensino;
	}
	public void setEnsino(String ensino) {
		this.ensino = ensino;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Integer getId_paciente() {
		return id_paciente;
	}
	public void setId_paciente(Integer id_paciente) {
		this.id_paciente = id_paciente;
	}
	
}
