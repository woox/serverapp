package wooxes.net.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public class Usuari implements IsSerializable{
	private int id;
	private String nick;
	private String nom;
	private String fecha;

	public Usuari() {
	}

	public Usuari(int id, String nick, String nom) {
		this.id 	= id;
		this.nick 	= nick;
		this.nom 	= nom;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Panel getPanel(){
		FlowPanel root = new FlowPanel();

		Label hola = new Label("Hola " + getNom());
		HTML ultimAcces = new HTML("<b>Ultim accés:</b> " +getFecha());

		root.add(hola);
		root.add(ultimAcces);
		return root;
	}
}
