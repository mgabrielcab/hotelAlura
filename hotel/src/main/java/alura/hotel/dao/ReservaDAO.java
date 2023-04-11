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

import javax.swing.JOptionPane;

import alura.hotel.model.Reserva;

public class ReservaDAO {
	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservas (fecha_entrada,fecha_salida,valor,forma_pago_id) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			System.out.println(statement);
			try (statement) {
				registrar(reserva, statement);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void registrar(Reserva reserva, PreparedStatement statement) {
		try {
			statement.setString(1, reserva.getFechaEntrada().format(DateTimeFormatter.ISO_LOCAL_DATE));
			statement.setString(2, reserva.getFechaSalida().format(DateTimeFormatter.ISO_LOCAL_DATE));
			statement.setFloat(3, reserva.getValor());
			statement.setInt(4, reserva.getFormaPagoId());
			statement.execute();
			System.out.println(statement);
			try (ResultSet resultSet = statement.getGeneratedKeys();) {
				while (resultSet.next()) {
					reserva.setId(resultSet.getInt(1));
					System.out.println("Reserva Creada");
				}
				resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			throw new RuntimeException(e);
		}

	}

	public void modificar(LocalDate fechaEntrada, LocalDate fechaSalida, float valor, int formaPagoId, int reservaId) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE reservas SET fecha_entrada= ?, fecha_salida = ?, valor= ?, forma_pago_id= ? WHERE id = ?");
			try (statement) {
				statement.setString(1, fechaEntrada.format(DateTimeFormatter.ISO_LOCAL_DATE));
				statement.setString(2, fechaSalida.format(DateTimeFormatter.ISO_LOCAL_DATE));
				statement.setFloat(3, valor);
				statement.setInt(4, formaPagoId);
				statement.setInt(5, reservaId);
				statement.execute();
				System.out.println(statement.getUpdateCount());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Reserva> listar() {
		List<Reserva> listaReservas = new ArrayList<>();
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("SELECT id,fecha_entrada,fecha_salida,valor,forma_pago_id FROM reservas;");
			try (statement) {
				statement.execute();
				ResultSet resultSet = statement.getResultSet();
				while (resultSet.next()) {
					Reserva reserva = new Reserva(resultSet.getInt("id"),
							resultSet.getDate("fecha_entrada").toLocalDate(),
							resultSet.getDate("fecha_salida").toLocalDate(), resultSet.getFloat("valor"),
							resultSet.getInt("forma_pago_id"));
					listaReservas.add(reserva);
				}
				return listaReservas;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		if (verificacionForanea(con, id)) {
			JOptionPane.showMessageDialog(null, "No se puede eliminar el registro ya que se encuentra vinculado.");
			return 0;
		}
		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id=?");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();
				return statement.getUpdateCount();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean verificacionForanea(Connection con, int id) {
		String foreignTable = "huespedes";
		String query = "SELECT COUNT(*) FROM " + foreignTable + " WHERE id_reserva = ?";
		try (PreparedStatement foreignStatement = con.prepareStatement(query)) {
			foreignStatement.setInt(1, id);
			try (ResultSet resultSet = foreignStatement.executeQuery()) {
				resultSet.next();
				int count = resultSet.getInt(1);

				if (count > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	public void modificar(Integer id, LocalDate checkIn, LocalDate checkOut, Float valor) {
		try (con) {
			final PreparedStatement statement = con
					.prepareStatement("UPDATE reservas SET fecha_entrada= ?, fecha_salida = ?, valor= ? WHERE id = ?");
			try (statement) {
				statement.setString(1, checkIn.format(DateTimeFormatter.ISO_LOCAL_DATE));
				statement.setString(2, checkOut.format(DateTimeFormatter.ISO_LOCAL_DATE));
				statement.setFloat(3, valor);
				statement.setInt(4, id);
				System.out.println("UPDATE reservas SET fecha_entrada=" + checkIn + ", fecha_salida =" + checkOut
						+ ", valor=" + valor + "  WHERE id =" + id + ";");
				statement.executeUpdate();
				System.out.println(statement.getUpdateCount());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Reserva buscarId(int idBuscada) {
		Reserva reserva = new Reserva();
		try (con) {
			final PreparedStatement statement = con.prepareStatement("SELECT * FROM reservas WHERE id=?");
			try (statement) {
				statement.setInt(1, idBuscada);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				System.out.println("Busca: " + statement);
				try (resultSet) {
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt("id"));
						reserva.setFechaEntrada(resultSet.getDate("fecha_entrada").toLocalDate());
						reserva.setFechaSalida(resultSet.getDate("fecha_salida").toLocalDate());
						reserva.setFormaPagoId(resultSet.getInt("forma_pago_id"));
						reserva.setValor(resultSet.getFloat("valor"));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reserva;
	}

	public void eliminarReservaSinHuesped() {
		try (con) { // Reemplaza obtenerConexion() con tu método de obtención de conexión a la base
					// de datos
			try (PreparedStatement statement = con
					.prepareStatement("DELETE FROM reservas WHERE id NOT IN (SELECT id_reserva FROM huespedes)")) {
				statement.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
