package alura.hotel.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, btnExit, header, divisor;
	private JLabel labelExit, imgFondo, imgLogo, labelLogin, imgLogin;
	private int xMouse, yMouse;
	private Login login;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		estructuraBase();
		fondoPantalla();
		logo();
		iniciarSesion();
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
		divisor.setBounds(0, 0, 583, 451);
		pantalla.add(divisor);
		divisor.setLayout(null);
		// Fin boton Cerrar
	}

	private void logo() {
		imgLogo = new JLabel("");
		String urlLogo = "/alura/hotel/imagenes/aH-150px.png";
		imgLogo.setIcon(cargarImagen(urlLogo, 130, 130));
		imgLogo.setBounds(593, 89, 139, 131);
		pantalla.add(imgLogo);
	}

	private void fondoPantalla() {
		imgFondo = new JLabel("");
		String urlFondo = "/alura/hotel/imagenes/menu-img.png";
		imgFondo.setIcon(cargarImagen(urlFondo, 706, 451));
		imgFondo.setBounds(0, 0, 706, 451);
		divisor.add(imgFondo);
	}

	private static ImageIcon cargarImagen(String url, int ancho, int alto) {
		ImageIcon icono = new ImageIcon(MenuPrincipal.class.getResource(url));
		Image imagen = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagen);
	}

	private void iniciarSesion() {
		labelLogin = new JLabel("LOGIN");
		labelLogin.setForeground(new Color(0, 153, 255));
		labelLogin.setFont(new Font("Arial", Font.BOLD, 24));
		labelLogin.setBounds(619, 239, 80, 36);
		pantalla.add(labelLogin);

		imgLogin = new JLabel("");
		String urlLogin = "/alura/hotel/imagenes/login.png";
		imgLogin.setIcon(cargarImagen(urlLogin, 64, 64));
		imgLogin.setBounds(629, 285, 64, 77);
		imgLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

		pantalla.add(imgLogin);
		imgLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login = new Login();
				login.setVisible(true);
				dispose();

			}
		});
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
