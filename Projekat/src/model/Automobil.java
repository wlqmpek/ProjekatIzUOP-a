package model;

import java.util.UUID;

import controller.AutomobilController;
import controller.ServisnaKnjizicaController;
import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;


public class Automobil {
	private String oznaka;
	private Musterija vlasnik;
	private Marka marka;
	private Model model;
	private short godinaProizvodnje;
	private short zapreminaMotora;
	private short snagaMotora;
	private Gorivo gorivo;
	private boolean obrisan;
	
	public Automobil(Musterija vlasnik, Marka marka, Model model, short godinaProizvodnje,
			short zapreminaMotora, short snagaMotora, Gorivo gorivo) {
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
	
	//koristi se da odmah dodamo vlasnika
	public Automobil(String oznaka, Musterija vlasnik, Marka marka, Model model, short godinaProizvodnje,
			short zapreminaMotora, short snagaMotora, Gorivo gorivo, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.vlasnik = vlasnik;
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.obrisan = obrisan;
	}
	
	//koristi se kod iscitavanja iz fajla Note: promeni za oznaku
	public Automobil(String oznaka, String vlasnik, Marka marka, Model model, short godinaProizvodnje,
			short zapreminaMotora, short snagaMotora, Gorivo gorivo, boolean obrisan) {
		super();
		this.oznaka = oznaka;
		this.vlasnik = AutomobilController.nadjiVlasnika(vlasnik);
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.obrisan = obrisan;
	}
	
	
	

	public Automobil(String vlasnik, Marka marka, Model model, short godinaProizvodnje,
			short zapreminaMotora, short snagaMotora, Gorivo gorivo, boolean obrisan) {
		super();
		this.oznaka = generisiOznaku();
		this.vlasnik = AutomobilController.nadjiVlasnika(vlasnik);
		this.marka = marka;
		this.model = model;
		this.godinaProizvodnje = godinaProizvodnje;
		this.zapreminaMotora = zapreminaMotora;
		this.snagaMotora = snagaMotora;
		this.gorivo = gorivo;
		this.obrisan = obrisan;
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

	public Gorivo getGorivo() {
		return gorivo;
	}

	public void setGorivo(Gorivo gorivo) {
		this.gorivo = gorivo;
	}
	


	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Automobil [vlasnik=" + vlasnik + ", marka=" + marka + ", model=" + model + ", godinaProizvodnje="
				+ godinaProizvodnje + "]";
	}

	
	
	
	
}
