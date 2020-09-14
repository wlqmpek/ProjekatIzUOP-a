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
import enumi.Specijalizacija;
import model.Administrator;
import model.Automobil;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;

public class ServiserController {
	private static File file = new File(".\\podaci\\serviseri.txt");
	private static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	private static ArrayList<Serviser> serviseri = new ArrayList<Serviser>();
	
	static {
		inicijalizujServisere();
	}
	
	public static void upisiServiseraUFajl(Serviser serviser) {
		ServiserController.serviseri.add(serviser);
		String serviserKaoString = String.join("|", serviserUStringArray(serviser)) + "\r\n";
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(serviserKaoString);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//zapisuje sve iznova iz servisera u fajl
	public static void sacuvajIzmeneUFajl() {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw);
			for(Serviser serviser : ServiserController.serviseri) {
				pw.append(String.join("|", serviserUStringArray(serviser)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void procitajFajl() {
		ServiserController.podaci.clear();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				ServiserController.podaci.add(new ArrayList<String>(Arrays.asList((sc.nextLine()).split("\\|"))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Serviser stringUServisera(ArrayList<String> podaci) {
		return new Serviser(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), Pol.valueOf(podaci.get(4)), podaci.get(5), podaci.get(6), podaci.get(7), podaci.get(8), Boolean.valueOf(podaci.get(9)), Specijalizacija.valueOf(podaci.get(10)), Double.valueOf(podaci.get(11)));
	}
	
	public static ArrayList<String> serviserUStringArray(Serviser serviser) {
		return new ArrayList<String>(Arrays.asList(serviser.getOznaka(), serviser.getIme(), serviser.getPrezime(), serviser.getJMBG(), serviser.getPol().toString(), serviser.getAdresa(), serviser.getBrojTelefona(), serviser.getKorisnickoIme(), serviser.getLozinka(), String.valueOf(serviser.isObrisan()), serviser.getSpecijalizacija().toString(), String.valueOf(serviser.getPlata())));
	}
	
	//konvertuje iz niza stringova u niz servisera
	public static void konvertujSveAutomobile() {
		ServiserController.serviseri.clear();
		for (ArrayList<String> serv : ServiserController.podaci) {
			ServiserController.serviseri.add(stringUServisera(serv));
		}
	}
	
	//Vraca listu SVIH servisa u kojima je servser ucestvovao
	public static ArrayList<Servis> listaServisaServisera(Serviser serviser) {
		ArrayList<Servis> listaTrazenihServisa = new ArrayList<Servis>();
		
		for(Servis servis:ServisController.servisi) {
			if(servis.getServiser().getOznaka().equalsIgnoreCase(serviser.getOznaka())) {
				listaTrazenihServisa.add(servis);
			}
		}
		return listaTrazenihServisa;
	}
	
	
	public static void inicijalizujServisere() {
		procitajFajl();
		konvertujSveServisere();
	}
	
	
	//konvertuje iz niza stringova u niz servisera
	public static void konvertujSveServisere() {
		ServiserController.serviseri.clear();
		for (ArrayList<String> ser : ServiserController.podaci) {
			ServiserController.serviseri.add(stringUServisera(ser));
		}
		
	}
	
	//ovo bi trebalo da obrise i servise
	public static void izbrisiServisera(Serviser serviser) {
		if(serviser == null) {
			System.out.println("Molim vas izaberite validan servis");
		} else {
			serviser.setObrisan(true);
			izbrisiServiseServisera(serviser);
			//treba dodati da se ove izmene sacuvaju u fajlu servisa
			sacuvajIzmeneUFajl();
		}
	}
	
	//brise sve servise u kojima je serviser ucestvovao
	public static void izbrisiServiseServisera(Serviser serviser) {
		for(Servis servis : listaServisaServisera(serviser)) {
			ServisController.izbrisiServis(servis);
		}
	}
	
	public static Serviser nadjiServiseraPoOznaci(String oznaka) {
		Serviser trazenServiser = null;
		if(oznaka == null) {
			trazenServiser = null;
		} else if (oznaka.equalsIgnoreCase("null")) {
			trazenServiser = null;
		} else {
			for(Serviser serviser : ServiserController.serviseri) {
				if(oznaka.equalsIgnoreCase(serviser.getOznaka())) {
					trazenServiser = serviser;
				}
			}
		}
		
		return trazenServiser;
	}
	
	public static Serviser nadjiServiseraPoKorisnickomImenu(String korisnickoIme) {
		Serviser trazenServiser = null;
		for(Serviser serviser : ServiserController.serviseri) {
			if(korisnickoIme.equalsIgnoreCase(serviser.getKorisnickoIme())) {
				trazenServiser = serviser;
			}
		}
		return trazenServiser;
	}
	
	public static void ispisiSveServisere() {
		for(Serviser ser : ServiserController.serviseri) {
			System.out.println(ser);
		}
	}

	
	public static ArrayList<Serviser> getNeObrisaniServiseri() {
		ArrayList<Serviser> neObrisaniServiseri = new ArrayList<Serviser>();
		inicijalizujServisere();
		System.out.println("Pre neobrisanih ser " +ServiserController.serviseri);
		for(Serviser ser : ServiserController.serviseri) {
			if(ser.isObrisan() == false) {
				neObrisaniServiseri.add(ser);
			}
		}
		return neObrisaniServiseri;
	}
	
	public static void izbrisiIzUcitanihServiseraSaOznakom(String oznaka) {
		ServiserController.serviseri.remove(nadjiServiseraPoOznaci(oznaka));
	}

	public static ArrayList<Serviser> getServiseri() {
		inicijalizujServisere();
		return ServiserController.serviseri;
	}
	
	public static void setServiseri(ArrayList<Serviser> serviseri) {
		ServiserController.serviseri = serviseri;
	}
	
	
	
}
