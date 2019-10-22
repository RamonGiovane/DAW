package br.tsi.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tsi.daw.livraria.jdbc.ConnectionFactory;
import br.tsi.daw.livraria.modelo.Categoria;
import br.tsi.daw.livraria.modelo.Livro;

public class LivrariaDAO {

	private Connection connection;

	public LivrariaDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public List<Categoria> getCategorias(){
		String sql= "select * from categoria";
		List<Categoria> categorias = null;


		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			categorias = new ArrayList<Categoria>();
			while(rs.next()) {
				categorias.add(popularCategoria(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;

	}

	private Categoria getCategoria(long codigo) {
		String sql= "select * from categoria where codigo = ?";



		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, codigo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return popularCategoria(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	public List<Livro> getLivros(String nomeLivro) {
		String sql= "select * from livro where titulo ILIKE '%?%'";
		List<Livro> categorias = null;


		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, nomeLivro);

			ResultSet rs = stmt.executeQuery();
			categorias = new ArrayList<Livro>();
			while(rs.next()) {
				categorias.add(poularLivro(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;
	}

	private Livro poularLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setAutor(rs.getString("autor"));
		livro.setCategoria(getCategoria(rs.getLong("codcategoria")));
		livro.setCodigo(rs.getLong("codigo"));
		livro.setDescricao(rs.getString("descricao"));
		livro.setTitulo(rs.getString("titulo"));
		return livro;
	}



	private Categoria popularCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setDescricao(rs.getString("descricao"));
		categoria.setId(rs.getLong("codigo"));
		return categoria;
	}
}
