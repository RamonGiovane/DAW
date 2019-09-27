package br.tsi.daw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.tsi.daw.modelo.Caixa;
import br.tsi.daw.modelo.Revista;

public class RevistaDAO extends DAO {
	
	public RevistaDAO() throws ClassNotFoundException, SQLException {
		novaConexao();
	}
	
	public void adiciona(Revista revista) throws SQLException {
		String sql = "insert into revista (colecao, numedicao, anorevista, "
				+ "idcaixa, disponibilidade) values (?, ?, ?, ?, ?)";
				
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setString(1, revista.getColecao());
		preparedStatement.setInt(2, revista.getNumeroEdicao());
		preparedStatement.setInt(3, revista.getAno());
		preparedStatement.setLong(4, revista.getIdCaixa());
		preparedStatement.setBoolean(5, true);
		System.out.printf("Query SQL: \ninsert into revista (colecao, numedicao, anorevista, "
				+ "idcaixa, disponibilidade)\nvalues (%s, %s, %s, %s, %s)\n", revista.getColecao(), revista.getNumeroEdicao(),
				revista.getAno(), revista.getIdCaixa(), true);
		executar();
	}
	
	public void adiciona(Caixa caixa) throws SQLException {
		String sql = "insert into caixa (cor) values (?)";
				
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setString(1, caixa.getCor());
		
		executar();
	}

	public void alterarDisponibilidade(Long idRevista, boolean disponibilidade) throws SQLException {
		String sql = "update revista set disponibilidade = ? where idrevista = ?";
		
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setBoolean(1, disponibilidade);
		preparedStatement.setLong(2, idRevista);
		
		executar();
	
	}
	
	public boolean verificarDisponibilidade(Long idRevista) throws SQLException {
		String sql = "select disponibilidade from revista where idrevista = ?";
		
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setLong(1, idRevista);
		
		try(ResultSet rs = executarQuery()){
			return rs.getBoolean("disponibilidade");
		}
		
	}
	
	public List<Revista> getRevistasDisponiveis() throws SQLException{
		String sql = "select * from revista where disponibilidade = true";
		List<Revista> lista = new ArrayList<Revista>();
		preparedStatement = conexao.prepareStatement(sql);
		
		try(ResultSet rs = executarQuery()){
			while(rs.next()) {
				lista.add(obterRevista(rs));
			}
		}
		
		return lista;
	}
	
	public List<Revista> getRevistasEmprestadas() throws SQLException{
		String sql = "select * from revista where disponibilidade = false";
		List<Revista> lista = new ArrayList<Revista>();
		preparedStatement = conexao.prepareStatement(sql);
		
		try(ResultSet rs = executarQuery()){
			while(rs.next()) {
				lista.add(obterRevista(rs));
			}
		}
		
		return lista;
	}
	
	public List<Revista> getRevistasAmigo(Long idAmigo) throws SQLException{
		String sql = "select * from revista where idamigo = ?";
		List<Revista> lista = new ArrayList<Revista>();
		
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setLong(1, idAmigo);
		
		try(ResultSet rs = executarQuery()){
			while(rs.next()) {
				lista.add(obterRevista(rs));
			}
		}
		
		return lista;
	}
	
	
	
	
	private Revista obterRevista(ResultSet rs) throws SQLException {
		Revista r = new Revista();
		r.setAno(rs.getInt("anorevista"));
		r.setColecao(rs.getString("colecao"));
		r.setId(rs.getLong("idrevista"));
		r.setIdCaixa(rs.getLong("idcaixa"));
		r.setDisponibilidade(rs.getBoolean("disponibilidade"));
		r.setNumeroEdicao(rs.getInt("numedicao"));		
		return r;
	}
	
}
