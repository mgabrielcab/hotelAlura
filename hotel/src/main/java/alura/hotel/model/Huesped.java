package alura.hotel.model;

import java.time.LocalDate;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private int nacionalidadId;
	private String telefono;
	private int reservaId;

	public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String telefono, int reservaId) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public Huesped(int id, String nombre, String apellido, LocalDate fechaNacimiento, int nacionalidadId,
			String telefono, int reservaId) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidadId = nacionalidadId;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public Huesped() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdNacionalidad() {
		return nacionalidadId;
	}

	public void setIdNacionalidad(int idNacionalidad) {
		this.nacionalidadId = idNacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdReserva() {
		return reservaId;
	}

	public void setIdReserva(int reservaId) {
		this.reservaId = reservaId;
	}

}
