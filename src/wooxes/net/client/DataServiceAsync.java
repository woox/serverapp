package wooxes.net.client;

import wooxes.net.shared.Ordinador;
import wooxes.net.shared.Usuari;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

/**
 * The async counterpart of <code>DataService</code>.
 */
public interface DataServiceAsync {
	void getUsuari(String user, String password, AsyncCallback<Usuari> callback);
	void getArrayOrdinadors(Usuari user, AsyncCallback<ArrayList<Ordinador>> callback);
}
