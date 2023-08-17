package alurahotel;

import java.sql.Connection;

import factory.ConnectionFactory;

public class Main {

	public static void main(String[] args) {
		ConnectionFactory connection = new ConnectionFactory();
		Connection conn = connection.recuperarConexao();
		System.out.println("Conectado com sucesso");

	}

}
