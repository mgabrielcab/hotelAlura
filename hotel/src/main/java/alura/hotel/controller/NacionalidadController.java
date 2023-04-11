package alura.hotel.controller;

import java.util.List;
import java.util.Map;

import alura.hotel.dao.NacionalidadDAO;
import alura.hotel.factory.ConnectionFactory;
import alura.hotel.model.Nacionalidad;

public class NacionalidadController {

	private NacionalidadDAO nacionalidadDAO;

	public NacionalidadController() {
		this.nacionalidadDAO = new NacionalidadDAO(new ConnectionFactory().recuperaConexion());
	}

	public List<Nacionalidad> listar() {
		return nacionalidadDAO.listar();
	}

	public Nacionalidad nacionalidad(int id) {
		return nacionalidadDAO.buscarId(id);
	}

	public Map<Integer, Nacionalidad> nacionalidades(List<Integer> listaId) {
		return nacionalidadDAO.nacionalidades(listaId);
	}

}
