package alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import alura.hotel.dao.ReservaDAO;
import alura.hotel.factory.ConnectionFactory;
import alura.hotel.model.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public void guardar(Reserva reserva, int metPagoId) {
		reserva.setFormaPagoId(metPagoId);
		reservaDAO.guardar(reserva);
	}

	public void modificar(LocalDate fechaEntrada, LocalDate fechaSalida, float valor, int formaPagoId, int reservaId) {
		reservaDAO.modificar(fechaEntrada, fechaSalida, valor, formaPagoId, reservaId);
	}

	public List<Reserva> listar() {
		return reservaDAO.listar();
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

	public void modificar(Integer id, LocalDate checkIn, LocalDate checkOut, Float valor) {
		reservaDAO.modificar(id, checkIn, checkOut, valor);
	}

	public Reserva buscarId(int idBuscada) {
		return reservaDAO.buscarId(idBuscada);
	}

	public void controlReservasinHuesped() {
		reservaDAO.eliminarReservaSinHuesped();
	}

}
