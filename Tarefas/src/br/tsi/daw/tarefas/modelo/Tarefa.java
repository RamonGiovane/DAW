package br.tsi.daw.tarefas.modelo;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Tarefa {
	private long id;
	
	@NotNull(message="A descrição não pode estar vazia") @Size(min=5, max=20, message="A descrição deve ter entre 5 e 20 caracteres")
	private String descricao;
	
	private boolean finalizado;
	private Calendar datafinalizacao;
	
	public Tarefa() {
		this.finalizado = false;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public Calendar getDatafinalizacao() {
		return datafinalizacao;
	}
	
	public void setDatafinalizacao(Calendar datafinalizacao) {
		this.datafinalizacao = datafinalizacao;
	}
	
	
}
