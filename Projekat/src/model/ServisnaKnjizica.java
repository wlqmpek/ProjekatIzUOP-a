package model;

import java.util.ArrayList;

public class ServisnaKnjizica {
	
	private String oznaka;
	private Automobil automobil;
	private ArrayList<Servis> servisi = new ArrayList<Servis>();
	private boolean obrisan;
	
	public ServisnaKnjizica(Automobil automobil) {
		super();
		generisiOznaku();
		this.automobil = automobil;
		obrisan = false;
	}
	
	public ServisnaKnjizica(Automobil automobil, ArrayList<Servis> servisi) {
		super();
		generisiOznaku();
		this.automobil = automobil;
		this.servisi = servisi;
		obrisan = false;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void generisiOznaku() {
		this.oznaka = oznaka;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	public ArrayList<Servis> getServisi() {
		return servisi;
	}

	public void setServisi(ArrayList<Servis> servisi) {
		this.servisi = servisi;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
}
