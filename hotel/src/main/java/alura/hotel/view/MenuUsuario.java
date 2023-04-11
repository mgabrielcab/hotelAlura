package alura.hotel.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import alura.hotel.controller.ReservaController;

public class MenuUsuario extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, header, btnExit, divisor, espacioFecha, btnRegistro, btnBusqueda;
	private JLabel labelExit, imgLogo, labelSystem, labelFecha, labelBienvenida, labelDescr1, labelDescr2, labelDescr3,
			labelDescr4, labelDescr5, labelRegistro, labelBusqueda;
	private LocalDate fechaActual;
	private DateTimeFormatter formatoFecha;
	private int xMouse, yMouse;
	private ReservasView reservasView;
	private ReservaController reservaController;

	/**
	 * Create the frame.
	 */
	public MenuUsuario() {
		estructuraBase();
		cargaImagen();
		introduccion();
		bienvenida();
		menu();

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
		divisor = new JPanel();
		divisor.setBackground(new Color(12, 138, 199));
		divisor.setBounds(0, 0, 257, 451);
		pantalla.add(divisor);
		divisor.setLayout(null);
		// Fin boton Cerrar

	}

	private void cargaImagen() {
		imgLogo = new JLabel("");
		imgLogo.setBounds(53, 40, 150, 150);
		imgLogo.setIcon(new ImageIcon(MenuUsuario.class.getResource("/alura/hotel/imagenes/aH-150px.png")));
		divisor.add(imgLogo);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(12, 202, 233, 2);
		divisor.add(panel);

	}

	private void introduccion() {
		// Obtengo fecha Actual
		fechaActual = LocalDate.now();
		// Formateo Fecha
		formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fechaActual.format(formatoFecha);
		// Panel
		espacioFecha = new JPanel();
		espacioFecha.setBackground(new Color(100, 149, 237));
		espacioFecha.setBounds(249, 48, 512, 99);
		pantalla.add(espacioFecha);
		espacioFecha.setLayout(null);
		// Sistema.....
		labelSystem = new JLabel("Sistema de reservas Hotel Alura");
		labelSystem.setBounds(82, 12, 339, 22);
		labelSystem.setForeground(new Color(255, 255, 255));
		labelSystem.setFont(new Font("Arial", Font.PLAIN, 20));
		espacioFecha.add(labelSystem);
		// Fecha
		String construccion = "Hoy es " + fechaFormateada;
		labelFecha = new JLabel(construccion);
		labelFecha.setForeground(new Color(255, 255, 255));
		labelFecha.setFont(new Font("Arial", Font.BOLD, 24));
		labelFecha.setBounds(133, 46, 218, 35);
		espacioFecha.add(labelFecha);

	}

	private void bienvenida() {
		labelBienvenida = new JLabel("Bienvenido");
		labelBienvenida.setFont(new Font("Dialog", Font.BOLD, 20));
		labelBienvenida.setBounds(275, 172, 117, 30);
		pantalla.add(labelBienvenida);

		String descripcion1 = "<html><body>Sistema de reserva de hotel. Controle y administre de forma óptima y fácil <br> el flujo de reservas y de huespédes del hotel   </body></html>";
		labelDescr1 = new JLabel(descripcion1);
		labelDescr1.setFont(new Font("Arial", Font.PLAIN, 16));
		labelDescr1.setBounds(275, 201, 486, 72);
		pantalla.add(labelDescr1);

		String descripcion2 = "<html><body> Esta herramienta le permitirá llevar un control completo y detallado de sus reservas y huéspedes, tendrá acceso a heramientas especiales para tareas específicas como lo son:</body></html>";
		labelDescr2 = new JLabel(descripcion2);
		labelDescr2.setFont(new Font("Arial", Font.PLAIN, 16));
		labelDescr2.setBounds(275, 271, 486, 64);
		pantalla.add(labelDescr2);

		labelDescr3 = new JLabel("- Registro y Reservas de Húespedes.");
		labelDescr3.setFont(new Font("Arial", Font.PLAIN, 16));
		labelDescr3.setBounds(313, 340, 305, 30);
		pantalla.add(labelDescr3);

		labelDescr4 = new JLabel("- Edición de Reservas y Huéspedes existentes.");
		labelDescr4.setFont(new Font("Arial", Font.PLAIN, 16));
		labelDescr4.setBounds(313, 371, 395, 30);
		pantalla.add(labelDescr4);

		labelDescr5 = new JLabel("-Eliminar todo tipo de Registros.");
		labelDescr5.setFont(new Font("Arial", Font.PLAIN, 16));
		labelDescr5.setBounds(313, 409, 395, 30);
		pantalla.add(labelDescr5);
	}

	private void menu() {
		// Elimino reservas sin huespedes
		reservaController = new ReservaController();
		reservaController.controlReservasinHuesped();
		// Boton Registro
		btnRegistro = new JPanel();
		btnRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRegistro.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnRegistro.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				reservasView = new ReservasView();
				reservasView.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBackground(new Color(12, 138, 199));
		btnRegistro.setBounds(0, 242, 257, 42);
		divisor.add(btnRegistro);

		labelRegistro = new JLabel("Registro de Reservas");
		labelRegistro.setIcon(new ImageIcon(MenuUsuario.class.getResource("/alura/hotel/imagenes/icon-reservas.png")));
		labelRegistro.setForeground(SystemColor.text);
		labelRegistro.setBounds(0, 254, 257, 30);
		labelRegistro.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		btnRegistro.add(labelRegistro);

		// Boton Busqueda
		btnBusqueda = new JPanel();
		btnBusqueda.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBusqueda.setBackground(new Color(118, 187, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBusqueda.setBackground(new Color(12, 138, 199));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Busqueda busqueda = new Busqueda();
				busqueda.setVisible(true);
				dispose();
			}
		});
		btnBusqueda.setBackground(new Color(12, 138, 199));
		btnBusqueda.setBounds(0, 294, 257, 42);
		divisor.add(btnBusqueda);
		btnBusqueda.setLayout(null);

		labelBusqueda = new JLabel("Búsqueda");
		labelBusqueda.setIcon(new ImageIcon(MenuUsuario.class.getResource("/alura/hotel/imagenes/icon-buscar.png")));
		labelBusqueda.setForeground(SystemColor.text);
		labelBusqueda.setBounds(27, 0, 144, 42);
		labelBusqueda.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelBusqueda.setHorizontalAlignment(SwingConstants.LEFT);
		btnBusqueda.add(labelBusqueda);

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
