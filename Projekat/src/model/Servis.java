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
	private Double troskoviUsluge;
	private Status status;
	private boolean obrisan;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	
	//koristi se da odmah kreiramo servis
	public Servis(ServisnaKnjizica servisnaKnjizica, Serviser serviser, Date datum, String opis, Double troskoviUsluge, Status status) {
		super();
		this.oznaka = generisiOznaku();
		this.servisnaKnjizica = servisnaKnjizica;
		this.serviser = serviser;
		this.datum = datum;
		setOpis(opis);
		setTroskoviUsluge(troskoviUsluge);
		this.status = status;
		this.obrisan = false;
	}
	
	//kad musterija kreira servis
	public Servis(ServisnaKnjizica servisnaKnjizica, String opis) {
		super();
		this.oznaka = generisiOznaku();
		this.servisnaKnjizica = servisnaKnjizica;
		this.opis = opis;
		this.obrisan = false;
		this.status = Status.ZAKAZAN;
	}
	
	//kad ucitavamo musterijin serviss?
	public Servis(String oznaka, ServisnaKnjizica servisnaKnjizica, String opis) {
		super();
		this.oznaka = oznaka;
		this.servisnaKnjizica = servisnaKnjizica;
		this.opis = opis;
		this.obrisan = false;
	}
	
	//kreiraj servis iz fajla
	public Servis(String oznaka, String servisnaKnjizica, String serviser, String datum, String opis, Double troskoviUsluge, Status status, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.servisnaKnjizica = ServisnaKnjizicaController.nadjiServisnuKnjizicuPoOznaci(servisnaKnjizica);
		this.serviser = ServiserController.nadjiServiseraPoOznaci(serviser);
		this.setDatum(datum);
		this.opis = opis;
		setTroskoviUsluge(troskoviUsluge);
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
		String dat = null;
		if (datum == null) {
			dat = null;
		} else if(datum.equals("null")) {
			dat = null;
		} else {
			dat = sdf.format(datum);
		}
		
		return dat;
	}

	public void setDatum(String datum) {
		System.out.println("DATUM: " +datum);
		if(datum == null) {
			this.datum = null;
		} else if (datum.equalsIgnoreCase("null")) {
			this.datum = null;
		} else {
			try {
				this.datum = sdf.parse(datum);
			} catch (Exception e) {
				System.out.println("Datum nije validan!");
			}
		}
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		if(opis == null) {
			throw new NullPointerException("Opis ne sme biti null!");
		} else if(opis.length() == 0) {
			throw new IllegalArgumentException("Opis ne sme biti prazan string!");
		} else {
			this.opis = opis;
		}
	}


	
	
	public Double getTroskoviUsluge() {
		return troskoviUsluge;
	}

	public void setTroskoviUsluge(Double troskoviUsluge) {
		if(troskoviUsluge == null) {
			this.troskoviUsluge = null;
		} else if(troskoviUsluge < 0) {
			throw new IllegalArgumentException("Troskovi usluge ne sme biti manja od 0");
		} else {
			this.troskoviUsluge = troskoviUsluge;
		}
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
