package wooxes.net.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import wooxes.net.client.DataService;
import wooxes.net.shared.Ordinador;
import wooxes.net.shared.Usuari;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class DataServiceImpl extends RemoteServiceServlet implements DataService {

	public Usuari getUsuari(String user, String password){
		
		Usuari usuari = null;
		

		try {
			Connection conexion = Connexio.getConnexio();
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery ("select * from usuari where nick = '" + user + "' and password = '" + password + "'");
			
			if (rs.next()){
				usuari = new Usuari();
				usuari.setId(rs.getInt("id"));
				usuari.setNick(rs.getString("nick"));
				usuari.setNom(rs.getString("nom"));
				usuari.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("timestamp")));
			}

			rs.close();
			s.close();
			
			s = conexion.createStatement();
			// Un cop es tenen les dades de l'usuari, s'actualitza l'últim accés
	        int i = s.executeUpdate ( "UPDATE usuari SET timestamp = CURRENT_TIMESTAMP WHERE nick = '" + user + "' and password = '" + password + "'");

			s.close();
			conexion.close();
		} catch (SQLException e) {
			// Res a fer
		}
		
		return usuari;
	}
	
	public ArrayList<Ordinador> getArrayOrdinadors(Usuari user){
		return Connexio.getArrayOrdinadors(user);
	}
}