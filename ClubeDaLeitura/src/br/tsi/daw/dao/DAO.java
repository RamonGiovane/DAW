package br.tsi.daw.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import br.tsi.daw.jdbc.ConnectionFactory;

public abstract class DAO {
	protected Connection conexao;
	protected PreparedStatement preparedStatement;
	
	protected DAO() {
		try {
			novaConexao();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
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
	public static Date calendarToDate(Calendar cal) {
		return new Date(cal.getTimeInMillis());
	}
	
	public static Calendar stringToCalendar(String data) throws ParseException {
		System.out.println(data);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		f.setTimeZone(TimeZone.getDefault());
		java.util.Date date  = f.parse(data);
		cal.setTime(date);
		return cal;
	}
	public static Calendar sqlDateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date(date.getTime()));
		return cal;
	}
}
