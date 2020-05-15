package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

enum Status {
	
}

public class Servis {
	
	private String oznaka;
	private Automobil automobil;
	private Serviser serviser;
	private Date datum;
	private String opis;
	private ArrayList<Deo> delovi = new ArrayList<Deo>();
	private Status status;
	private boolean obrisan;
	
	
	public Servis(Automobil automobil, Serviser serviser, Date datum, String opis, Status status) {
		
		super();
		this.oznaka = generisiOznaku();
		this.automobil = automobil;
		this.serviser = serviser;
		this.datum = datum;
		this.opis = opis;
		this.status = status;
		this.obrisan = false;
	}

	public String getOznaka() {
		return oznaka;
	}

	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}
	
	public void dodajDeo(Deo deo) {
		this.delovi.add(deo);
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public Serviser getServiser() {
		return serviser;
	}

	public void setServiser(Serviser serviser) {
		this.serviser = serviser;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<Deo> getDelovi() {
		return delovi;
	}

	public void setDelovi(ArrayList<Deo> delovi) {
		this.delovi = delovi;
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
		return "Servis [oznaka=" + oznaka + ", automobil=" + automobil + ", serviser=" + serviser + ", datum=" + datum
				+ ", opis=" + opis + ", delovi=" + delovi + ", status=" + status + ", obrisan=" + obrisan + "]";
	}
	
	
	
	
}
