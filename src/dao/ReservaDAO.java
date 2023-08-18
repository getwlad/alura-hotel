package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import model.Reserva;

public class ReservaDAO {
	private Connection connection;
	
	public ReservaDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Reserva reserva) {
		String sql = "INSERT INTO reservas(DataEntrada, DataSaida, FormaPagamento)"
				+ " values (?, ?, ?)";
		try (PreparedStatement pstm = 
				connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setDate(1, new Date(reserva.getDataEntrada().getTime()));
			pstm.setDate(2,  new Date(reserva.getDataSaida().getTime()));
			pstm.setString(3, reserva.getFormaPagamento());
			
			pstm.execute();
			
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
			
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}
