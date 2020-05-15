package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
import enumi.Pol;
import model.Automobil;
import model.Musterija;
import model.ServisnaKnjizica;
import view.AutomobilView;

public class AutomobilController {
	
	private Automobil model;
	private AutomobilView view;
	private static File file = new File(".\\podaci\\automobili.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Automobil> automobili = new ArrayList<Automobil>();
	
	public AutomobilController(Automobil model, AutomobilView view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	//privremen
	public AutomobilController() {
		super();
	}	
	
	public static void upisiAutomobilUFajl(Automobil automobil) {
		String automobilKaoString = String.join("|", automobilUStringArray(automobil)) + "\n";
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(automobilKaoString);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//cita fajl i upisuje podatke u niz nizova atributa 
	public static void procitajFajl() {
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				podaci.add(new ArrayList<String>(Arrays.asList((sc.nextLine()).split("\\|"))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Automobil stringUAutomobil(ArrayList<String> podaci) {
		return new Automobil(podaci.get(0), podaci.get(1), Marka.valueOf(podaci.get(2)), Model.valueOf(podaci.get(3)), Short.valueOf(podaci.get(4)), Short.valueOf(podaci.get(5)), Short.valueOf(podaci.get(6)), Gorivo.valueOf(podaci.get(7)), Boolean.valueOf(podaci.get(8)));
	}
	

	public static ArrayList<String> automobilUStringArray(Automobil automobil) {
		return new ArrayList<String>(Arrays.asList(automobil.getOznaka(), automobil.getVlasnik().getOznaka(), automobil.getMarka().toString(), automobil.getModel().toString(), String.valueOf(automobil.getGodinaProizvodnje()), String.valueOf(automobil.getZapreminaMotora()), String.valueOf(automobil.getSnagaMotora()), automobil.getGorivo().toString(), String.valueOf(automobil.isObrisan())));
	}
	
	//konvertuje iz niza stringova u niz automobila
	public static void konvertujSveAutomobile() {
		for (ArrayList<String> auto : podaci) {
			automobili.add(stringUAutomobil(auto));
		}
	}
	
	//pribavlja vlasnika/musteriju pomocu oznake
	public static Musterija nadjiVlasnika(String oznaka) {
		return MusterijaController.nadjiMusteriju(oznaka);
	}
	
	public static void inicijalizujAutomobile() {
		procitajFajl();
		konvertujSveAutomobile();
	}
	
	public static ArrayList<Automobil> nadjiAutomobilePoIdVlasnika(String oznaka) {
		ArrayList<Automobil> sviAutomobiliVlasnika = new ArrayList<Automobil>();
		for(Automobil auto : automobili) {
			if(auto.getVlasnik().getOznaka().equals(oznaka)) {
				sviAutomobiliVlasnika.add(auto);
			}
		}	
		if (sviAutomobiliVlasnika.size() == 0) {
			sviAutomobiliVlasnika = null;
		}
		return sviAutomobiliVlasnika;
	}
	
	public static void ispisiSveAutomobile() {
		for(Automobil auto : automobili) {
			System.out.println(auto);
		}
	}
	
	public static void ispisiVlasnikaAutomobila(String oznaka) {
		for(Automobil auto:automobili) {
			if(auto.getOznaka().equals(oznaka)) {
				System.out.println(auto.getVlasnik());
			}
		}
	}
	
}
