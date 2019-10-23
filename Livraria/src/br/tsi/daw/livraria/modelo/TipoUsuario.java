package br.tsi.daw.livraria.modelo;

public enum TipoUsuario {
	USER("USER"), ADMIN("ADMIN"), GERENTE("GERENTE");
	
	private String descricao;

	
	private TipoUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
