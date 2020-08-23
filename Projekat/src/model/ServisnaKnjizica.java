package model;

import controller.ServisnaKnjizicaController;

public class ServisnaKnjizica {
	
	private String oznaka;
	private Automobil automobil;
	private boolean obrisan;
	
	
	public ServisnaKnjizica(Automobil automobil) {
		super();
		this.automobil = automobil;
		this.oznaka = this.getAutomobil().getOznaka(); //posto su servisna knjizica i automobil u relaciji 1-1 ima mi smila da dele zajednicku oznaku
		this.obrisan = this.getAutomobil().isObrisan();
	}
	
	//koristi se za kreiranje objekta iz fajla
	public ServisnaKnjizica(String automobil) {
		super();
		this.automobil = ServisnaKnjizicaController.nadjiAutomobilPoOznaci(automobil);
		this.oznaka = this.getAutomobil().getOznaka(); //posto su servisna knjizica i automobil u relaciji 1-1 ima mi smila da dele zajednicku oznaku
		this.obrisan = this.getAutomobil().isObrisan();
	}
	

	public String getOznaka() {
		return oznaka;
	}

	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
		this.oznaka = automobil.getOznaka();
	}


	public boolean isObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "ServisnaKnjizica [oznaka=" + oznaka + ", automobil=" + automobil + ", obrisan=" + obrisan + "]";
	}

	
	
}
