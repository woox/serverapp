package wooxes.net.shared;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class Usuari implements IsSerializable{
	private int id;
	private String nick;
	private String nom;
	private String fecha;
	private String imatge;
	
	public Usuari() {
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

	public String getImatge() {
		return imatge;
	}

	public void setImatge(String imatge) {
		this.imatge = imatge;
	}
	
	public Panel getPanel(){
		FlowPanel root = new FlowPanel();

		Label hola = new Label("Hola " + getNom());
		HTML ultimAcces = new HTML("<b>Ultim accés:</b> " +getFecha());

		root.add(hola);
		root.add(ultimAcces);
		return root;
	}
	public Panel getConfiguracioPanel(){
		FlowPanel root = new FlowPanel();
		FlexTable pTop = new FlexTable();
		FlexTable pPass = new FlexTable();
		
		// Dades generals
		HTML generalTitul = new HTML("<b><u>Dades generales</u></b>");
		HTML nomLabel = new HTML("<b>Nom:</b>");
		TextBox nomField = new TextBox();
		nomField.setText(getNom());
		
		HTML imatgeLabel = new HTML("<b>Imatge:</b>");

		final ListBox imatgeList = new ListBox();
		imatgeList.addItem("Actual", imatge);
		imatgeList.addItem("Ok", "ok.gif");
		imatgeList.addItem("Error", "error.jpg");
		imatgeList.addItem("Refresh", "refresh.jpg");

		// S'asigna l'imatge actual
		final Image imatgeImage = new Image(imatge);

		imatgeList.addChangeHandler(new ChangeHandler(){
			@Override
			public void onChange(ChangeEvent event) {
				int index = imatgeList.getSelectedIndex();
				String url = imatgeList.getValue(index);
				imatgeImage.setUrl(url);
			}
		});
		
		Button guardarGenButton = new Button("Guardar");
		guardarGenButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("no s'ha guardat!");
				
			}
		});
		
		pTop.setWidget(0, 0, generalTitul);
		pTop.setWidget(1, 0, nomLabel);
		pTop.setWidget(1, 1, nomField);
		pTop.setWidget(2, 0, imatgeLabel);
		pTop.setWidget(2, 1, imatgeList);
		pTop.setWidget(2, 2, imatgeImage);
		pTop.setWidget(3, 0, guardarGenButton);
		pTop.getFlexCellFormatter().setColSpan(0, 0, 3);
		pTop.getFlexCellFormatter().setColSpan(3, 0, 3);

		// Password
		
		HTML passTitul = new HTML("<b><u>Password</u></b>");
		HTML passAntLabel = new HTML("<b>Password actual:</b>");
		HTML passNouLabel = new HTML("<b>Password nou:</b>");
		HTML passRepLabel = new HTML("<b>Password repetir:</b>");
		PasswordTextBox passAntField = new PasswordTextBox();
		PasswordTextBox passNouField = new PasswordTextBox();
		PasswordTextBox passRepField = new PasswordTextBox();
		
		Button guardarPassButton = new Button("Guardar");
		guardarPassButton.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				Window.alert("no s'ha guardat el password!");
				
			}
		});
		
		pPass.setWidget(0, 0, passTitul);
		pPass.setWidget(1, 0, passAntLabel);
		pPass.setWidget(1, 1, passAntField);
		pPass.setWidget(2, 0, passNouLabel);
		pPass.setWidget(2, 1, passNouField);
		pPass.setWidget(3, 0, passRepLabel);
		pPass.setWidget(3, 1, passRepField);
		pPass.setWidget(4, 0, guardarPassButton);
		pPass.getFlexCellFormatter().setColSpan(0, 0, 3);
		pPass.getFlexCellFormatter().setColSpan(4, 0, 3);
		
		// Assignar al container
		root.add(pTop);
		root.add(pPass);

		pTop.setStyleName("div_configuracio");
		pPass.setStyleName("div_configuracio");
		return root;
	}
}
