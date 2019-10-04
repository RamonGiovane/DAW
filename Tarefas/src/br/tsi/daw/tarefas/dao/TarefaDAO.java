package br.tsi.daw.tarefas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.tsi.daw.tarefas.jdbc.ConnectionFactory;
import br.tsi.daw.tarefas.modelo.Tarefa;

public class TarefaDAO {
	private Connection connection;

	public TarefaDAO() {
		connection = ConnectionFactory.getConnection();
	}

	//Método para adicionar nova tarefa ao banco
	public void adiciona(Tarefa tarefa) {
		String sql= "insert into tarefas (descricao, finalizado) values (?,?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());

			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//Método para adicionar nova tarefa ao banco
	public void finaliza(Long id) {
		String sql= "update tarefas set finalizado = ?, datafinalizado = ? where id = ?";
		
		if(id == null) {
			throw new IllegalStateException("ID da tarefa não pode ser nulo");
		}
		

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, id);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	//Método para adicionar nova tarefa ao banco
		public void altera(Tarefa tarefa) {
			String sql= "update tarefas set descricao = ? where id = ?";
			

			try (PreparedStatement stmt = connection.prepareStatement(sql)){
				stmt.setLong(1, tarefa.getId());

				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	//Método para adicionar nova tarefa ao banco
	public void pesquisa(Long id) {
		String sql= "select * from tarefas where id = ?";
		
		if(id == null) {
			throw new IllegalStateException("ID da tarefa não pode ser nulo");
		}
		

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, id);
			
			try(ResultSet rs = stmt.executeQuery()){
				popularTarefa(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	


	private Tarefa popularTarefa(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setId(rs.getLong("id"));
		tarefa.setFinalizado(rs.getBoolean("finalizado"));
		
		if(tarefa.isFinalizado()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(rs.getDate("datafinalizacao").getTime());
		
			tarefa.setDatafinalizacao(cal);
		}
		return tarefa;
	}

	//Método para adicionar nova tarefa ao banco
	public List<Tarefa> getLista() {
		String sql= "select * from tarefas";
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			try(ResultSet rs = stmt.executeQuery()){
				while(rs.next()) {
					
					tarefas.add(popularTarefa(rs));
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tarefas;
	}
}
