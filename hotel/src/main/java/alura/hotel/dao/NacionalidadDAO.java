package alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alura.hotel.model.Nacionalidad;

public class NacionalidadDAO {
	final private Connection con;

	public NacionalidadDAO(Connection con) {
		this.con = con;
	}

	public List<Nacionalidad> listar() {
		List<Nacionalidad> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM nacionalidades");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var nacionalidad = new Nacionalidad(resultSet.getInt("id"), resultSet.getString("nombre"));
						resultado.add(nacionalidad);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public Nacionalidad buscarId(int id) {
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id,nombre FROM nacionalidades WHERE id = ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					return new Nacionalidad(resultSet.getInt("id"), resultSet.getString("nombre"));
				} else {
					return null;
				}

			}
		} catch (SQLException e) {
			System.out.println("Aca error");
			throw new RuntimeException(e);
		}
	}

	public Map<Integer, Nacionalidad> nacionalidades(List<Integer> listaId) {
		Map<Integer, Nacionalidad> resultado = new HashMap<>();
		if (listaId.isEmpty()) {
			return resultado;
		}
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id,nombre FROM nacionalidades WHERE id = ?");
			for (Integer id : listaId) {
				statement.setInt(1, id);
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					Nacionalidad nacionalidad = new Nacionalidad(resultSet.getInt("id"), resultSet.getString("nombre"));
					resultado.put(nacionalidad.getId(), nacionalidad);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}
}
