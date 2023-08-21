package dao;

import java.math.BigDecimal;
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
		String sql = "INSERT INTO reservas(data_entrada, data_saida, forma_pagamento, valor)"
				+ " values (?, ?, ?, ?)";
		try (PreparedStatement pstm = 
				connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setDate(1, new Date(reserva.getDataEntrada().getTime()));
			pstm.setDate(2,  new Date(reserva.getDataSaida().getTime()));
			pstm.setString(3, reserva.getFormaPagamento());
			pstm.setBigDecimal(4, reserva.getValor());
			
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
	
	public Reserva buscar(int numeroReserva) {
		Reserva reserva = new Reserva(null, null, null, new BigDecimal(2));
		String sql = "SELECT * from reservas where id = ?";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			pstm.setInt(1, numeroReserva);
			try(ResultSet rst = pstm.executeQuery()){
				while(rst.next()) {
					int id =rst.getInt("id");
					Date dataE = rst.getDate("data_entrada");
					Date dataS = rst.getDate("data_saida");
					String formaPagamento = rst.getString("forma_pagamento");
					BigDecimal valor = rst.getBigDecimal("valor");
					reserva.setId(id);
					reserva.setDataEntrada(dataE);
					reserva.setDataSaida(dataS);
					reserva.setFormaPagamento(formaPagamento);
					reserva.setValor(valor);
				}
			}
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		return reserva;
	}
	
	public void update(Reserva reserva) {
		String sql = "UPDATE reservas SET data_entrada = ?, data_saida = ?,"
		           + " forma_pagamento = ?, valor = ? WHERE id = ?; ";
		try(PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setDate(1, new Date(reserva.getDataEntrada().getTime()));
			pstm.setDate(2,  new Date(reserva.getDataSaida().getTime()));
			pstm.setString(3, reserva.getFormaPagamento());
			pstm.setBigDecimal(4, reserva.getValor());
			pstm.setInt(5, reserva.getId());
			pstm.execute();
			try (ResultSet rst = pstm.getGeneratedKeys()) {
				while (rst.next()) {
					reserva.setId(rst.getInt(1));
				}
			}
		}
		catch(SQLException e) { throw new RuntimeException(e);}
	}
}
