package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import enumi.Pol;
import model.Administrator;
import model.Automobil;
import model.Musterija;
import model.Servis;
import model.Serviser;
import view.MusterijaView;

public class MusterijaController {
	
	private static File file = new File(".\\podaci\\musterije.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Musterija> musterije = new ArrayList<Musterija>();
	
	static {
		inicijalizujMusterije();
	}
	
	public static void upisiMusterijuUFajl(Musterija musterija) {
		musterije.add(musterija);
		String musterijaKaoString = String.join("|", musterijaUStringArray(musterija)) + "\r\n";	
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(musterijaKaoString);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//zapisuje sve iznova iz musterija u fajl
	public static void sacuvajIzmeneUFajl() {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw);
			for(Musterija musterija : MusterijaController.musterije) {
				pw.append(String.join("|", musterijaUStringArray(musterija)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//cita fajl i upisuje podatke u niz nizova atributa 
	public static void procitajFajl() {
		MusterijaController.podaci.clear();
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
	
	
	public static Musterija stringUMusteriju(ArrayList<String> podaci) {
		return new Musterija(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), Pol.valueOf(podaci.get(4)), podaci.get(5), podaci.get(6), podaci.get(7), podaci.get(8), Byte.valueOf(podaci.get(9)), Boolean.valueOf(podaci.get(10)));
	}
	
	public static ArrayList<String> musterijaUStringArray(Musterija musterija) {
		return new ArrayList<String>(Arrays.asList(musterija.getOznaka(), musterija.getIme(), musterija.getPrezime(), musterija.getJMBG(), musterija.getPol().toString(), musterija.getAdresa(), musterija.getBrojTelefona(), musterija.getKorisnickoIme(), musterija.getLozinka(), String.valueOf(musterija.getBrojPoena()), String.valueOf(musterija.isObrisan())));
	}
	

	
	public static ArrayList<Automobil> nadjiAutomobilePoIdVlasnika(String oznaka) {
		return AutomobilController.nadjiAutomobilePoIdVlasnika(oznaka);
	}
	
	public static void inicijalizujMusterije() {
		MusterijaController.musterije.clear();
		MusterijaController.podaci.clear();
		procitajFajl();
		konvertujSveMusterije();
	}
	
	//konvertuje iz niza stringova u niz musterija
	public static void konvertujSveMusterije() {
		MusterijaController.musterije.clear();
		for (ArrayList<String> mus : MusterijaController.podaci) {
			musterije.add(stringUMusteriju(mus));
		}
	}
	
	//Vraca listu SVIH automobila korisnika
	public static ArrayList<Automobil> listaAutomobilaMusterije(Musterija musterija) {
		ArrayList<Automobil> listaTrazenihAutomobila = new ArrayList<Automobil>();
		
		for(Automobil automobil : AutomobilController.automobili) {
			if(automobil.getVlasnik().getOznaka().equalsIgnoreCase(musterija.getOznaka())) {
				listaTrazenihAutomobila.add(automobil);
			}
		}
		return listaTrazenihAutomobila;
	}
	
	//ovo bi trebalo da obrise i automobile
	public static void izbrisiMusteriju(Musterija musterija) {
		if(musterija == null) {
			System.out.println("Molim vas izaberite validan servis");
		} else {
			musterija.setObrisan(true);
			izbrisiAutomobileMusterije(musterija);
			//treba dodati da se ove izmene sacuvaju u fajlu servisa
			sacuvajIzmeneUFajl();
		}
	}
	
	private static void izbrisiAutomobileMusterije(Musterija musterija) {
		for(Automobil automobil : listaAutomobilaMusterije(musterija)) {
			AutomobilController.izbrisiAutomobil(automobil);
		}
	}


	public static Musterija nadjiMusterijuPoOznaci(String oznaka) {
		Musterija trazenaMusterija = null;
		for(Musterija musterija : MusterijaController.musterije) {
			if(oznaka.equalsIgnoreCase(musterija.getOznaka())) {
				trazenaMusterija = musterija;
			}
		}
		return trazenaMusterija;
	}
	
	public static Musterija nadjiMusterijuPoKorisnickomImenu(String korisnickoIme) {
		Musterija trazenaMusterija = null;
		for(Musterija musterija : MusterijaController.musterije) {
			if(korisnickoIme.equalsIgnoreCase(musterija.getKorisnickoIme())) {
				trazenaMusterija = musterija;
			}
		}
		return trazenaMusterija;
	}

	
	//metode za demonstraciju funkcionalnosti
	public static void ispisiSveMusterije() {
		for(Musterija musterija : MusterijaController.musterije) {
			System.out.println(musterija);
		}
	}
	
	
	public static void ispisiSveAutomobileMusterije(String oznaka) {
		for(Automobil auto : MusterijaController.nadjiAutomobilePoIdVlasnika(oznaka)) {
			System.out.println(auto);
		}
	}

	
	public static ArrayList<Musterija> getNeObrisaniMusterije() {
		ArrayList<Musterija> neObrisaneMusterije = new ArrayList<Musterija>();
		inicijalizujMusterije();
		for(Musterija mus : musterije) {
			if(mus.isObrisan() == false) {
				neObrisaneMusterije.add(mus);
			}
		}
		return neObrisaneMusterije;
	}
	
	
	public static void izbrisiIzUcitanihMusterijaSaOznakom(String oznaka) {
		MusterijaController.musterije.remove(nadjiMusterijuPoOznaci(oznaka));
	}

	public static ArrayList<Musterija> getMusterije() {
		inicijalizujMusterije();
		return MusterijaController.musterije;
	}


	public static void setMusterije(ArrayList<Musterija> musterije) {
		MusterijaController.musterije = musterije;
	}
	
	
}
