package br.tsi.daw.tarefas.dao;

import java.sql.Connection;
import java.sql.Date;
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
			e.printStackTrace();
		}
	}

	//Lista todas as tarefas no banco
	public List<Tarefa> getLista() {
		String sql= "select * from tarefas";
		List<Tarefa> tarefas = null;

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			ResultSet rs = stmt.executeQuery();
			tarefas = new ArrayList<Tarefa>();
			while(rs.next()) {
				tarefas.add(populaTarefas(rs));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tarefas;
	}

	public void finaliza(Long id) {
		
		if(id==null) {
			throw new IllegalStateException("Id da tarefa não pode ser nula.");
		}
		String sql="update tarefas set finalizado=?, datafinalizacao=? where id=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Tarefa buscaPorId(Long id) {
		if(id==null) {
			throw new IllegalStateException("Id da tarefa não pode ser nula.");
		}
		String sql= "select * from tarefas where id=?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return populaTarefas(rs);
			}
			
			rs.close();
			stmt.close();
			
			return null;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	private Tarefa populaTarefas(ResultSet rs) throws SQLException {
		Tarefa tarefa = new Tarefa();
		
		tarefa.setId(rs.getLong("id"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setFinalizado(rs.getBoolean("finalizado"));
		
		
		if(tarefa.isFinalizado()) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(rs.getDate("datafinalizacao").getTime());

			tarefa.setDatafinalizacao(calendar);
		}
		return tarefa;
	}
	
	
	public void altera(Tarefa tarefa) {
		String sql="update tarefas set descricao=? where id=?";
		
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setString(1, tarefa.getDescricao());
			stmt.setLong(2, tarefa.getId());
			
			stmt.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeTarefa(Long id) {
		String sql= "delete from tarefas where id=?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)){
			stmt.setLong(1, id);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
