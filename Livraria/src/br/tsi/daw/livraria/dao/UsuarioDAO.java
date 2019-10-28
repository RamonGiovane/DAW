package br.tsi.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tsi.daw.livraria.jdbc.ConnectionFactory;
import br.tsi.daw.livraria.modelo.TipoUsuario;
import br.tsi.daw.livraria.modelo.Usuario;


public class UsuarioDAO {

	private Connection connection;

	public UsuarioDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	public boolean adiciona(Usuario usuario) {
		String sql = "insert into usuario "
				+ "(login, senha, tipo)" 
				+ "values (?, ?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setString(3, usuario.getTipoUsuario().getDescricao());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public void altera(Usuario usuario) {
		String sql = "update usuario set senha=? where login=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, usuario.getSenha());
			stmt.setString(2, usuario.getLogin());
			stmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}		
	}

	public void remove (String login) {
		String sql = "delete from usuario where login=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, login);
			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public Usuario recuperaUsuario (String login) {
		String sql = "select * from usuario where login=?";
	
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return popularUsuario(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	public Usuario validaCredencial(String login, String senha) {
		String sql = "select * from usuario where login=? and senha=?";
		try {
		System.out.println("0");
		PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			ResultSet rs = stmt.executeQuery();
			

			while (rs.next()) {
				
				return popularUsuario(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Usuario> listaUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select * from usuario";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo")));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		return usuarios;
	}
	
	private Usuario popularUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setLogin(rs.getString("login"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setId(rs.getLong("id"));
		usuario.setTipoUsuario(TipoUsuario.valueOf(rs.getString("tipo")));
		return usuario;

	}

}
