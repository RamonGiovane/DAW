package br.tsi.daw.livraria.modelo;

public class Livro {
	//código, título, um autor, uma descrição e uma categoria.
	private Long codigo;
	private String titulo, descricao, categoria;
	private String autor;

	
	
	
	public Livro() { }
	public Livro(Long codigo, String titulo, String descricao, String categoria, String autor) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.autor = autor;
	}
	
	public Livro(String titulo, String descricao, String categoria, String autor) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.autor = autor;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
