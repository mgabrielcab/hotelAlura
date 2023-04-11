package alura.hotel.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import alura.hotel.controller.FormaDePagoController;
import alura.hotel.controller.HuespedController;
import alura.hotel.controller.NacionalidadController;
import alura.hotel.controller.ReservaController;
import alura.hotel.model.FormaPago;
import alura.hotel.model.Huesped;
import alura.hotel.model.Nacionalidad;
import alura.hotel.model.Reserva;

public class Busqueda extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, btnExit, header, btnBuscar, btnEditar, btnEliminar, btnAtras;
	private JTabbedPane tabla;
	private JLabel labelExit, labelTitulo, imgLogo, imgBuscar, labelEliminar, labelEditar, labelAtras;
	private JTextField ingBusqueda;
	private int xMouse, yMouse;
	private JTable tbReservas, tbHuesped;
	private DefaultTableModel modeloReservas, modeloHuesped;
	private HuespedController huespedController;
	private NacionalidadController nacionalidadController;
	private ReservaController reservaController;
	private FormaDePagoController formaPagoController;

	/**
	 * Create the frame.
	 */
	public Busqueda() {
		estructuraBase();
		imagenes();
		pantalla();
		tabla();
	}

	private void estructuraBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 451);
		setLocationRelativeTo(null);
		pantalla = new JPanel();
		pantalla.setBackground(new Color(255, 255, 255));
		pantalla.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setContentPane(pantalla);
		pantalla.setLayout(null);

		// Desplazamiento de la pantalla
		header = new JPanel();
		header.setBorder(null);
		header.setOpaque(false);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);

			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setBackground(SystemColor.window);
		header.setBounds(0, 0, 734, 36);
		pantalla.add(header);
		header.setLayout(null);
		// Fin Desplazamiento

		// Inicio Boton atras
		btnAtras = new JPanel();
		btnAtras.setBackground(new Color(0, 0, 0));
		btnAtras.setBorder(null);
		btnAtras.setOpaque(false);
		btnAtras.setBounds(0, 5, 49, 31);
		header.add(btnAtras);
		btnAtras.setLayout(null);

		labelAtras = new JLabel("<");
		labelAtras.setForeground(new Color(0, 0, 0));
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setBounds(17, 0, 32, 27);
		labelAtras.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnAtras.add(labelAtras);

		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario menu = new MenuUsuario();
				menu.setVisible(true);
				dispose();
			}
		});
		btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Boton cerrar ventana
		btnExit = new JPanel();
		btnExit.setBounds(getWidth() - 30, 0, 30, 30);
		pantalla.add(btnExit);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(new Color(12, 138, 199));
				labelExit.setForeground(Color.white);
			}
		});
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setLayout(null);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBackground(new Color(0, 0, 0));
		labelExit.setBounds(-11, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setForeground(new Color(0, 0, 0));
		labelExit.setFont(new Font("DialogInput", Font.BOLD, 15));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		// Fin boton Cerrar

	}

	private void headerMousePressed(MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void pantalla() {
		labelTitulo = new JLabel("SISTEMA DE BUSQUEDA");
		labelTitulo.setForeground(new Color(12, 138, 199));
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBounds(0, 35, 761, 24);
		pantalla.add(labelTitulo);

		ingBusqueda = new JTextField();
		ingBusqueda.setFont(new Font("Arial", Font.BOLD, 14));
		ingBusqueda.setBorder(bordeInferior());
		ingBusqueda.setBounds(518, 99, 182, 36);
		pantalla.add(ingBusqueda);

		btnBuscar = new JPanel();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Accion al Hacer Click
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(SystemColor.textHighlight);
			}
		});
		btnBuscar.setBackground(SystemColor.textHighlight);
		btnBuscar.setBounds(710, 99, 48, 36);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnBuscar.setLayout(null);
		pantalla.add(btnBuscar);

		imgBuscar = new JLabel("");
		imgBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		String urlLupa = "/alura/hotel/imagenes/lupa2.png";
		imgBuscar.setIcon(cargarImagen(urlLupa, 32, 32));
		imgBuscar.setBounds(0, 0, 48, 36);
		btnBuscar.add(imgBuscar);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscar();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(SystemColor.textHighlight);
			}
		});

		btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(500, 411, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pantalla.add(btnEditar);

		labelEditar = new JLabel("EDITAR");
		labelEditar.setBounds(0, 0, 122, 35);
		labelEditar.setHorizontalAlignment(SwingConstants.CENTER);
		labelEditar.setForeground(Color.WHITE);
		labelEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEditar.add(labelEditar);

		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(SystemColor.textHighlight);
			}
		});

		btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(629, 411, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		pantalla.add(btnEliminar);

		labelEliminar = new JLabel("ELIMINAR");
		labelEliminar.setBounds(0, 0, 122, 35);
		labelEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		labelEliminar.setForeground(Color.WHITE);
		labelEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnEliminar.add(labelEliminar);

		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				eliminar();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnEliminar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEliminar.setBackground(SystemColor.textHighlight);
			}
		});

	}

	private void limpiarTablaReservas() {
		// modeloHuesped.getDataVector().clear();
		modeloReservas.getDataVector().clear();
	}

	private void limpiarTablaHuesped() {
		modeloHuesped.getDataVector().clear();
		// modeloReservas.getDataVector().clear();
	}

	private void buscar() {

		int indexDeTabbedPane = tabla.getSelectedIndex();
		if (indexDeTabbedPane == 0) {
			limpiarTablaReservas();
			reservaController = new ReservaController();
			formaPagoController = new FormaDePagoController();
			int idBuscada = Integer.parseInt(ingBusqueda.getText());
			Reserva reserva = reservaController.buscarId(idBuscada);
			if (reserva != null) {
				Integer formaPagoId = reserva.getFormaPagoId();
				FormaPago formaPago = formaPagoController.busqueda(formaPagoId);
				modeloReservas.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(),
						reserva.getFechaSalida(), reserva.getValor(), formaPago != null ? formaPago.getNombre() : "" }); // Agregar
																															// fila
																															// a
																															// la
																															// tabla
			}

		} else if (indexDeTabbedPane == 1) {
			limpiarTablaHuesped();
			huespedController = new HuespedController();
			nacionalidadController = new NacionalidadController();
			int idBuscada = Integer.parseInt(ingBusqueda.getText());
			Huesped huesped = huespedController.buscarId(idBuscada);
			if (huesped != null) {
				System.out.println("aca");
				Integer nacionalidadId = huesped.getIdNacionalidad();
				Nacionalidad nacionalidad = nacionalidadController.nacionalidad(nacionalidadId);
				modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getNombre(), huesped.getApellido(),
						huesped.getFechaNacimiento(), nacionalidad != null ? nacionalidad.getNombre() : "",
						huesped.getTelefono(), huesped.getIdReserva() }); // Agregar fila a la tabla
			}
		}
	}

	private void eliminar() {
		int indexDeTabbedPane = tabla.getSelectedIndex();
		if (indexDeTabbedPane == 0) {
			reservaController = new ReservaController();
			if (tieneFilaElegida(indexDeTabbedPane)) {
				JOptionPane.showMessageDialog(this, "Por favor selecciona un item");
				return;
			}
			Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer
								.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString());
						int cantidadEliminada;
						cantidadEliminada = this.reservaController.eliminar(id);
						modeloReservas.removeRow(tbReservas.getSelectedRow());

						if (cantidadEliminada > 0) {
							JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
						}
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
			limpiarTablaReservas();
			cargarTableReserva();

		} else if (indexDeTabbedPane == 1) {
			huespedController = new HuespedController();
			if (tieneFilaElegida(indexDeTabbedPane)) {
				JOptionPane.showMessageDialog(this, "Por favor selecciona un item");
				return;
			}
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), tbHuesped.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = Integer
								.valueOf(modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 0).toString());
						int cantidadEliminada;
						cantidadEliminada = this.huespedController.eliminar(id);
						modeloHuesped.removeRow(tbHuesped.getSelectedRow());

						if (cantidadEliminada > 0) {
							JOptionPane.showMessageDialog(this, cantidadEliminada + " Item eliminado con éxito!");
						}
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
			limpiarTablaHuesped();
			cargarTablaHuesped();
		}
	}

	private boolean tieneFilaElegida(int index) {
		boolean respuesta = false;
		if (index == 0) {
			respuesta = tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
		} else if (index == 1) {
			respuesta = tbHuesped.getSelectedRowCount() == 0 || tbHuesped.getSelectedColumnCount() == 0;
		}
		return respuesta;
	}

	private void modificar() {
		int indexDeTabbedPane = tabla.getSelectedIndex();
		if (indexDeTabbedPane == 0) {
			reservaController = new ReservaController();
			if (tieneFilaElegida(indexDeTabbedPane)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			Optional.ofNullable(modeloReservas.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = (Integer
								.valueOf(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 0).toString()));

						String fechaCheckIn = modeloReservas.getValueAt(tbReservas.getSelectedRow(), 1).toString();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate checkIn = LocalDate.parse(fechaCheckIn, formatter);
						String fechaCkecOut = modeloReservas.getValueAt(tbReservas.getSelectedRow(), 2).toString();
						LocalDate checkOut = LocalDate.parse(fechaCkecOut, formatter);
						Float valor = Float
								.parseFloat(modeloReservas.getValueAt(tbReservas.getSelectedRow(), 3).toString());

						this.reservaController.modificar(id, checkIn, checkOut, valor);
						JOptionPane.showMessageDialog(this, "Item Modificado con Exito");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
			limpiarTablaReservas();
			cargarTableReserva();

		} else if (indexDeTabbedPane == 1) {
			huespedController = new HuespedController();
			if (tieneFilaElegida(indexDeTabbedPane)) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			}
			Optional.ofNullable(modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), tbHuesped.getSelectedColumn()))
					.ifPresentOrElse(fila -> {
						Integer id = (Integer
								.valueOf(modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 0).toString()));
						String nombre = modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 1).toString();
						String apellido = modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 2).toString();
						String fechaNacimiento = modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 3).toString();
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						LocalDate nacimiento = LocalDate.parse(fechaNacimiento, formatter);
						String telefono = modeloHuesped.getValueAt(tbHuesped.getSelectedRow(), 5).toString();

						this.huespedController.modificar(id, nombre, apellido, nacimiento, telefono);
						JOptionPane.showMessageDialog(this, "Item Modificado con Exito");
					}, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
			limpiarTablaHuesped();
			cargarTablaHuesped();

		}
	}

	private void tabla() {
		// huespedController = new HuespedController();
		// reservaController = new ReservaController();
		tabla = new JTabbedPane(JTabbedPane.TOP);
		tabla.setBounds(10, 141, 741, 262);
		pantalla.add(tabla);

		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloReservas = (DefaultTableModel) tbReservas.getModel();
		modeloReservas.addColumn("Numero de Reserva");
		modeloReservas.addColumn("Fecha Check In");
		modeloReservas.addColumn("Fecha Check Out");
		modeloReservas.addColumn("Valor");
		modeloReservas.addColumn("Forma de Pago");
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		tabla.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/alura/hotel/imagenes/reservado.png")),
				scroll_table, null);
		scroll_table.setVisible(true);
		cargarTableReserva();

		tbHuesped = new JTable();
		tbHuesped.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuesped.setFont(new Font("Roboto", Font.PLAIN, 16));
		modeloHuesped = (DefaultTableModel) tbHuesped.getModel();
		modeloHuesped.addColumn("N°");
		modeloHuesped.addColumn("Nombre");
		modeloHuesped.addColumn("Apellido");
		modeloHuesped.addColumn("Nacimiento");
		modeloHuesped.addColumn("Nacionalidad");
		modeloHuesped.addColumn("Telefono");
		modeloHuesped.addColumn("N° Reserva");
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuesped);
		tabla.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/alura/hotel/imagenes/pessoas.png")),
				scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		cargarTablaHuesped();

	}

	private void cargarTableReserva() {
		reservaController = new ReservaController();
		formaPagoController = new FormaDePagoController();
		List<Reserva> reservas = this.reservaController.listar();
		List<Integer> idsMetodoPago = reservas.stream().map(Reserva::getFormaPagoId).collect(Collectors.toList());
		Map<Integer, FormaPago> formasDePago = formaPagoController.formaDePago(idsMetodoPago);
		for (Reserva reserva : reservas) {
			modeloReservas.addRow(new Object[] { reserva.getId(), reserva.getFechaEntrada(), reserva.getFechaSalida(),
					reserva.getValor(), formasDePago.get(reserva.getFormaPagoId()).getNombre() });
		}
	}

	private void cargarTablaHuesped() {
		huespedController = new HuespedController();
		nacionalidadController = new NacionalidadController();
		List<Huesped> huespedes = this.huespedController.listar();
		List<Integer> idsNacionalidad = huespedes.stream().map(Huesped::getIdNacionalidad).collect(Collectors.toList());
		Map<Integer, Nacionalidad> nacionalidades = nacionalidadController.nacionalidades(idsNacionalidad);
		for (Huesped huespe : huespedes) {
			modeloHuesped.addRow(new Object[] { huespe.getId(), huespe.getNombre(), huespe.getApellido(),
					huespe.getFechaNacimiento(), nacionalidades.get(huespe.getIdNacionalidad()).getNombre(),
					huespe.getTelefono(), huespe.getIdReserva() });
		}
	}

	private MatteBorder bordeInferior() {
		return new MatteBorder(0, 0, 2, 0, (Color) new Color(12, 138, 199));
	}

	private void imagenes() {
		imgLogo = new JLabel("");
		String urlLogo = "/alura/hotel/imagenes/Ha-100px.png";
		imgLogo.setIcon(cargarImagen(urlLogo, 100, 100));
		imgLogo.setBounds(0, 35, 100, 100);
		pantalla.add(imgLogo);
	}

	private static ImageIcon cargarImagen(String url, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(ReservasView.class.getResource(url));
		Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}

	private void headerMouseDragged(MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
