package br.tsi.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tsi.daw.livraria.jdbc.ConnectionFactory;
import br.tsi.daw.livraria.modelo.Carrinho;
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
	private List<Livro> procurarLivros(PreparedStatement stmt) throws SQLException{
		List<Livro> livros = null;


		ResultSet rs = stmt.executeQuery();
		livros = new ArrayList<Livro>();
		while(rs.next()) {
			livros.add(popularLivro(rs));
		}

		return livros;

	}

	public List<Livro> procurarLivros(String nomeLivro) {

		String sql= "select * from livro where titulo ILIKE ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, "%" + nomeLivro + "%");

			return procurarLivros(stmt);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Livro> procurarLivros(Long codigoCategoria) {

		String sql= "select * from livro where codcategoria = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, codigoCategoria);

			return procurarLivros(stmt);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	private Livro popularLivro(ResultSet rs) throws SQLException {
		Livro livro = new Livro();
		livro.setAutor(rs.getString("autor"));
		livro.setCategoria(getCategoria(rs.getLong("codcategoria")));
		livro.setCodigo(rs.getLong("codigo"));
		livro.setDescricao(rs.getString("descricao"));
		livro.setTitulo(rs.getString("titulo"));
		livro.setPreco(rs.getDouble("preco"));
		return livro;
	}



	private Categoria popularCategoria(ResultSet rs) throws SQLException {
		Categoria categoria = new Categoria();
		categoria.setDescricao(rs.getString("descricao"));
		categoria.setId(rs.getLong("codigo"));
		return categoria;
	}

	public Livro obterLivro(Long codigoLivro) {
		String sql= "select * from livro where codigo = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, codigoLivro);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				return popularLivro(rs);
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean fecharCarrinho() {
		if(Carrinho.getLivros().isEmpty()) return false;
		
		try {
			Long pedido = gravarPedido();
			
			for (Livro livro : Carrinho.getLivros()) {
				String sql = "insert into item_pedido (codigo_pedido, codigo_livro) values(?, ?)";
				
				try (PreparedStatement stmt = connection.prepareStatement(sql)){
					
					stmt.setLong(1, pedido);
					stmt.setLong(2, livro.getCodigo());
					
					stmt.execute();
				}
				
			}
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	private Long gravarPedido() throws SQLException {
		String sql = "insert into pedido (cliente) values(?) returning codigo";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			//TODO stmt.setLong(1, CODIGO DO USER AQUI)
			stmt.setLong(1, 1);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return rs.getLong("codigo");
			}
		}
		return null;
	}




}
