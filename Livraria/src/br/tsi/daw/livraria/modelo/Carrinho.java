package br.tsi.daw.livraria.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {
	private static List<Livro> livros =  new ArrayList<Livro>();
	
	public static void adicionarLivro(Livro livro) {
		livros.add(livro); 
	}
	
	public static double getValorTotal() {
		double valor = 0;
		for(Livro livro : livros)
			valor += livro.getPreco();
		return valor;
	}
	
	public static void removerLivro(Livro livro) {
		for(int i =0; i<livros.size(); i++) {
			if(livro.getCodigo() == livros.get(i).getCodigo()) {
				livros.remove(i);
				break;
			}
		}
	}
	
	public static List<Livro> getLivros() {
		return Collections.unmodifiableList(livros);
	}
	
	public static void limparCarrinho() {
		livros.clear();
	}
	
}
