package controller;

import java.sql.Connection;
import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.reservaDAO = new ReservaDAO(connection);
	}
	
	public void save(Reserva reserva) {
		this.reservaDAO.save(reserva);
	}
	public Reserva buscarReserva(int idReserva) {
		return this.reservaDAO.buscar(idReserva);
	}
	
	public void updateReserva(Reserva reserva) {
		this.reservaDAO.update(reserva);
	}
}
