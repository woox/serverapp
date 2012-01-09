package wooxes.net.client;

import wooxes.net.shared.*;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Serverapp implements EntryPoint {

	private Usuari usuari = null;
	private ArrayList<Ordinador> arrayOrdinador = null;
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";


	// Serveis
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private final DataServiceAsync dataService = GWT.create(DataService.class);

	// Imatges
	private final String imageRefresh = "refresh.jpg";
	
	// MÃ©tode principal
	public void onModuleLoad() {
		// Login Inicial
		final Label nameTxt	  	= new Label("Introdueix el teu nom:");
		final TextBox nameField = new TextBox();
		nameField.setText("woox");
		final PasswordTextBox namePass = new PasswordTextBox();
		namePass.setText("1234");
		final Button nameButton = new Button("Enviar");
		final Label errorLabel = new Label();

		// Assignacio
		//pTop.add(title);
		//pTop.add(titleImg);
		
		//pMenu.add(menu1);
		//pMenu.add(menu2);
		
		//pContent.add(nameField);
		//pContent.add(sendButton);
		
//		pRoot.add(pTop, DockPanel.NORTH);
//		pRoot.add(pMenu, DockPanel.WEST);
//		pRoot.add(pContent, DockPanel.CENTER);

		// We can add style names to widgets
		//sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
		RootPanel.get("content").add(nameTxt);
		RootPanel.get("content").add(nameField);
		RootPanel.get("content").add(namePass);
		RootPanel.get("content").add(nameButton);
		// RootPanel.get("sendButtonContainer").add(sendButton);
		// RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		
//		// Create the popup dialog box
//		final DialogBox dialogBox = new DialogBox();
//		dialogBox.setText("Remote Procedure Call");
//		dialogBox.setAnimationEnabled(true);
//		final Button closeButton = new Button("Close");
//		// We can set the id of a widget by accessing its Element
//		closeButton.getElement().setId("closeButton");
//		final Label textToServerLabel = new Label();
//		final HTML serverResponseLabel = new HTML();
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
//		dialogVPanel.add(textToServerLabel);
//		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
//		dialogVPanel.add(serverResponseLabel);
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		dialogVPanel.add(closeButton);
//		dialogBox.setWidget(dialogVPanel);
//
//		// Add a handler to close the DialogBox
//		closeButton.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				dialogBox.hide();
//				nameButton.setEnabled(true);
//				nameButton.setFocus(true);
//			}
//		});
//
//		// Create a handler for the sendButton and nameField
//		class MyHandler implements ClickHandler, KeyUpHandler {
//			/**
//			 * Fired when the user clicks on the sendButton.
//			 */
//			public void onClick(ClickEvent event) {
//				sendNameToServer();
//			}
//
//			/**
//			 * Fired when the user types in the nameField.
//			 */
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					sendNameToServer();
//				}
//			}
//
//			/**
//			 * Send the name from the nameField to the server and wait for a response.
//			 */
//			private void sendNameToServer() {
//				// First, we validate the input.
//				errorLabel.setText("");
//				String textToServer = nameField.getText();
//				if (!FieldVerifier.isValidName(textToServer)) {
//					errorLabel.setText("Please enter at least four characters");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				nameButton.setEnabled(false);
//				textToServerLabel.setText(textToServer);
//				serverResponseLabel.setText("");
//				greetingService.greetServer(textToServer,
//						new AsyncCallback<String>() {
//							public void onFailure(Throwable caught) {
//								// Show the RPC error message to the user
//								dialogBox
//										.setText("Remote Procedure Call - Failure");
//								serverResponseLabel
//										.addStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(SERVER_ERROR);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//
//							public void onSuccess(String result) {
//								dialogBox.setText("Remote Procedure Call");
//								serverResponseLabel
//										.removeStyleName("serverResponseLabelError");
//								serverResponseLabel.setHTML(result);
//								dialogBox.center();
//								closeButton.setFocus(true);
//							}
//						});
//			}
//		}

		// Add a handler to send the name to the server
//		MyHandler handler = new MyHandler();
//		nameButton.addClickHandler(handler);
//		nameField.addKeyUpHandler(handler);
		
		nameButton.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				sendLogin( nameField, namePass);
			}
		});
		nameField.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				sendLogin( nameField, namePass);
			}
		});
		namePass.addKeyUpHandler(new KeyUpHandler(){
			@Override
			public void onKeyUp(KeyUpEvent event) {
				sendLogin( nameField, namePass);
			}
		});

		hideCarregant();
	}
	
	private void sendLogin(TextBox user, PasswordTextBox password){
		showCarregant();
		String userTxt = user.getText();
		String passTxt = password.getText();

		dataService.getUsuari(userTxt, passTxt, new AsyncCallback<Usuari>() {
					public void onFailure(Throwable caught) {
						hideCarregant();
						showErrorConnexio();
					}

					public void onSuccess(Usuari user) {
						hideCarregant();
						if( user != null){
							usuari = user;
							cargaInicial();
						}
						else{
							final DialogBox popup = new DialogBox(true);
							final Label text = new Label("Usuario o contraseÃ±a incorrectos");
							popup.setText("Error");
							popup.setWidget(text);
							popup.setAnimationEnabled(true); // autohide = true
							popup.center();
							popup.show();
						}
					}
				});
	}

	
	private void getOrdinador(){
		showCarregant();
		dataService.getArrayOrdinadors(usuari, new AsyncCallback<ArrayList<Ordinador>>() {
					public void onFailure(Throwable caught) {
						hideCarregant();
						showErrorConnexio();
					}

					public void onSuccess(ArrayList<Ordinador> array) {
						hideCarregant();
						arrayOrdinador = array;
						cargaOrdinador();
					}
				});
	}

	private void cargaInicial(){
		// Es carrega el menÃº i la pÃ gina inicial
		cargaMenu();
		cargaGeneral();
	}
	
	private void cargaMenu(){
		RootPanel menu;
		final Label menu1 = new Label("General");
		final Label menu2 = new Label("Ordinador");
		final Label menu3 = new Label("Configuració");
		
		menu = RootPanel.get("menu");
		menu.clear();
		menu.add(menu1);
		menu.add(menu2);
		menu.add(menu3);

		// Estils
		menu1.setStyleName("menu_button");
		menu2.setStyleName("menu_button");
		menu3.setStyleName("menu_button");
		
		// S'afegeix el onclick
		menu1.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				cargaGeneral();
			}
		});
		
		// S'afegeix el onclick
		menu2.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				if( arrayOrdinador == null){
					// Si encara no s'ha inicialitzat es fa la consulta a BBDD
					getOrdinador();
				}
				else{
					cargaOrdinador();
				}
			}
		});

		// S'afegeix el onclick
		menu3.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				cargaConfiguracio();
			}
		});
	}

	private void cargaGeneral(){
		RootPanel content = RootPanel.get("content");
		content.clear();
		content.add(usuari.getPanel());
	}

	private void cargaOrdinador(){
		RootPanel content = RootPanel.get("content");
		content.clear();

		// S'afegeix un botó per recarregar la pàgina
		Image refresh = new Image(imageRefresh);
		refresh.setTitle("Refrescar");
		PushButton button = new PushButton(refresh, new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				getOrdinador();
				
			}
			
		});

		content.add(button);
		
		if( arrayOrdinador.isEmpty() ){
			content.add(new Label("No hi ha ordinadors donats d'alta!"));
		}
		else{
			Iterator<Ordinador> it = arrayOrdinador.iterator();
			while( it.hasNext() ){
				Ordinador ordinador = it.next();
				content.add(ordinador.getPanel());
			}
		}
	}
	
	private void cargaConfiguracio(){
		RootPanel content = RootPanel.get("content");
		content.clear();

		// S'afegeix un botó per recarregar la pàgina
		Image refresh = new Image(imageRefresh);
		refresh.setTitle("Refrescar");
		PushButton button = new PushButton(refresh, new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				getOrdinador();
				
			}
			
		});

		content.add(button);

		content.add(usuari.getConfiguracioPanel());
		/*
		HorizontalPanel hp;
		VerticalPanel vp;
		
		// Dades usuari
		Label usuariLabel = new Label("Dades usuari");
		
		// Nom usuari
		Label nameLabel = new Label("Nom");
		TextBox nameField = new TextBox();
		nameField.setText(usuari.getNom());
		
		hp = new HorizontalPanel();
		hp.add(nameLabel);
		hp.add(nameField);
		
		vp = new VerticalPanel();
		vp.add(usuariLabel);
		vp.add(hp);
		
		
		DecoratorPanel panelUsuari= new DecoratorPanel();
		panelUsuari.add(vp);

		content.add(panelUsuari);

		content.add(new Label("Aqui hi haura la configuració"));
		content.add(new Label("Aqui hi haura la configuració"));
		content.add(new Label("Aqui hi haura la configuració"));
		*/
	}

	private void showError(String caption, String text){
		final DialogBox popup = new DialogBox(true);
		final Label label = new Label(caption);
		popup.setText(text);
		popup.setWidget(label);
		popup.setAnimationEnabled(true); // autohide = true
		popup.center();
		popup.show();
	}

	private void showErrorConnexio(){
		showError("Error", "Error al connectar amb el servidor");
	}

	private void showCarregant(){
		RootPanel loading = RootPanel.get("loading");
		loading.setVisible(true);
	}

	private void hideCarregant(){
		RootPanel loading = RootPanel.get("loading");
		loading.setVisible(false);

	}
}
