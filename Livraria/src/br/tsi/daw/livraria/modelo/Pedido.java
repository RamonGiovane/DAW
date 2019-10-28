	package br.tsi.daw.livraria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private List<Livro> livros;
	private Long quantidade;
	private double preco;
	private Long codigo;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Pedido() {
		livros = new ArrayList<Livro>();
	}
	public List<Livro> getLivros() {
		return livros;
	}
	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public void adicionarLivro(Livro livro) {
		livros.add(livro);
	}
	
	

}
