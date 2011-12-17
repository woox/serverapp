package wooxes.net.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import wooxes.net.shared.Hardware;
import wooxes.net.shared.Ordinador;
import wooxes.net.shared.Red;
import wooxes.net.shared.Software;
import wooxes.net.shared.Usuari;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Connexio {

	private static Connection connexio = null;

	
	public static Connection getConnexio() throws SQLException{
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("serverapp");
		dataSource.setPassword("serverapp1234");
		dataSource.setDatabaseName("serverapp");
		dataSource.setServerName("10.3.192.3");

		try {
			connexio = dataSource.getConnection();
		}
		catch (SQLException e) {
			throw e;
		}

		return connexio;
	}
	
	public static boolean checkOrdinador(int id, String clau){
		boolean result = false;

		try{
			Connection connection = getConnexio();
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery ("select id from ordinador where id = " + id + " and clau = '" + clau + "'");
			
			if (rs.next()){
				result = true;
			}
			
			rs.close();
			connection.close();
		}
		catch (SQLException e){
			// Res a fer
		}
		
		return result;
	}

	public static ArrayList<Ordinador> getArrayOrdinadors(Usuari user){
		ArrayList<Ordinador> array = new ArrayList<Ordinador>();
		Iterator<Ordinador> it;
		Ordinador ordinador;
		
		try {
			Connection conexion = Connexio.getConnexio();
			Statement s = conexion.createStatement();
			ResultSet rs = s.executeQuery (
					"SELECT ordinador.* " +
			        "FROM ordinador " +
			        "INNER JOIN us_or " +
			        "ON us_or.ordinador = ordinador.id " +
			        "WHERE us_or.usuari = '"+user.getId()+"'"
			        );
			
			while (rs.next()){
				ordinador = new Ordinador();
				ordinador.setId(rs.getInt("id"));
				ordinador.setNom(rs.getString("nom"));
				ordinador.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("timestamp")));
				ordinador.setTimestamp(rs.getTimestamp("timestamp"));
				
				array.add(ordinador);
			}

			rs.close();

			it = array.iterator();
			while( it.hasNext()){
				ordinador = it.next();
				
				// Red
				rs = s.executeQuery(
						"SELECT red.* " +
						"FROM red " +
						"WHERE id_ordinador = " + ordinador.getId() + " " +
						"ORDER BY timestamp DESC " + 
						"LIMIT 1"
						);
				
				if (rs.next()){
					Red red = new Red();
					red.setIpInterna(rs.getString("ip_interna"));
					red.setIpExterna(rs.getString("ip_externa"));
					red.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("timestamp")));
					
					ordinador.setRed(red);
				}
				rs.close();
				
				rs = s.executeQuery(
						"SELECT software.* " +
						"FROM software " +
						"WHERE id_ordinador = " + ordinador.getId() + " " +
						"ORDER BY timestamp DESC " + 
						"LIMIT 1"
						);
				
				if (rs.next()){
					Software software = new Software();
					software.setDescripcio(rs.getString("descripcio"));
					software.setNom(rs.getString("nom"));
					software.setArquitectura(rs.getString("arquitectura"));
					software.setVersio(rs.getString("versio"));
					software.setUptime(rs.getString("uptime"));
					software.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("timestamp")));

					ordinador.setSoftware(software);
				}
				rs.close();

				rs = s.executeQuery(
						"SELECT hardware.* " +
						"FROM hardware " +
						"WHERE id_ordinador = " + ordinador.getId() + " " +
						"ORDER BY timestamp DESC " + 
						"LIMIT 1"
						);
				
				if (rs.next()){
					Hardware hardware = new Hardware();
					hardware.setFabricant(rs.getString("fabricant"));
					hardware.setModel(rs.getString("model"));
					hardware.setMhz(rs.getString("mhz"));
					hardware.setTotalCpu(rs.getString("total_cpu"));
					hardware.setCpuFisica(rs.getString("cpu_fisica"));
					hardware.setNuclisCpu(rs.getString("nuclis_cpu"));
					hardware.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("timestamp")));

					ordinador.setHardware(hardware);
				}
				rs.close();
			}
			
			conexion.close();
		} catch (SQLException e) {
			// Res a fer
		}

		return array;
	}
}
