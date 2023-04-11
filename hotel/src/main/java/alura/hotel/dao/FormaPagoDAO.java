package alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import alura.hotel.model.FormaPago;

public class FormaPagoDAO {
	final private Connection con;

	public FormaPagoDAO(Connection con) {
		this.con = con;
	}

	public List<FormaPago> listar() {
		List<FormaPago> resultado = new ArrayList<>();
		try {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM forma_pago");
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						var formaPago = new FormaPago(resultSet.getInt("id"), resultSet.getString("nombre"));
						resultado.add(formaPago);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public FormaPago buscar(int id) {
		FormaPago formaPago = new FormaPago();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM forma_pago WHERE id=?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				System.out.println("busca " + statement);
				try (resultSet) {
					while (resultSet.next()) {
						formaPago.setId(resultSet.getInt("id"));
						formaPago.setNombre(resultSet.getString("nombre"));
					}
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return formaPago;
	}

	public Map<Integer, FormaPago> formasPago(List<Integer> listaId) {
		Map<Integer, FormaPago> resultado = new HashMap<>();
		if (listaId.isEmpty()) {
			return resultado;
		}
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT id,nombre FROM forma_pago WHERE id = ?");
			for (Integer id : listaId) {
				statement.setInt(1, id);
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				if (resultSet.next()) {
					FormaPago formaDePago = new FormaPago(resultSet.getInt("id"), resultSet.getString("nombre"));
					resultado.put(formaDePago.getId(), formaDePago);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

}
