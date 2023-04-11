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
import java.time.ZoneId;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import com.toedter.calendar.JDateChooser;

import alura.hotel.controller.HuespedController;
import alura.hotel.controller.NacionalidadController;
import alura.hotel.model.Huesped;
import alura.hotel.model.Nacionalidad;
import alura.hotel.model.Reserva;

public class RegistroHuesped extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, header, btnAtras, btnExit, divisor, btnGuardar;
	private JTextField ingNombre, ingApellido, ingTelefono;
	private JLabel labelAtras, labelExit, labelTitulo, labelNombre, labelApellido, labelFechaNacimiento, labelTelefono,
			labelNumeroReserva, labelGuardar, labelNacionalidad, imgLogo, imgFondo, numeroReserva;
	private int xMouse, yMouse;
	private JComboBox<Nacionalidad> ingNacionalidad;
	private JDateChooser ingFechaNac;
	private HuespedController huespedController;
	private Reserva reservaRealizada;
	// private Huesped huesped;
	private NacionalidadController nacionalidadController;
	private Exito exito;

	/**
	 * Create the frame.
	 * 
	 * @param reserva
	 */
	public RegistroHuesped(Reserva reserva) {
		this.reservaRealizada = reserva;
		estructuraBase();
		imagenes();
		formulario();

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
		labelAtras.setForeground(new Color(255, 255, 255));
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setBounds(17, 0, 32, 27);
		labelAtras.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnAtras.add(labelAtras);

		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Deberia enviar los adtos de la reserva que se hizo
				ReservasView reservaView = new ReservasView(reservaRealizada);
				reservaView.setVisible(true);
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
				btnExit.setBackground(new Color(255, 255, 255));
				labelExit.setForeground(Color.black);
			}
		});
		btnExit.setBackground(new Color(255, 255, 255));
		btnExit.setLayout(null);
		btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

		labelExit = new JLabel("X");
		labelExit.setBackground(Color.WHITE);
		labelExit.setBounds(-11, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setForeground(new Color(0, 0, 0));
		labelExit.setFont(new Font("DialogInput", Font.BOLD, 15));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		divisor = new JPanel();
		divisor.setBackground(new Color(12, 138, 199));
		divisor.setBounds(0, 0, 432, 451);
		pantalla.add(divisor);
		divisor.setLayout(null);
	}

	private void imagenes() {
		String urlLogo = "/alura/hotel/imagenes/Ha-100px.png";
		imgLogo = new JLabel("");
		imgLogo.setBounds(140, 38, 100, 100);
		imgLogo.setIcon(cargarImagen(urlLogo, 100, 100));
		divisor.add(imgLogo);

		String urlFondo = "/alura/hotel/imagenes/registro.png";
		imgFondo = new JLabel("");
		imgFondo.setBounds(51, 121, 312, 330);
		imgFondo.setIcon(cargarImagen(urlFondo, 300, 330));
		divisor.add(imgFondo);
	}

	private void formulario() {
		nacionalidadController = new NacionalidadController();

		labelTitulo = new JLabel("REGISTRO HUÉSPED");
		labelTitulo.setForeground(new Color(12, 138, 199));
		labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setBounds(487, 36, 247, 36);
		pantalla.add(labelTitulo);

		labelNombre = new JLabel("NOMBRE");
		labelNombre.setFont(new Font("Arial", Font.BOLD, 16));
		labelNombre.setForeground(Color.DARK_GRAY);
		labelNombre.setBounds(469, 82, 92, 19);
		pantalla.add(labelNombre);
		// 18 espacio
		ingNombre = new JTextField();
		ingNombre.setFont(new Font("Arial", Font.BOLD, 14));
		ingNombre.setBorder(bordeInferior());
		ingNombre.setBounds(469, 100, 265, 36);
		pantalla.add(ingNombre);
		// 46 espacion
		labelApellido = new JLabel("APELLIDO");
		labelApellido.setFont(new Font("Arial", Font.BOLD, 16));
		labelApellido.setForeground(Color.DARK_GRAY);
		labelApellido.setBounds(469, 146, 92, 17);
		pantalla.add(labelApellido);
		// 18
		ingApellido = new JTextField();
		ingApellido.setFont(new Font("Arial", Font.BOLD, 14));
		ingApellido.setBorder(bordeInferior());
		ingApellido.setBounds(469, 164, 265, 36);
		pantalla.add(ingApellido);
		// 46
		labelFechaNacimiento = new JLabel("FECHA DE NACIMIENTO");
		labelFechaNacimiento.setFont(new Font("Arial", Font.BOLD, 16));
		labelFechaNacimiento.setForeground(Color.DARK_GRAY);
		labelFechaNacimiento.setBounds(469, 210, 265, 19);
		pantalla.add(labelFechaNacimiento);
		// 18
		ingFechaNac = new JDateChooser();
		String urlCalendar = "/alura/hotel/imagenes/reservado.png";
		ingFechaNac.setFont(new Font("Arial", Font.BOLD, 14));
		ingFechaNac.getCalendarButton().setBackground(SystemColor.textHighlight);
		ingFechaNac.getCalendarButton().setIcon(cargarImagen(urlCalendar, 24, 24));
		ingFechaNac.setBorder(bordeInferior());
		ingFechaNac.setBounds(469, 228, 265, 36);
		ingFechaNac.setDateFormatString("yyyy-MM-dd");
		pantalla.add(ingFechaNac);
		// 46
		labelNacionalidad = new JLabel("NACIONALIDAD");
		labelNacionalidad.setFont(new Font("Arial", Font.BOLD, 16));
		labelNacionalidad.setForeground(Color.DARK_GRAY);
		labelNacionalidad.setBounds(469, 274, 265, 19);
		pantalla.add(labelNacionalidad);
		// 18
		ingNacionalidad = new JComboBox<>();
		ingNacionalidad.setBorder(bordeInferior());
		ingNacionalidad.addItem(new Nacionalidad(0, "Seleccione Nacionalidad"));
		ingNacionalidad.setFont(new Font("Arial", Font.BOLD, 14));
		ingNacionalidad.setBounds(469, 292, 265, 36);
		var nacionalidad = this.nacionalidadController.listar();
		nacionalidad.forEach(nac -> ingNacionalidad.addItem(nac));
		pantalla.add(ingNacionalidad);
		// 46
		labelTelefono = new JLabel("TELEFONO");
		labelTelefono.setFont(new Font("Arial", Font.BOLD, 16));
		labelTelefono.setForeground(Color.DARK_GRAY);
		labelTelefono.setBounds(469, 338, 265, 19);
		pantalla.add(labelTelefono);
		// 18
		ingTelefono = new JTextField();
		ingTelefono.setFont(new Font("Arial", Font.BOLD, 14));
		ingTelefono.setBorder(bordeInferior());
		ingTelefono.setBounds(469, 356, 265, 36);
		pantalla.add(ingTelefono);
		// 46
		labelNumeroReserva = new JLabel("N° RESERVA");
		labelNumeroReserva.setBounds(469, 402, 105, 19);
		labelNumeroReserva.setFont(new Font("Arial", Font.BOLD, 16));
		labelNumeroReserva.setForeground(Color.DARK_GRAY);
		pantalla.add(labelNumeroReserva);

		numeroReserva = new JLabel(String.valueOf(reservaRealizada.getId()));
		numeroReserva.setFont(new Font("Arial", Font.BOLD, 16));
		numeroReserva.setForeground(Color.DARK_GRAY);
		numeroReserva.setBounds(469, 422, 92, 19);
		pantalla.add(numeroReserva);

		btnGuardar = new JPanel();
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exito = new Exito();
				exito.setVisible(true);
				guardar();
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnGuardar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(SystemColor.textHighlight);
			}
		});
		btnGuardar.setBackground(SystemColor.textHighlight);
		btnGuardar.setBounds(646, 399, 105, 46);
		pantalla.add(btnGuardar);
		btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnGuardar.setLayout(null);

		labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Arial", Font.BOLD, 18));
		labelGuardar.setBounds(0, 0, 105, 46);
		btnGuardar.add(labelGuardar);

	}

	private void guardar() {
		this.huespedController = new HuespedController();
		if (ingNombre.getText().isEmpty() || ingApellido.getText().isEmpty() || ingFechaNac == null
				|| ingNacionalidad.getSelectedItem() == null || ingTelefono.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debes rellenar todos los campos");
			return;
		}
		var huesped = new Huesped(ingNombre.getText(), ingApellido.getText(), convertirFechaLocal(ingFechaNac),
				ingTelefono.getText(), reservaRealizada.getId());
		var nacion = (Nacionalidad) ingNacionalidad.getSelectedItem();
		huespedController.guardar(huesped, nacion.getId());

	}

	public LocalDate convertirFechaLocal(JDateChooser dateChooser) {
		java.util.Date fechaUtil = dateChooser.getDate();
		LocalDate fechaLocalDate = fechaUtil.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return fechaLocalDate;
	}

	private MatteBorder bordeInferior() {
		return new MatteBorder(0, 0, 2, 0, (Color) new Color(12, 138, 199));
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
