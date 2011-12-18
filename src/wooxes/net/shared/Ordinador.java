package wooxes.net.shared;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

public class Ordinador implements IsSerializable{
	private int id;
	private String nom;
	private String fecha;
	private String mac;
	private Date timestamp;
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	private Red red;
	private Software software;
	private Hardware hardware;
	
	public Ordinador(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}

	public Red getRed() {
		return red;
	}
	public void setRed(Red red) {
		this.red = red;
	}

	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}

	public Hardware getHardware() {
		return hardware;
	}
	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	public Widget getWidgetEstat(){
		Date actual = new Date();
		long diferencia = actual.getTime() - timestamp.getTime();
		
		// Diferencia en minuts
		diferencia = diferencia / ( 1000 * 60 );
		
		if( diferencia >= 6 ){
			return new Image("error.jpg");
		}
		else{
			return new Image("ok.gif");
		}
	}
	
	public Panel getPanel(){
		FlowPanel root = new FlowPanel();
		FlowPanel pTop = new FlowPanel();
		FlowPanel pImage = new FlowPanel();
		FlowPanel pText = new FlowPanel();
		DisclosurePanel pBottom = new DisclosurePanel("Dades última actualització");


		Image image = new Image("ordinador.jpg");
		pImage.add(image);

		pText.add(new HTML("<b>ID:</b> "+Integer.toString(getId())));
		pText.add(new HTML("<b>Nom:</b> "+getNom()));
		pText.add(new HTML("<b>Actualització:</b> "+getFecha()));
		pText.add(getWidgetEstat());

		pTop.add(pImage);
		pTop.add(pText);
		
	
		pBottom.setWidth("100%");
		pBottom.addOpenHandler(new OpenHandler<DisclosurePanel>(){
			@Override
			public void onOpen(OpenEvent<DisclosurePanel> event) {
				// Al obrir, es carreguen les dades
				TabLayoutPanel pTab	= new TabLayoutPanel(2.5, Unit.EM);
				
				// Red
				pTab.add(getRed().getPanel(), "Red");
				pTab.add(getSoftware().getPanel(), "Software");
				pTab.add(getHardware().getPanel(), "Hardware");
				pTab.add(new Label("3"), "Fitxers");
				pTab.add(new Label("4"), "Memoria");
				pTab.selectTab(0);
				pTab.setWidth("100%");
				pTab.setHeight("200px");
				
				DisclosurePanel panel = event.getTarget();
				panel.setContent(pTab);
			}
		});
		
		pBottom.addCloseHandler(new CloseHandler<DisclosurePanel>(){

			@Override
			public void onClose(CloseEvent<DisclosurePanel> event) {
				DisclosurePanel panel = event.getTarget();
				panel.clear();
			}
		});
		
		root.add(pTop);
		root.add(pBottom);

		pImage.setStyleName("div_ordinador_image");
		pText.setStyleName("div_ordinador_text");
		root.setStyleName("div_ordinador");
		return root;
	}
}
