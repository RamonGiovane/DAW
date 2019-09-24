package br.tsi.daw.modelo;

import java.util.Calendar;

public class Emprestimo {
	private Long id;
	private Long idAmigo;
	private Long idRevista;
	
	private Calendar dataEmprestimo, dataDevolucao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAmigo() {
		return idAmigo;
	}

	public void setIdAmigo(Long idAmigo) {
		this.idAmigo = idAmigo;
	}

	public Long getIdRevista() {
		return idRevista;
	}

	public void setIdRevista(Long idRevista) {
		this.idRevista = idRevista;
	}

	public Calendar getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Calendar dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Calendar getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Calendar dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
}
