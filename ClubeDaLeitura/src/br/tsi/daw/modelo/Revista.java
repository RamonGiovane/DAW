package br.tsi.daw.modelo;

public class Revista {
	private Long id;
	private String colecao;
	private int numeroEdicao, ano;
	private boolean disponibilidade;
	private Long idCaixa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColecao() {
		return colecao;
	}
	public void setColecao(String colecao) {
		this.colecao = colecao;
	}
	public int getNumeroEdicao() {
		return numeroEdicao;
	}
	public void setNumeroEdicao(int numeroEdicao) {
		this.numeroEdicao = numeroEdicao;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public boolean isDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public Long getIdCaixa() {
		return idCaixa;
	}
	public void setIdCaixa(Long idCaixa) {
		this.idCaixa = idCaixa;
	}
	
	
}
