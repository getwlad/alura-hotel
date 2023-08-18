package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Hospede;

public class HospedeDAO {
	private Connection connection;
	
	public HospedeDAO(Connection connection) {
			this.connection = connection;
	}
	
	public void salvar(Hospede hospede) {
		String sql = "INSERT INTO hospedes(nome, sobrenome, dataNascimento, "
				+ "telefone, nacionalidade, idReserva) values (?, ?, ?, ?, ?, ?)";
		try(PreparedStatement pstm = connection.prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS) ) {
			pstm.setString(1, hospede.getNome());
			pstm.setString(2, hospede.getSobrenome());
			pstm.setDate(3, new Date(hospede.getDataNascimento().getTime()));
			pstm.setDouble(4, hospede.getTelefone());
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
}
