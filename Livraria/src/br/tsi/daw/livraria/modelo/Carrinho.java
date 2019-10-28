package br.tsi.daw.livraria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private  List<Livro> livros =  new ArrayList<Livro>();
	
	public  void adicionarLivro(Livro livro) {
		for(Livro l : livros)
			if(l.getCodigo() == livro.getCodigo()) {
				l.adicionarQuantidade();
				return;
			}
		livro.setQuantidade(1);
		livros.add(livro); 
	}
	
	public  double getValorTotal() {
		double valor = 0;
		for(Livro livro : livros)
			valor += livro.getPreco() * livro.getQuantidade();
		return valor;
	}
	
	public  void removerLivro(Livro livro) {
		if(livro.getQuantidade() > 1) livro.diminuirQuantidade();
		Livro l;
		for(int i =0; i<livros.size(); i++) {
			l = livros.get(i);
			if(l.getCodigo() == livro.getCodigo()) {
				if(l.getQuantidade() > 1) {
					l.diminuirQuantidade();
					return;
				}
				livros.remove(i);
				break;
			}
		}
	}
	
	public List<Livro> getLivros() {
		return livros;
	}
	
	public void limparCarrinho() {
		livros.clear();
	}
	
}
