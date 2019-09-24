package br.tsi.daw.dao;

import java.sql.SQLException;

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
	
	

}
