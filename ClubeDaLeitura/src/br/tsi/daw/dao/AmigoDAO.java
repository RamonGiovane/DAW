package br.tsi.daw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import br.tsi.daw.modelo.Amigo;
import br.tsi.daw.modelo.Emprestimo;

public class AmigoDAO extends DAO{

	public void adiciona(Amigo amigo) throws SQLException {

		String sql = "insert into amigo (nome, telefone) values(?, ?)";
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setString(1, amigo.getNome());
		preparedStatement.setString(2, amigo.getTelefone());

		executar();

	}

	public List<Emprestimo> pesquisa(String amigo) throws SQLException, ParseException {
		String sql = "select * from emprestimo inner join amigo on (amigo.idamigo = emprestimo.idamigo) where amigo.nome = ?";
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		preparedStatement = conexao.prepareStatement(sql);
		preparedStatement.setString(1, amigo);
		Emprestimo emprestimo;
		try(ResultSet rs = preparedStatement.executeQuery()){
			while(rs.next()) {
				emprestimo = new Emprestimo();
				emprestimo.setId(rs.getLong("idemprestimo"));
				emprestimo.setDataDevolucao(DAO.sqlDateToCalendar(rs.getDate("datadevolucao")));
				emprestimo.setIdAmigo(rs.getLong("idamigo"));
				emprestimo.setIdRevista(rs.getLong("idrevista"));
				lista.add(emprestimo);

			}
		}

		return lista;
	}
}
