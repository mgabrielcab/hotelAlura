package alura.hotel.model;

import java.time.LocalDate;

public class Reserva {
	private int id;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private Float valor;
	private int formaPagoId;

	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Float valor, int formaPagoId) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPagoId = formaPagoId;
	}

	public Reserva(int id, LocalDate fechaEntrada, LocalDate fechaSalida, Float valor, int formaPagoId) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
		this.formaPagoId = formaPagoId;
	}

	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, Float valor) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valor = valor;
	}

	public Reserva() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public int getFormaPagoId() {
		return formaPagoId;
	}

	public void setFormaPagoId(int formaPagoId) {
		this.formaPagoId = formaPagoId;
	}

}
