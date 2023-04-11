package alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import alura.hotel.model.Huesped;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes (nombre,apellido,fecha_nacimiento,id_nacionalidad,telefono,id_reserva) VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			System.out.println(statement);
			try (statement) {
				registrar(huesped, statement);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void registrar(Huesped huesped, PreparedStatement statement) throws SQLException {
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, huesped.getFechaNacimiento().format(DateTimeFormatter.ISO_LOCAL_DATE));
		statement.setInt(4, huesped.getIdNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());
		statement.execute();
		System.out.println("Se ejecuta");
		try (ResultSet resultSet = statement.getGeneratedKeys();) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println("Fue creado el huesped");
			}
			resultSet.close();
		}

	}

	public List<Huesped> listar() {
		List<Huesped> listaHuesped = new ArrayList<>();
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id,nombre,apellido,fecha_nacimiento,id_nacionalidad,telefono,id_reserva FROM huespedes");
			try (statement) {
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Huesped huesped = new Huesped(resultSet.getInt("id"), resultSet.getString("nombre"),
							resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento").toLocalDate(),
							resultSet.getInt("id_nacionalidad"), resultSet.getString("telefono"),
							resultSet.getInt("id_reserva"));
					listaHuesped.add(huesped);
				}
				return listaHuesped;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE from huespedes WHERE id= ?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void modificar(Integer id, String nombre, String apellido, LocalDate nacimiento, String telefono) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huespedes SET nombre= ?, apellido = ?, fecha_nacimiento= ?,telefono=? WHERE id = ?");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setString(3, nacimiento.format(DateTimeFormatter.ISO_LOCAL_DATE));
				statement.setString(4, telefono);
				statement.setInt(5, id);
				statement.executeUpdate();
				System.out.println(statement.getUpdateCount());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Huesped buscarId(int idBuscada) {
		Huesped huesped = new Huesped();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes WHERE id = ?");
			statement.setInt(1, idBuscada);
			statement.execute();
			System.out.println("Busca: " + statement);
			final ResultSet resultSet = statement.getResultSet();
			try (resultSet) {
				while (resultSet.next()) {
					huesped.setId(resultSet.getInt("id"));
					huesped.setNombre(resultSet.getString("nombre"));
					huesped.setApellido(resultSet.getString("apellido"));
					huesped.setFechaNacimiento(resultSet.getDate("fecha_nacimiento").toLocalDate());
					huesped.setIdNacionalidad(resultSet.getInt("id_nacionalidad"));
					huesped.setTelefono(resultSet.getString("telefono"));
					huesped.setIdReserva(resultSet.getInt("id_reserva"));
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huesped;
	}

}
