package br.tsi.distribuidora.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.tsi.distribuidora.dao.UsuarioDAO;
import br.tsi.distribuidora.modelo.Usuario;

@SessionScoped
@ManagedBean
public class UsuarioBean {
	private Usuario usuario = new Usuario();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String efetuaLogin() {
		UsuarioDAO dao = new UsuarioDAO();
		boolean loginValido = dao.existeUsuario(usuario);
		if(loginValido)
			return "produto";
		else {
			usuario = new Usuario();
			return "login";
		}
	}
	
	public String logout() {
		usuario = new Usuario();
		
		return "login?faces-redirect=true";
	}
	
	public boolean isUsuarioLogado() {
		return usuario.getNome() != null;
	}
}
