package wooxes.net.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

public class Red  implements IsSerializable{
	private int id_ordinador;
	private String ip_interna;
	private String ip_externa;
	private String fecha;
	
	public Red(){
		
	}

	public int getIdOrdinador() {
		return id_ordinador;
	}
	public void setIdOrdinador(int id_ordinador) {
		this.id_ordinador = id_ordinador;
	}

	public String getIpInterna() {
		return ip_interna;
	}
	public void setIpInterna(String ip) {
		this.ip_interna = ip;
	}
	public String getIpExterna() {
		return ip_externa;
	}
	public void setIpExterna(String ip) {
		this.ip_externa = ip;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Panel getPanel(){
		FlowPanel root = new FlowPanel();
		root.add(new HTML("<b>Actualització:</b> "+getFecha()));
		root.add(new HTML("<b>IP interna:</b> "+getIpInterna()));
		root.add(new HTML("<b>IP externa:</b> "+getIpExterna()));
		
		return root;
	}
}
