package br.tsi.daw.livraria.modelo;

import javax.validation.constraints.NotNull;

public class Livro implements Comparable<Livro> {
	//código, título, um autor, uma descrição e uma categoria.
	@NotNull
	private Long codigo;
	private String titulo, descricao;
	private String autor;
	private Categoria categoria;
	private double preco;
	
	private int quantidade;
	
	public Livro() {
		quantidade = 1;
	}
	

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	@Override
	public int compareTo(Livro livro) {
		return Long.compare(livro.getCodigo(), this.codigo);
	}


	public void adicionarQuantidade() {
		quantidade++;
	}


	public void diminuirQuantidade() {
		quantidade--;
		
	}
	 
	
}
