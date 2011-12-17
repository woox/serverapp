package wooxes.net.shared;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;

public class Hardware implements IsSerializable{
	public int id_ordinador;
	public String fecha;
	public String fabricant;
	public String model;
	public String mhz;
	public String total_cpu;
	public String cpu_fisica;
	public String nuclis_cpu;
	
	public Hardware(){
		
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

	public String getFabricant() {
		return fabricant;
	}

	public void setFabricant(String fabricant) {
		this.fabricant = fabricant;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMhz() {
		return mhz;
	}

	public void setMhz(String mhz) {
		this.mhz = mhz;
	}

	public String getTotalCpu() {
		return total_cpu;
	}

	public void setTotalCpu(String total_cpu) {
		this.total_cpu = total_cpu;
	}

	public String getCpuFisica() {
		return cpu_fisica;
	}

	public void setCpuFisica(String cpu_fisica) {
		this.cpu_fisica = cpu_fisica;
	}

	public String getNuclisCpu() {
		return nuclis_cpu;
	}

	public void setNuclisCpu(String nuclis_cpu) {
		this.nuclis_cpu = nuclis_cpu;
	}
	
	public Panel getPanel(){
		FlowPanel root = new FlowPanel();
		root.add(new HTML("<b>Actualització:</b> "+getFecha()));
		root.add(new HTML("<b>Fabricant:</b> "+getFabricant()));
		root.add(new HTML("<b>Model:</b> "+getModel()));
		root.add(new HTML("<b>MHZ:</b> "+getMhz()));
		root.add(new HTML("<b>Total CPUs:</b> "+getTotalCpu()));
		root.add(new HTML("<b>CPUs fisica:</b> "+getCpuFisica()));
		root.add(new HTML("<b>Nuclis per CPU:</b> "+getNuclisCpu()));

		return root;
	}
}
