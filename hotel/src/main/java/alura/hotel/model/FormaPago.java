package alura.hotel.model;

public class FormaPago {
	private int id;
	private String nombre;

	public FormaPago() {
	}

	public FormaPago(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		// TODO Esbozo de método generado automáticamente
		return nombre;
	}
}
