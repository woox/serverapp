package wooxes.net.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

public class Software implements IsSerializable{

	public int id_ordinador;
	public String fecha;
	public String descripcio;
	public String nom;
	public String arquitectura;
	public String versio;
	public String uptime;

	public Software(){

	}
	
	public int getIdOrdinador() {
		return id_ordinador;
	}
	public void setIdOrdinador(int id_ordinador) {
		this.id_ordinador = id_ordinador;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDescripcio() {
		return descripcio;
	}
	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getArquitectura() {
		return arquitectura;
	}
	public void setArquitectura(String arquitectura) {
		this.arquitectura = arquitectura;
	}
	public String getVersio() {
		return versio;
	}
	public void setVersio(String versio) {
		this.versio = versio;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public Panel getPanel(){
		FlowPanel root = new FlowPanel();
		root.add(new HTML("<b>Actualització:</b> "+getFecha()));
		root.add(new HTML("<b>Descripció:</b> "+getDescripcio()));
		root.add(new HTML("<b>Nom:</b> "+getNom()));
		root.add(new HTML("<b>Arquitectura:</b> "+getArquitectura()));
		root.add(new HTML("<b>Versio:</b> "+getVersio()));
		root.add(new HTML("<b>Uptime:</b> "+getUptime()));

		return root;
	}
	
}
