package model;

import java.util.UUID;

import controller.DeoController;
import enumi.Marka;
import enumi.Model;

public class Deo {
	
	private String oznaka;
	private Marka marka;
	private Model model;
	private String naziv;
	private double cena;
	private Servis iskoriscenUSevisu = null;
	private boolean obrisan;
	
	//ovaj konstruktor se koristi za kreiranje novog objekta deo iz aplikacije
	public Deo(Marka marka, Model model, String naziv, double cena, Servis servis) {
		super();
		this.oznaka = generisiOznaku();
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		setCena(cena);
		this.iskoriscenUSevisu = servis;
		this.obrisan = false;
	}
	
	//ovaj konstruktor se koristi za kreiranje novog objekta deo iz fajla
	public Deo(String oznaka, Marka marka, Model model, String naziv, double cena, String iskoriscenUServisu, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.marka = marka;
		this.model = model;
		this.naziv = naziv;
		setCena(cena);
		this.iskoriscenUSevisu = DeoController.nadjiServis(iskoriscenUServisu);
		this.obrisan = obrisan;
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
		if (cena <= 0) {
			throw new IllegalArgumentException("Uneta c mora biti veca od 0");
		} else {
			this.cena = cena;
		}
	}

	
	public Servis getIskoriscenUSevisu() {
		return iskoriscenUSevisu;
	}

	public void setIskoriscenUSevisu(Servis iskoriscenUSevisu) {
		this.iskoriscenUSevisu = iskoriscenUSevisu;
	}


	public boolean isObrisan() {
		return obrisan;
	}


	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Deo [oznaka=" + oznaka + ", marka=" + marka + ", model=" + model + ", naziv=" + naziv + ", cena=" + cena
				+ ", iskoriscenUSevisu=" + iskoriscenUSevisu + ", obrisan=" + obrisan + "]";
	}
	
	
	
	
}
