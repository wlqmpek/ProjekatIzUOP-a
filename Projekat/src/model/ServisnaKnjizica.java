package model;

import java.util.ArrayList;
import java.util.UUID;

public class ServisnaKnjizica {
	
	private String oznaka;
	private Automobil automobil;
	private ArrayList<Servis> servisi = new ArrayList<Servis>();
	private boolean obrisan;
	
	public ServisnaKnjizica(Automobil automobil) {
		super();
		this.oznaka = this.getAutomobil().getOznaka();
		this.automobil = automobil;
		obrisan = false;
	}
	
	public ServisnaKnjizica(Automobil automobil, ArrayList<Servis> servisi) {
		super();
		this.automobil = automobil;
		this.oznaka = this.getAutomobil().getOznaka(); //posto su servisna knjizica i automobil u relaciji 1-1 ima mi smila da dele zajednicku oznaku
		this.servisi = servisi;
		obrisan = false;
	}
	
	public void dodajServis(Servis servis) {
		this.servisi.add(servis);
	}

	public String getOznaka() {
		return oznaka;
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

	@Override
	public String toString() {
		return "ServisnaKnjizica [oznaka=" + oznaka + ", automobil=" + automobil + ", servisi=" + servisi + ", obrisan="
				+ obrisan + "]";
	}
	
	
	
}
