package view;

import java.util.ArrayList;

import model.Automobil;

public class MusterijaView {
	
	public void prikaziSveAutomobile(ArrayList<Automobil> automobili) {
		for (Automobil automobil : automobili) {
			System.out.println(automobil);
		}
	}
	
	
	
}
