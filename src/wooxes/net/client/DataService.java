package wooxes.net.client;

import wooxes.net.shared.Ordinador;
import wooxes.net.shared.Usuari;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

/**
 * The client side stub for the RPC service.
 */

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService {
	Usuari getUsuari(String user, String password);
	ArrayList<Ordinador> getArrayOrdinadors(Usuari user);
}
