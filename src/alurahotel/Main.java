package alurahotel;


import controller.ReservaController;
import model.Reserva;
import views.MenuPrincipal;

public class Main {

	public static void main(String[] args) {
		MenuPrincipal menu = new MenuPrincipal();
		menu.setVisible(true);
		ReservaController reservaController = new ReservaController();
		Reserva reserva = reservaController.buscarReserva(1);
		System.out.println(reserva.getDataEntrada());
	}

}
