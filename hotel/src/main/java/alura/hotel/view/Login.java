package alura.hotel.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pantalla, btnEntrar, btnExit;
	private JLabel imgLogo, titleIniciarSesion, user, pass, labelExit, imgHotel, labelEntrar;
	private JTextField ingresoUsuario;
	private int xMouse, yMouse;
	private JPasswordField ingPass;
	private String defaultUser, defaultPass;
	private MenuUsuario menuUsuario;

	/**
	 * Create the frame.
	 */
	public Login() {
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
		JPanel header = new JPanel();
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
		labelExit.setBounds(-11, 0, 53, 36);
		btnExit.add(labelExit);
		labelExit.setForeground(SystemColor.text);
		labelExit.setFont(new Font("DialogInput", Font.BOLD, 15));
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);

		// carga de la imagen
		imgLogo = new JLabel(new ImageIcon(Login.class.getResource("/alura/hotel/imagenes/aH-40px.png")));
		imgLogo.setBounds(8, 66, 107, 60);
		pantalla.add(imgLogo);
		// fin carga imagen
		titleIniciarSesion = new JLabel("INICIAR SESIÓN");
		titleIniciarSesion.setForeground(new Color(0, 51, 255));
		titleIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 20));
		titleIniciarSesion.setBounds(54, 124, 210, 25);
		pantalla.add(titleIniciarSesion);

		user = new JLabel("USUARIO");
		user.setForeground(SystemColor.textInactiveText);
		user.setFont(new Font("Tahoma", Font.BOLD, 18));
		user.setBounds(54, 171, 180, 35);
		pantalla.add(user);

		ingresoUsuario = new JTextField();
		ingresoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ingresoUsuario.setBounds(54, 214, 285, 35);
		ingresoUsuario.setText("Ingrese su nombre de Usuario");
		ingresoUsuario.setBorder(BorderFactory.createEmptyBorder());
		pantalla.add(ingresoUsuario);
		ingresoUsuario.setColumns(10);

		pass = new JLabel("CONTRASEÑA");
		pass.setForeground(SystemColor.textInactiveText);
		pass.setFont(new Font("Tahoma", Font.BOLD, 18));
		pass.setBounds(54, 271, 180, 35);
		pantalla.add(pass);

		ingPass = new JPasswordField();
		ingPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (String.valueOf(ingPass.getPassword()).equals("**********")) {
					ingPass.setText("");
					ingPass.setForeground(Color.black);
				}
				if (ingresoUsuario.getText().isEmpty()) {
					ingresoUsuario.setText("Ingrese su nombre de Usuario");
					ingresoUsuario.setForeground(Color.gray);
				}
			}
		});
		ingPass.setText("**********");
		ingPass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ingPass.setColumns(10);

		ingPass.setBounds(54, 314, 285, 35);
		ingPass.setBorder(BorderFactory.createEmptyBorder());
		pantalla.add(ingPass);

		// Boton entrar
		btnEntrar = new JPanel();
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEntrar.setBackground(new Color(0, 156, 223));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEntrar.setBackground(SystemColor.textHighlight);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				menuPrincipal();
			}
		});
		btnEntrar.setBackground(SystemColor.textHighlight);
		btnEntrar.setBounds(54, 357, 122, 44);
		pantalla.add(btnEntrar);
		btnEntrar.setLayout(null);
		btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		labelEntrar = new JLabel("ENTRAR");
		labelEntrar.setBounds(0, 0, 122, 44);
		btnEntrar.add(labelEntrar);
		labelEntrar.setForeground(SystemColor.controlHighlight);
		labelEntrar.setHorizontalAlignment(SwingConstants.CENTER);
		labelEntrar.setFont(new Font("Roboto", Font.PLAIN, 18));

		JPanel divisor = new JPanel();
		divisor.setBackground(new Color(12, 138, 199));
		divisor.setBounds(355, 0, 406, 451);
		pantalla.add(divisor);
		divisor.setLayout(null);
		// carga de imagen
		imgHotel = new JLabel("");
		imgHotel.setBounds(64, -24, 285, 475);
		imgHotel.setPreferredSize(new Dimension(152, 269));
		divisor.add(imgHotel);
		imgHotel.setIcon(new ImageIcon(Login.class.getResource("/alura/hotel/imagenes/img-hotel-login-.png")));

	}

	protected void menuPrincipal() {
		defaultUser = "admin";
		defaultPass = "admin";
		String entradaCont = new String(ingPass.getPassword());
		if (ingresoUsuario.getText().equals(defaultUser) && entradaCont.equals(defaultPass)) {
			System.out.println("Defecto User: " + defaultUser);
			System.out.println("Defecto Pass: " + defaultPass);
			System.out.println("User Ingresado: " + ingresoUsuario.getText());
			System.out.println("Pass ingresada: " + entradaCont);
			System.out.println("Coincide");
			dispose();
			menuUsuario = new MenuUsuario();
			menuUsuario.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Usuario o Contraseña no válidos");
		}

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
