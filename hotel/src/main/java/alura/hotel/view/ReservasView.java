package alura.hotel.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.NumberFormatter;
import com.toedter.calendar.JDateChooser;
import alura.hotel.controller.FormaDePagoController;
import alura.hotel.controller.ReservaController;
import alura.hotel.model.FormaPago;
import alura.hotel.model.Reserva;
import javax.swing.JSeparator;

public class ReservasView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, btnExit, header, divisor, btnSiguiente, btnAtras;
	private JLabel labelExit, imgLogo, imgFondo, labelTitulo, labelCheckIn, labelCheckOut, labelValor, labelFormaPago,
			labelSiguiente, labelAtras;
	private int xMouse, yMouse;
	private JComboBox<FormaPago> ingFormaPago;
	private JDateChooser ingCheckIn, ingCheckOut;
	private JFormattedTextField ingValor;
	private MenuUsuario menuUsuario;
	private ReservaController reservaController;
	private RegistroHuesped registroHuesped;
	private FormaDePagoController formaPagoController;
	private Reserva reservaAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReservasView() {
		estructuraBase();
		imagenes();
		formulario();
	}

	public ReservasView(Reserva reserva) {
		this.reservaAtras = reserva;
		System.out.println(this.reservaAtras.getId());
		estructuraBase();
		imagenes();
		formularioAtras(); // Seria un tipo de modificacion
		// Seguir en Modificar la reserva una vez que se vuelve atras

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

		// Inicio Boton atras
		btnAtras = new JPanel();
		btnAtras.setBackground(new Color(0, 0, 0));
		btnAtras.setBorder(null);
		btnAtras.setOpaque(false);
		btnAtras.setBounds(0, 5, 49, 31);
		header.add(btnAtras);
		btnAtras.setLayout(null);

		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setBounds(17, 0, 32, 27);
		labelAtras.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnAtras.add(labelAtras);

		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				menuUsuario = new MenuUsuario();
				menuUsuario.setVisible(true);
				dispose();
			}
		});
		btnAtras.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// Fin Desplazamiento

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
		btnExit.setBackground(new Color(12, 138, 199));
		btnExit.setLayout(null);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBackground(Color.WHITE);
		labelExit.setBounds(-11, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setForeground(Color.WHITE);
		labelExit.setFont(new Font("DialogInput", Font.BOLD, 15));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		divisor = new JPanel();
		divisor.setBackground(new Color(12, 138, 199));
		divisor.setBounds(468, 0, 293, 451);
		pantalla.add(divisor);
		divisor.setLayout(null);
		// Fin boton Cerrar

	}

	private void formulario() {
		formaPagoController = new FormaDePagoController(); // Luego Mover esta opcion a otro lado
		labelTitulo = new JLabel("SISTEMA DE RESERVAS");
		labelTitulo.setForeground(new Color(12, 138, 199));
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBounds(0, 33, 442, 36);
		pantalla.add(labelTitulo);

		labelCheckIn = new JLabel("FECHA DE CHECK IN");
		labelCheckIn.setFont(new Font("Arial", Font.BOLD, 16));
		labelCheckIn.setForeground(Color.DARK_GRAY);
		labelCheckIn.setHorizontalAlignment(SwingConstants.LEFT);
		labelCheckIn.setBounds(29, 78, 184, 36);
		pantalla.add(labelCheckIn);

		ingCheckIn = new JDateChooser();
		ingCheckIn.getCalendarButton().setBackground(SystemColor.textHighlight);
		ingCheckIn.setBounds(29, 107, 314, 36);
		ingCheckIn.setFont(new Font("Arial", Font.BOLD, 14));
		String urlCalendario = "/alura/hotel/imagenes/reservado.png";
		ingCheckIn.getCalendarButton().setIcon(cargarImagen(urlCalendario, 24, 24));
		pantalla.add(ingCheckIn);
		ingCheckIn.setDateFormatString("yyyy-MM-dd");

		labelCheckOut = new JLabel("FECHA DE CHECK OUT");
		labelCheckOut.setFont(new Font("Arial", Font.BOLD, 16));
		labelCheckOut.setForeground(Color.DARK_GRAY);
		labelCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelCheckOut.setBounds(29, 159, 184, 36);
		pantalla.add(labelCheckOut);

		ingCheckOut = new JDateChooser();
		ingCheckOut.getCalendarButton().setBackground(SystemColor.textHighlight);
		ingCheckOut.setBounds(29, 191, 314, 36);
		ingCheckOut.setFont(new Font("Arial", Font.BOLD, 14));
		ingCheckOut.getCalendarButton().setIcon(cargarImagen(urlCalendario, 24, 24));
		pantalla.add(ingCheckOut);
		ingCheckOut.setDateFormatString("yyyy-MM-dd");

		labelValor = new JLabel("VALOR DE LA RESERVA");
		labelValor.setFont(new Font("Arial", Font.BOLD, 16));
		labelValor.setForeground(Color.DARK_GRAY);
		labelValor.setBounds(29, 244, 239, 36);
		pantalla.add(labelValor);

		// Para cambiar el tipo de JtextField
		NumberFormat floatFormat = new DecimalFormat("#0.00");
		NumberFormatter formatter = new NumberFormatter(floatFormat);
		formatter.setValueClass(Float.class);
		formatter.setMinimum(Float.MIN_VALUE);
		formatter.setMaximum(Float.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		ingValor = new JFormattedTextField(formatter);
		ingValor.setBackground(SystemColor.text);
		ingValor.setHorizontalAlignment(SwingConstants.LEFT);
		ingValor.setBounds(29, 273, 314, 36);
		ingValor.setFont(new Font("Arial", Font.BOLD, 14));
		ingValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ingValor.setColumns(10);
		pantalla.add(ingValor);

		labelFormaPago = new JLabel("FORMA DE PAGO");
		labelFormaPago.setFont(new Font("Arial", Font.BOLD, 16));
		labelFormaPago.setForeground(Color.DARK_GRAY);
		labelFormaPago.setBounds(29, 319, 239, 36);
		pantalla.add(labelFormaPago);

		ingFormaPago = new JComboBox<>();
		ingFormaPago.setFont(new Font("Arial", Font.BOLD, 14));
		ingFormaPago.setBounds(29, 351, 314, 36);
		ingFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		ingFormaPago.addItem(new FormaPago(0, "Selecciona un metodo de pago"));
		var metodosPago = this.formaPagoController.listar();
		metodosPago.forEach(metodo -> ingFormaPago.addItem(metodo));
		pantalla.add(ingFormaPago);

		JSeparator separador1 = new JSeparator();
		separador1.setBackground(new Color(0, 0, 160));
		separador1.setForeground(new Color(255, 255, 255));
		separador1.setBounds(29, 143, 239, 6);
		pantalla.add(separador1);

		JSeparator separador2 = new JSeparator();
		separador2.setForeground(Color.WHITE);
		separador2.setBackground(new Color(12, 138, 199));
		separador2.setBounds(29, 228, 239, 6);
		pantalla.add(separador2);

		JSeparator separador3 = new JSeparator();
		separador3.setForeground(Color.WHITE);
		separador3.setBackground(new Color(12, 138, 199));
		separador3.setBounds(29, 309, 239, 6);
		pantalla.add(separador3);

		JSeparator separador4 = new JSeparator();
		separador4.setForeground(Color.WHITE);
		separador4.setBackground(new Color(12, 138, 199));
		separador4.setBounds(29, 389, 239, 6);
		pantalla.add(separador4);

		btnSiguiente = new JPanel();
		// Conversiones

		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ingCheckIn.getDate() != null && ingCheckOut.getDate() != null && ingValor.getValue() != null) {
					guardarReserva();
					// registroHuesped = new RegistroHuesped();
					// registroHuesped.setVisible(true);
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
					return;
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnSiguiente.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSiguiente.setBackground(SystemColor.textHighlight);
			}
		});
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setBounds(328, 397, 130, 46);
		pantalla.add(btnSiguiente);
		btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnSiguiente.setLayout(null);

		labelSiguiente = new JLabel("SIGUIENTE");
		labelSiguiente.setForeground(new Color(255, 255, 255));
		labelSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		labelSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		labelSiguiente.setBounds(0, 0, 130, 46);
		btnSiguiente.add(labelSiguiente);

	}

	private void formularioAtras() {
		formaPagoController = new FormaDePagoController();
		labelTitulo = new JLabel("SISTEMA DE RESERVAS");
		labelTitulo.setForeground(new Color(12, 138, 199));
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBounds(0, 33, 442, 36);
		pantalla.add(labelTitulo);

		labelCheckIn = new JLabel("FECHA DE CHECK IN");
		labelCheckIn.setFont(new Font("Arial", Font.BOLD, 16));
		labelCheckIn.setForeground(Color.DARK_GRAY);
		labelCheckIn.setHorizontalAlignment(SwingConstants.LEFT);
		labelCheckIn.setBounds(29, 78, 184, 36);
		pantalla.add(labelCheckIn);

		ingCheckIn = new JDateChooser();
		ingCheckIn.setDate(Date.valueOf(reservaAtras.getFechaEntrada()));
		ingCheckIn.getCalendarButton().setBackground(SystemColor.textHighlight);
		ingCheckIn.setBounds(29, 107, 314, 36);
		ingCheckIn.setFont(new Font("Arial", Font.BOLD, 14));
		String urlCalendario = "/alura/hotel/imagenes/reservado.png";
		ingCheckIn.getCalendarButton().setIcon(cargarImagen(urlCalendario, 24, 24));
		pantalla.add(ingCheckIn);
		ingCheckIn.setDateFormatString("yyyy-MM-dd");

		labelCheckOut = new JLabel("FECHA DE CHECK OUT");
		labelCheckOut.setFont(new Font("Arial", Font.BOLD, 16));
		labelCheckOut.setForeground(Color.DARK_GRAY);
		labelCheckOut.setHorizontalAlignment(SwingConstants.LEFT);
		labelCheckOut.setBounds(29, 159, 184, 36);
		pantalla.add(labelCheckOut);

		ingCheckOut = new JDateChooser();
		ingCheckOut.getCalendarButton().setBackground(SystemColor.textHighlight);
		ingCheckOut.setDate(Date.valueOf(reservaAtras.getFechaSalida()));
		ingCheckOut.setBounds(29, 191, 314, 36);
		ingCheckOut.setFont(new Font("Arial", Font.BOLD, 14));
		ingCheckOut.getCalendarButton().setIcon(cargarImagen(urlCalendario, 24, 24));
		pantalla.add(ingCheckOut);
		ingCheckOut.setDateFormatString("yyyy-MM-dd");

		labelValor = new JLabel("VALOR DE LA RESERVA");
		labelValor.setFont(new Font("Arial", Font.BOLD, 16));
		labelValor.setForeground(Color.DARK_GRAY);
		labelValor.setBounds(29, 244, 239, 36);
		pantalla.add(labelValor);

		// Para cambiar el tipo de JtextField
		NumberFormat floatFormat = new DecimalFormat("#0.00");
		NumberFormatter formatter = new NumberFormatter(floatFormat);
		formatter.setValueClass(Float.class);
		formatter.setMinimum(Float.MIN_VALUE);
		formatter.setMaximum(Float.MAX_VALUE);
		formatter.setAllowsInvalid(false);
		ingValor = new JFormattedTextField(formatter);
		ingValor.setBackground(SystemColor.text);
		ingValor.setHorizontalAlignment(SwingConstants.LEFT);
		ingValor.setBounds(29, 273, 314, 36);
		ingValor.setValue(reservaAtras.getValor());
		ingValor.setFont(new Font("Arial", Font.BOLD, 14));
		ingValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		ingValor.setColumns(10);
		pantalla.add(ingValor);

		labelFormaPago = new JLabel("FORMA DE PAGO");
		labelFormaPago.setFont(new Font("Arial", Font.BOLD, 16));
		labelFormaPago.setForeground(Color.DARK_GRAY);
		labelFormaPago.setBounds(29, 319, 239, 36);
		pantalla.add(labelFormaPago);

		ingFormaPago = new JComboBox<>();
		ingFormaPago.setFont(new Font("Arial", Font.BOLD, 14));
		ingFormaPago.setBounds(29, 351, 314, 36);
		ingFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		var metodosPago = this.formaPagoController.listar();
		metodosPago.forEach(metodo -> ingFormaPago.addItem(metodo));

		for (int i = 0; i < ingFormaPago.getItemCount(); i++) {
			FormaPago metPago = (FormaPago) ingFormaPago.getItemAt(i);
			if (metPago.getId() == reservaAtras.getFormaPagoId()) {
				ingFormaPago.setSelectedIndex(i);
				break;
			}
		}
		pantalla.add(ingFormaPago);

		JSeparator separador1 = new JSeparator();
		separador1.setBackground(new Color(0, 0, 160));
		separador1.setForeground(new Color(255, 255, 255));
		separador1.setBounds(29, 143, 239, 6);
		pantalla.add(separador1);

		JSeparator separador2 = new JSeparator();
		separador2.setForeground(Color.WHITE);
		separador2.setBackground(new Color(12, 138, 199));
		separador2.setBounds(29, 228, 239, 6);
		pantalla.add(separador2);

		JSeparator separador3 = new JSeparator();
		separador3.setForeground(Color.WHITE);
		separador3.setBackground(new Color(12, 138, 199));
		separador3.setBounds(29, 309, 239, 6);
		pantalla.add(separador3);

		JSeparator separador4 = new JSeparator();
		separador4.setForeground(Color.WHITE);
		separador4.setBackground(new Color(12, 138, 199));
		separador4.setBounds(29, 389, 239, 6);
		pantalla.add(separador4);
		btnSiguiente = new JPanel();
		btnSiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ingCheckIn.getDate() != null && ingCheckOut.getDate() != null && ingValor.getValue() != null) {
					modificarReserva();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Debes llenar todos los campos.");
					return;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnSiguiente.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnSiguiente.setBackground(SystemColor.textHighlight);
			}
		});
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setBounds(328, 397, 130, 46);
		pantalla.add(btnSiguiente);
		btnSiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnSiguiente.setLayout(null);

		labelSiguiente = new JLabel("SIGUIENTE");
		labelSiguiente.setForeground(new Color(255, 255, 255));
		labelSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		labelSiguiente.setFont(new Font("Arial", Font.BOLD, 14));
		labelSiguiente.setBounds(0, 0, 130, 46);
		btnSiguiente.add(labelSiguiente);
	}

	private void modificarReserva() {
		this.reservaController = new ReservaController();
		Float valor = (Float) ingValor.getValue();
		if (valor == null || valor == 0) {
			JOptionPane.showConfirmDialog(null, "Debes ingresar el valor de la reserva");
			return;
		}
		var metPago = (FormaPago) ingFormaPago.getSelectedItem();
		reservaController.modificar(convertirFecha(ingCheckIn), convertirFecha(ingCheckOut), valor, metPago.getId(),
				reservaAtras.getId());
		registroHuesped = new RegistroHuesped(reservaAtras);
		registroHuesped.setVisible(true);
	}

	private void guardarReserva() {
		this.reservaController = new ReservaController();
		Float valor = (Float) ingValor.getValue();
		if (valor == null || valor == 0) {
			JOptionPane.showConfirmDialog(null, "Debes ingresar el valor de la reserva");
			return;
		}
		var reserva = new Reserva(convertirFecha(ingCheckIn), convertirFecha(ingCheckOut), valor);
		var metPago = (FormaPago) ingFormaPago.getSelectedItem();
		reservaController.guardar(reserva, metPago.getId());
		registroHuesped = new RegistroHuesped(reserva);
		registroHuesped.setVisible(true);

	}

	// Conversor
	public LocalDate convertirFecha(JDateChooser dateChooser) {
		java.util.Date fechaUtil = dateChooser.getDate();
		LocalDate fechaLocalDate = fechaUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return fechaLocalDate;
	}

	private void imagenes() {
		imgLogo = new JLabel("");
		String urlLogo = "/alura/hotel/imagenes/Ha-100px.png";
		imgLogo.setIcon(cargarImagen(urlLogo, 100, 100));
		imgLogo.setBounds(100, 83, 100, 100);
		divisor.add(imgLogo);

		imgFondo = new JLabel("");
		imgFondo.setHorizontalAlignment(SwingConstants.CENTER);
		imgFondo.setBounds(10, 158, 273, 283);
		String urlFondo = "/alura/hotel/imagenes/reservas-img-3.png";
		imgFondo.setIcon(cargarImagen(urlFondo, 240, 260));
		divisor.add(imgFondo);

	}

	private static ImageIcon cargarImagen(String url, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(ReservasView.class.getResource(url));
		Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}

	private void headerMousePressed(MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}

	private void headerMouseDragged(MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
}
