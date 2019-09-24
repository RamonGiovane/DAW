package br.tsi.daw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private final String URL = "jdbc:postgresql://localhost/clube_da_leitura", 
			USER = "postgres",
			PASSWORD = "aluno",
			DRIVER_CLASS_NAME =  "org.postgresql.Driver";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS_NAME);
		return DriverManager.getConnection(URL, USER, PASSWORD);

	}
}
