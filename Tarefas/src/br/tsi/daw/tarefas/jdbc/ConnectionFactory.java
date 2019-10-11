package br.tsi.daw.tarefas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection
					("jdbc:postgresql://localhost:5432/tarefa", "postgres", "postgres");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
