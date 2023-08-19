package controller;

import java.sql.Connection;
import java.util.ArrayList;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospede;

public class HospedeController {
	private HospedeDAO hospedeDAO;
	
	public HospedeController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.hospedeDAO = new HospedeDAO(connection);
	}
	public void save(Hospede hospede) {
		this.hospedeDAO.salvar(hospede);
	}
	public ArrayList<Hospede> buscarHospedes(String sobrenome){
		return this.hospedeDAO.buscarHospedes(sobrenome);
	}
}
