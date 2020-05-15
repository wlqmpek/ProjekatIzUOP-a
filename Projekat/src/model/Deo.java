package model;

import java.util.UUID;

public class Deo {
	
	private String oznaka;
	private Marka marka;
	private Model model;
	private String naziv;
	private double cena;
	private boolean obrisan;
	
	
	public Deo(Marka marka, Model model, String naziv, double cena) {
		super();
		this.oznaka = generisiOznaku();
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		this.cena = cena;
		this.obrisan = false;
	}


	public String getOznaka() {
		return oznaka;
	}


	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}


	public Marka getMarka() {
		return marka;
	}


	public void setMarka(Marka marka) {
		this.marka = marka;
	}


	public Model getModel() {
		return model;
	}


	public void setModel(Model model) {
		this.model = model;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public double getCena() {
		return cena;
	}


	public void setCena(double cena) {
		this.cena = cena;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	
	
	
}
