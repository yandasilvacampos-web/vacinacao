package com.application.dtos;

public class HistoricoVacinasDto {

	private String nome_vacina;
	private String nome_paciente;
	
	public String getNome_vacina() {
		return nome_vacina;
	}
	public void setNome_vacina(String nome_vacina) {
		this.nome_vacina = nome_vacina;
	}
	public String getNome_paciente() {
		return nome_paciente;
	}
	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}
	@Override
	public String toString() {
		return "HistoricoVacinasDto [nome_vacina=" + nome_vacina + ", nome_paciente=" + nome_paciente + "]";
	}
}
