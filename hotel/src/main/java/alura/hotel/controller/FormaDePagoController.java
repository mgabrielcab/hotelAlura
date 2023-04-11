package alura.hotel.controller;

import java.util.List;
import java.util.Map;

import alura.hotel.dao.FormaPagoDAO;
import alura.hotel.factory.ConnectionFactory;
import alura.hotel.model.FormaPago;

public class FormaDePagoController {
	private FormaPagoDAO formaPagoDAO;

	public FormaDePagoController() {
		this.formaPagoDAO = new FormaPagoDAO(new ConnectionFactory().recuperaConexion());
		System.out.println("Conexion Establecida");
	}

	public List<FormaPago> listar() {
		return formaPagoDAO.listar();
	}

	public FormaPago busqueda(int id) {
		return formaPagoDAO.buscar(id);

	}

	public Map<Integer, FormaPago> formaDePago(List<Integer> listaId) {
		return formaPagoDAO.formasPago(listaId);
	}

}
