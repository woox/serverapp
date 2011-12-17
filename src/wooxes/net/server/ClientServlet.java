package wooxes.net.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wooxes.net.server.Connexio;
 
@SuppressWarnings("serial")
public class ClientServlet extends HttpServlet {
 
	
    /**
     * Servlet de ejemplo que procesa una petición GET
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	
    	int id = 0;
    	try{
    		id = Integer.parseInt(request.getParameter("id"));
    	}
    	catch(Exception e){
    		// ID no vàlid
    		out.println("ID no vàlid");
    	}
    	String clau = request.getParameter("clau");
    	
    	if( clau == null ){
    		out.println("Clau no vàlida");
    		return;
    	}

    	// Es comproba la clau
    	if( Connexio.checkOrdinador(id, clau) == false ){
    		out.println("ID o clau no vàlida");
    		return;
    	}

    	out.println("ID: " + Integer.toString(id));

    	out.println("Actualitzant Timestamp");
    	if( updateTimestamp(out, Integer.parseInt(request.getParameter("id")), request.getParameter("clau")) == false){
    		return;
    	}
    	
    	String operacio = request.getParameter("o");
    	if( operacio == null){
    		// No s'ha especificat cap operació, es surt
    		return;
    	}
    }

	public boolean updateTimestamp(PrintWriter out, int id, String clau){
        String query = "UPDATE ordinador SET timestamp = CURRENT_TIMESTAMP WHERE id = "+ id;

		try {
			Connection conexion = Connexio.getConnexio();
			Statement s = conexion.createStatement();
			int num = s.executeUpdate ( query );
	
			s.close();
			conexion.close();
			if( num == 1){
				out.println("OK");
				return true;
			}
			else{
				out.println("Error");
			}
		} catch (SQLException e) {
			// Res a fer
			out.println(e.toString());
		}
		return false;

    }

	public String updateRed(int id, String key){
    	String resultat = "";
        if( Connexio.checkOrdinador(id, key)){
            String query = "UPDATE ordinador SET timestamp = CURRENT_TIMESTAMP WHERE id = "+ id;

    		try {
    			Connection conexion = Connexio.getConnexio();
    			Statement s = conexion.createStatement();
    			int num = s.executeUpdate ( query );
    			
    			if( num == 1){
    				resultat = "OK";
    			}
    	
    			s.close();
    			conexion.close();
    		} catch (SQLException e) {
    			// Res a fer
    			resultat = "Error: " + e.toString();
    		}
        }
        else{
        	resultat = "Error: Key no vàlida";
        }
        return resultat;
	}
}