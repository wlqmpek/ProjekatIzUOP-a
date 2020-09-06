package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import controller.AutomobilController;
import controller.ServiserController;
import controller.ServisnaKnjizicaController;
import enumi.Status;


public class Servis {
	
	private String oznaka;
	private ServisnaKnjizica servisnaKnjizica;
	private Serviser serviser;
	private Date datum;
	private String opis;
	private Status status;
	private boolean obrisan;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	//koristi se da odmah kreiramo servis
	public Servis(ServisnaKnjizica servisnaKnjizica, Serviser serviser, Date datum, String opis, Status status) {
		super();
		this.oznaka = generisiOznaku();
		this.servisnaKnjizica = servisnaKnjizica;
		this.serviser = serviser;
		this.datum = datum;
		this.opis = opis;
		this.status = status;
		this.obrisan = false;
	}
	
	//kreiraj servis iz fajla
	public Servis(String oznaka, String servisnaKnjizica, String serviser, String datum, String opis, Status status, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.servisnaKnjizica = ServisnaKnjizicaController.nadjiServisnuKnjizicuPoOznaci(servisnaKnjizica);
		this.serviser = ServiserController.nadjiServiseraPoOznaci(serviser);
		this.setDatum(datum);
		this.opis = opis;
		this.status = status;
		this.obrisan = obrisan;
	}



	public String getOznaka() {
		return oznaka;
	}

	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}
	
	

	public ServisnaKnjizica getServisnaKnjizica() {
		return servisnaKnjizica;
	}

	public void setServisnaKnjizica(ServisnaKnjizica servisnaKnjizica) {
		this.servisnaKnjizica = servisnaKnjizica;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public String getDatum() {
		return sdf.format(datum);
	}

	public void setDatum(String datum) {
		try {
			this.datum = sdf.parse(datum);
		} catch (Exception e) {
			System.out.println("Datum nije validan!");
		}
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}


	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Servis [oznaka=" + oznaka + ", servisnaKnjizica=" + servisnaKnjizica + ", serviser=" + serviser
				+ ", datum=" + datum + ", opis=" + opis + ", status=" + status + ", obrisan=" + obrisan + "]";
	}

	
		
	
	
}
