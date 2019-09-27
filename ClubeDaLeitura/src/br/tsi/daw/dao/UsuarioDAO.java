package br.tsi.daw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.tsi.daw.modelo.Usuario;

public class UsuarioDAO extends DAO {

	public boolean autentica(Usuario usuario) throws SQLException {
		
		String sql = "select count(idusuario) from usuario where usuario = ? and senha = ?";
		PreparedStatement preparedStatement = conexao.prepareStatement(sql);
		
		preparedStatement.setString(1, usuario.getUsuario());
		preparedStatement.setString(2, usuario.getSenha());
		
		try(ResultSet rs = preparedStatement.executeQuery()){
			while(rs.next()) {
				return rs.getInt(1) > 0 ? true : false;
			}
		}
		return false;
		
	}
}
