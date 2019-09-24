package br.tsi.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.tsi.daw.jdbc.ConnectionFactory;

public abstract class DAO {
	protected Connection conexao;
	protected PreparedStatement preparedStatement;
	

	protected void novaConexao() throws ClassNotFoundException, SQLException {
		conexao =  new ConnectionFactory().getConnection();
	}
	
	protected void executar() throws SQLException {
		preparedStatement.execute();
		preparedStatement.close();
	}
	
	protected void fecharConexao() throws SQLException {
		conexao.close();
	}
	
	protected ResultSet executarQuery() throws SQLException {
		ResultSet rs = preparedStatement.executeQuery();
		
		return rs;
	}
	protected Date calendarToDate(Calendar cal) {
		return new Date(cal.getTimeInMillis());
	}
}
