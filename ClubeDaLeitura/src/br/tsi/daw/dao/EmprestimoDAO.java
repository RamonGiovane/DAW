package br.tsi.daw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.tsi.daw.modelo.Emprestimo;

public class EmprestimoDAO extends DAO {
	
	public void adicionar(Emprestimo emprestimo) throws SQLException, ClassNotFoundException {
		String sql = "insert into emprestimo (dataemprestimo, datadevolucao, idamigo, idrevista) "
				+ "values (?, ?, ?, ?)";
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setDate(1, calendarToDate(emprestimo.getDataEmprestimo()));
		preparedStatement.setDate(2, calendarToDate(emprestimo.getDataDevolucao()));
		preparedStatement.setLong(3, emprestimo.getIdAmigo());
		preparedStatement.setLong(4, emprestimo.getIdRevista());
		
		executar();
		
		RevistaDAO dao = new RevistaDAO();
		dao.alterarDisponibilidade(emprestimo.getIdRevista(), false);
	}
	
	
	public boolean verificarAmigo(Long idAmigo) throws SQLException {
		String sql = "select count (idamigo) from emprestimo where idamigo = ?";
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setLong(1, idAmigo);
		
		try(ResultSet rs = executarQuery()){
			while(rs.next())
			return rs.getInt(1) > 0 ? false : true;
		
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
		return false;
		
	}
	
//	private Calendar sqlDateToCalendar(java.sql.Date data) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date(data.getTime()));
//		return cal;
//	}
	
	public List<Emprestimo> getEmprestimos() throws SQLException{
		String sql = "select * from emprestimo inner join revista on (emprestimo.idrevista = revista.idrevista)"
				+ " where revista.disponibilidade = false";
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		preparedStatement = conexao.prepareStatement(sql);

		try(ResultSet rs = executarQuery()){
			while(rs.next()) {
				
				lista.add(obterEmprestimo(rs));
			}
		}
		
		return lista;
	}
	
	public Emprestimo obterEmprestimo(ResultSet rs) throws SQLException {
		Emprestimo e = new Emprestimo();
		e.setId(rs.getLong("idemprestimo"));
		e.setDataDevolucao(sqlDateToCalendar(rs.getDate("datadevolucao")));
		e.setDataEmprestimo(sqlDateToCalendar(rs.getDate("dataemprestimo")));
		e.setIdAmigo(rs.getLong("idamigo"));
		e.setIdRevista(rs.getLong("idrevista"));
		
		return e;
	}

	public List<Emprestimo> getEmprestimosAtrasados() throws SQLException{
		String sql = "select * from emprestimo inner join revista on "
				+ " (revista.idrevista = emprestimo.idrevista) where emprestimo.datadevolucao > ? and"
				+ " revista.disponibilidade = false";
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setDate(1, calendarToDate(Calendar.getInstance()));
		
		try(ResultSet rs = executarQuery()){
			while(rs.next()) {
				lista.add(obterEmprestimo(rs));
			}
		}
		
		return lista;
	}

	public void devolver(Emprestimo emprestimo) throws SQLException {
		String sql = "update revista set disponibilidade = ? where idrevista = ?";
		
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setBoolean(1, true);
		preparedStatement.setLong(2, emprestimo.getIdRevista());
		
		preparedStatement.execute();
		preparedStatement.close();
	}
	
	
	

}
