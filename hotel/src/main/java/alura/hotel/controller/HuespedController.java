package alura.hotel.controller;

import java.time.LocalDate;
import java.util.List;

import alura.hotel.dao.HuespedDAO;
import alura.hotel.factory.ConnectionFactory;
import alura.hotel.model.Huesped;

public class HuespedController {
	private HuespedDAO huespedDAO;

	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public void guardar(Huesped huesped, Integer nacionalidadId) {
		huesped.setIdNacionalidad(nacionalidadId);
		huespedDAO.guardar(huesped);
	}

	public List<Huesped> listar() {
		return huespedDAO.listar();
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}

	public void modificar(Integer id, String nombre, String apellido, LocalDate nacimiento, String telefono) {
		huespedDAO.modificar(id, nombre, apellido, nacimiento, telefono);

	}

	public Huesped buscarId(int idBuscada) {
		return huespedDAO.buscarId(idBuscada);
	}

}
