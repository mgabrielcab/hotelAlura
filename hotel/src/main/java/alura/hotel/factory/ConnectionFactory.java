package alura.hotel.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author mgabb
 */
public class ConnectionFactory {
	private DataSource dataSourse;

	/**
	 * Constructor de la clase ConnectionFactory. Configura la conexi�n a la base de
	 * datos MySQL utilizando la biblioteca C3PO. Se establece la URL JDBC, el
	 * nombre de usuario y la contrase�a. Tambi�n se establece el tama�o m�ximo de
	 * la piscina de conexiones en 5.
	 */
	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("root");
		pooledDataSource.setMaxPoolSize(5);
		this.dataSourse = pooledDataSource;
	}

	/**
	 * Recupera una conexi�n a la base de datos de la piscina de conexiones.
	 * 
	 * @return una conexi�n a la base de datos.
	 * @throws RuntimeException si no se puede establecer la conexi�n.
	 */
	public Connection recuperaConexion() {
		try {
			return this.dataSourse.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
