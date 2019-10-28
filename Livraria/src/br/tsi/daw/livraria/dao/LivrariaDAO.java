package br.tsi.daw.livraria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import br.tsi.daw.livraria.jdbc.ConnectionFactory;
import br.tsi.daw.livraria.modelo.Carrinho;
import br.tsi.daw.livraria.modelo.Categoria;
import br.tsi.daw.livraria.modelo.Livro;
import br.tsi.daw.livraria.modelo.Pedido;
import br.tsi.daw.livraria.modelo.Usuario;

public class LivrariaDAO {

	private Connection connection;

	public LivrariaDAO() {
		connection = ConnectionFactory.getConnection();
	}

	public List<Categoria> getCategorias(){
		String sql= "select * from categoria";
		List<Categoria> categorias = null;

		PreparedStatement stmt;
		ResultSet rs;
		try {
			stmt = connection.prepareStatement(sql);

			rs = stmt.executeQuery();
			categorias = new ArrayList<Categoria>();
			while(rs.next()) {
				categorias.add(popularCategoria(rs));
			}

			stmt.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return categorias;

	}
	
	

	private Categoria getCategoria(long codigo) {
		String sql= "select * from categoria where codigo = ?";

		Categoria c = null;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setLong(1, codigo);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				c = popularCategoria(rs);
			}

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;

	}
	

	private List<Livro> procurarLivros(PreparedStatement stmt) throws SQLException{
		List<Livro> livros = null;


		ResultSet rs = stmt.executeQuery();
		livros = new ArrayList<Livro>();
		while(rs.next()) {
			livros.add(popularLivro(rs));
		}
		rs.close();
		return livros;

	}
	
	public List<Livro> getLivros() {

		String sql= "select * from livro where estoque > 0";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			List<Livro> livros = procurarLivros(stmt);
			stmt.close();
			return livros;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Pedido> getPedidos(Usuario usuario) {

		String sql= "select * from pedido inner join item_pedido on(codigo_pedido = codigo) where cliente = ? order by codigo";
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Long codigoAnterior = new Long(0), codigo;
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, usuario.getId());
			ResultSet rs = stmt.executeQuery();
			Pedido pedido = new Pedido();
			while(rs.next()) {
				
				codigo = rs.getLong("codigo");
				Livro livro = obterLivro(rs.getLong("codigo_livro"));
				livro.setQuantidade(rs.getInt("quantidade"));
				pedido.setPreco(rs.getDouble("valor"));
				
				pedido.adicionarLivro(livro);
				
				if(codigo != codigoAnterior || rs.isLast()) {
					pedido.setCodigo(codigo);
					pedidos.add(pedido);
					pedido = new Pedido();
				}
				
			}
			stmt.close();
			//return livros;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pedidos;
	}

	public List<Livro> procurarLivros(String nomeLivro) {

		String sql= "select * from livro where titulo ILIKE ? and estoque > 0";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, "%" + nomeLivro + "%");
			List<Livro> livros = procurarLivros(stmt);
			stmt.close();
			return livros;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Livro> procurarLivros(Long codigoCategoria) {

		String sql= "select * from livro where codcategoria = ? and estoque > 0";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, codigoCategoria);
			List<Livro> livros = procurarLivros(stmt);
			stmt.close();
			return livros;

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
		livro.setQuantidade(rs.getInt("estoque"));
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

	public void diminuirLivro(Long codigo, int quantidade) {
		String sql = "update livro set estoque = estoque - ? where codigo=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setInt(1, quantidade);
			stmt.setLong(2, codigo);
			
			stmt.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}		
	}

	public boolean fecharCarrinho(Carrinho carrinho, HttpSession session) {
		if(carrinho.getLivros().isEmpty()) return false;

		try {
			
			Long pedido = gravarPedido(obterUsuarioLogado(session), carrinho.getValorTotal());

			for (Livro livro : carrinho.getLivros()) {
				String sql = "insert into item_pedido (codigo_pedido, codigo_livro, quantidade) values(?, ?, ?)";
				
				diminuirLivro(livro.getCodigo(), livro.getQuantidade());
				
				try (PreparedStatement stmt = connection.prepareStatement(sql)){

					stmt.setLong(1, pedido);
					stmt.setLong(2, livro.getCodigo());
					stmt.setInt(3, livro.getQuantidade());
					

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

	private Long gravarPedido(Usuario usuario, double valor) throws SQLException {
		System.out.println(usuario.getLogin() + " " + usuario.getId());
		String sql = "insert into pedido (cliente, valor) values(?, ?) returning codigo";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			//TODO stmt.setLong(1, CODIGO DO USER AQUI)
			stmt.setLong(1, usuario.getId());
			stmt.setDouble(2, valor);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return rs.getLong("codigo");
			}
		}
		return null;
	}

	public Usuario obterUsuarioLogado(HttpSession session) {
		return (Usuario) session.getAttribute("usuarioLogado");
	}

	public boolean adicionarLivro(Livro livro, Long codCategoria) {
		String sql = "insert into livro (titulo, descricao, autor, preco, quantidade, codcategoria) values(?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			//TODO stmt.setLong(1, CODIGO DO USER AQUI)
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getDescricao());
			stmt.setString(3, livro.getAutor());
			stmt.setDouble(4, livro.getPreco());
			stmt.setDouble(5, livro.getQuantidade());
			stmt.setLong(6, codCategoria);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean adicionarCategoria(Categoria categoria) {
		String sql = "insert into categoria (descricao) values(?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, categoria.getDescricao());

			stmt.execute();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	




}
