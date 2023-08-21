package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Hospede;

public class HospedeDAO {
	private Connection connection;
	
	public HospedeDAO(Connection connection) {
			this.connection = connection;
	}
	
	public void salvar(Hospede hospede) {
		String sql = "INSERT INTO hospedes(nome, sobrenome, data_nascimento, "
				+ "telefone, nacionalidade, idReserva) values (?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstm = connection.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS) ) {
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, new Date(hospede.getDataNascimento().getTime()));
			pstm.setLong(4, hospede.getTelefone());
			pstm.setString(5, hospede.getNacionalidade());
			pstm.setInt(6, hospede.getIdReserva());
			
			pstm.execute();
			
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					hospede.setId(rst.getInt(1));
				}
			}
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Hospede> buscarHospedes(String sobrenome) {
		ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
		String sql = "SELECT * from hospedes where sobrenome = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(1, sobrenome);
			pstm.execute();
			
			try(ResultSet rst = pstm.executeQuery()) {
				while(rst.next()) {
					int id = rst.getInt("id");
					String nome = rst.getString("nome");
					String sobrenom = rst.getString("sobrenome");
				    Date dataNascimento = rst.getDate("data_nascimento");
				    long telefone = rst.getLong("telefone");
				    String nacionalidade = rst.getString("nacionalidade");
				    int idReserva = rst.getInt("idReserva");
					Hospede hospede = new Hospede(nome, sobrenom, dataNascimento,
							telefone, nacionalidade, idReserva);
					hospede.setId(id);
					hospedes.add(hospede);
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return hospedes;
	}
	
	public void updateHospede(Hospede hospede) {
		String sql = "UPDATE hospedes SET nome = ?, sobrenome = ?, data_nascimento = ?,"
				+ " telefone = ?, nacionalidade = ?, idReserva = ? where id = ?;";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, new Date(hospede.getDataNascimento().getTime()));
			pstm.setLong(4, hospede.getTelefone());
			pstm.setString(5, hospede.getNacionalidade());
			pstm.setInt(6, hospede.getIdReserva());
			pstm.setInt(7, hospede.getId());
			pstm.execute();
			
			try(ResultSet rs = pstm.getGeneratedKeys()){
				while(rs.next()) {
					
				}
			}
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
