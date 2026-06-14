package com.application.entities;

public class Regiao {
	
	private Integer idregiao;
	private String cidade;
	private String estado;
	private String bairro;
	private String quadra;
	private String lote;
	
	public Integer getIdregiao() {
		return idregiao;
	}
	public void setIdregiao(Integer idregiao) {
		this.idregiao = idregiao;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getQuadra() {
		return quadra;
	}
	public void setQuadra(String quadra) {
		this.quadra = quadra;
	}
	public String getLote() {
		return lote;
	}
	public void setLote(String lote) {
		this.lote = lote;
	}
	
	@Override
	public String toString() {
		return "Regiao [idregiao=" + idregiao + ", cidade=" + cidade + ", estado=" + estado + ", bairro=" + bairro
				+ ", quadra=" + quadra + ", lote=" + lote + "]";
	}
}
