package model;

import java.util.UUID;

enum Marka {
	TESLA, BMW, VOLVO, FIAT, FORD;
}

enum Model {
	P100D, M100, A20, I3, MODEL_S, GOLF;
}

public class Automobil {
	private String oznaka;
	private Musterija vlasnik;
	private Marka marka;
	private Model model;
	private short godinaProizvodnje;
	private short zapreminaMotora;
	private short snagaMotora;
	private String gorivo;
	private boolean obrisan;
	private ServisnaKnjizica servisnaKnjizica;
	
	public Automobil(Musterija vlasnik, Marka marka, Model model, short godinaProizvodnje,
			short zapreminaMotora, short snagaMotora, String gorivo) {
		super();
		this.oznaka = generisiOznaku();
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.obrisan = false;
	}
	
	public String getOznaka() {
		return oznaka;
	}

	public String generisiOznaku() {
		return UUID.randomUUID().toString();
	}

	public Musterija getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Musterija vlasnik) {
		this.vlasnik = vlasnik;
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

	public short getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(short godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public short getZapreminaMotora() {
		return zapreminaMotora;
	}

	public void setZapreminaMotora(short zapreminaMotora) {
		this.zapreminaMotora = zapreminaMotora;
	}

	public short getSnagaMotora() {
		return snagaMotora;
	}

	public void setSnagaMotora(short snagaMotora) {
		this.snagaMotora = snagaMotora;
	}

	public String getGorivo() {
		return gorivo;
	}

	public void setGorivo(String gorivo) {
		this.gorivo = gorivo;
	}

	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	
	
	
	
}
