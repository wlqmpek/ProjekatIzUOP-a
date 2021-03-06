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
import model.Serviser;
import model.ServisnaKnjizica;

public class AutomobilController {
	
	private static File file = new File(".\\podaci\\automobili.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Automobil> automobili = new ArrayList<Automobil>();
	
	
	static {
		try {
			inicijalizujAutomobile();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void upisiAutomobilUFajl(Automobil automobil) {
		AutomobilController.automobili.add(automobil);
		String automobilKaoString = String.join("|", automobilUStringArray(automobil)) + "\r\n";
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
	
	public static void sacuvajIzmeneUFajl() {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw);
			for(Automobil automobil : AutomobilController.automobili) {
				pw.append(String.join("|", automobilUStringArray(automobil)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//cita fajl i upisuje podatke u niz nizova atributa 
	public static void procitajFajl() {
		AutomobilController.podaci.clear();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				String linija = sc.nextLine();
				AutomobilController.podaci.add(new ArrayList<String>(Arrays.asList((linija.split("\\|")))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Automobil stringUAutomobil(ArrayList<String> podaci) throws NumberFormatException, Exception {
		return new Automobil(podaci.get(0), podaci.get(1), Marka.valueOf(podaci.get(2)), Model.valueOf(podaci.get(3)), Short.valueOf(podaci.get(4)), Short.valueOf(podaci.get(5)), Short.valueOf(podaci.get(6)), Gorivo.valueOf(podaci.get(7)), Boolean.valueOf(podaci.get(8)));
	}
	

	public static ArrayList<String> automobilUStringArray(Automobil automobil) {
		
		String niz[] = {automobil.getOznaka(), automobil.getVlasnik().getOznaka(), automobil.getMarka().toString(), automobil.getModel().toString(), String.valueOf(automobil.getGodinaProizvodnje()), String.valueOf(automobil.getZapreminaMotora()), String.valueOf(automobil.getSnagaMotora()), automobil.getGorivo().toString(), String.valueOf(automobil.isObrisan())};
		ArrayList<String> array = new ArrayList<String>(Arrays.asList(niz));
		return array;
	}
	
	//konvertuje iz niza stringova u niz automobila
	public static void konvertujSveAutomobile() throws NumberFormatException, Exception {
		AutomobilController.automobili.clear();
		for (ArrayList<String> auto : AutomobilController.podaci) {
			AutomobilController.automobili.add(stringUAutomobil(auto));
		}
	}
	
	//pribavlja vlasnika/musteriju pomocu oznake
	public static Musterija nadjiVlasnika(String oznaka) {
		return MusterijaController.nadjiMusterijuPoOznaci(oznaka);
	}
	
	public static void inicijalizujAutomobile() throws NumberFormatException, Exception {
		procitajFajl();
		konvertujSveAutomobile();
	}
	
	public static ArrayList<Automobil> nadjiAutomobilePoIdVlasnika(String oznaka) {
		ArrayList<Automobil> sviAutomobiliVlasnika = new ArrayList<Automobil>();
		for(Automobil auto : AutomobilController.automobili) {
			if(auto.getVlasnik().getOznaka().equalsIgnoreCase(oznaka)) {
				sviAutomobiliVlasnika.add(auto);
			}
		}	
		
		return sviAutomobiliVlasnika;
	}
	
	public static void ispisiSveAutomobile() {
		for(Automobil auto : AutomobilController.automobili) {
			System.out.println(auto);
		}
	}
	
	public static void ispisiVlasnikaAutomobila(String oznaka) {
		for(Automobil auto:AutomobilController.automobili) {
			if(auto.getOznaka().equals(oznaka)) {
				System.out.println(auto.getVlasnik());
			}
		}
	}
	
	//TODO:!!! Ovo bi trebalo da obrise servisnu knjizicu?
	public static void izbrisiAutomobil(Automobil automobil) {
		if(automobil == null) {
			System.out.println("Molim vas izaberite validan automobil");
		} else {
			automobil.setObrisan(true);
			ServisnaKnjizicaController.nadjiServisnuKnjizicuPoOznaci(automobil.getOznaka()).setObrisan(true);
			ServisnaKnjizicaController.sacuvajIzmeneUFajl();
			AutomobilController.sacuvajIzmeneUFajl();
		}
	}
	
	public static Automobil nadjiAutomobilPoOznaci(String oznaka) {
		Automobil trazenAutomobil = null;
		for(Automobil automobil : AutomobilController.automobili) {
			if(oznaka.equalsIgnoreCase(automobil.getOznaka())) {
				trazenAutomobil = automobil;
			}
		}
		return trazenAutomobil;
	}
	
	public static ServisnaKnjizica servisnaKnjizicaAutomobila(Automobil automobil) {
		return ServisnaKnjizicaController.servisnaKnjizicaAutomobila(automobil);
	}
	
	public static ArrayList<Automobil> getNeObrisaniAutomobili() throws NumberFormatException, Exception {
		ArrayList<Automobil> neObrisaniAutomobili = new ArrayList<Automobil>();
		inicijalizujAutomobile();
		for(Automobil auto : AutomobilController.automobili) {
			if(auto.isObrisan() == false) {
				neObrisaniAutomobili.add(auto);
			}
		}
		return neObrisaniAutomobili;
	}
	
	public static void izbrisiIzUcitanihAutomobilaSaOznakom(String oznaka) {
		AutomobilController.automobili.remove(nadjiAutomobilPoOznaci(oznaka));
	}

	public static ArrayList<Automobil> getAutomobili() throws NumberFormatException, Exception {
		inicijalizujAutomobile();
		return AutomobilController.automobili;
	}
	
	public static void setAutomobili(ArrayList<Automobil> automobili) {
		AutomobilController.automobili = automobili;
	}
}
