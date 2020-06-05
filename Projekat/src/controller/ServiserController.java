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
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;

public class ServiserController {
	
	private static File file = new File(".\\podaci\\serviseri.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Serviser> serviseri = new ArrayList<Serviser>();
	
	static {
		inicijalizujServisere();
	}
	
	public static void upisiServiseraUFajl(Serviser serviser) {
		serviseri.add(serviser);
		String serviserKaoString = String.join("|", serviserUStringArray(serviser));
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
	
	public static Serviser stringUServisera(ArrayList<String> podaci) {
		return new Serviser(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), Pol.valueOf(podaci.get(4)), podaci.get(5), podaci.get(6), podaci.get(7), podaci.get(8), Boolean.valueOf(podaci.get(9)), Specijalizacija.valueOf(podaci.get(10)), Double.valueOf(podaci.get(11)));
	}
	
	public static ArrayList<String> serviserUStringArray(Serviser serviser) {
		return new ArrayList<String>(Arrays.asList(serviser.getOznaka(), serviser.getIme(), serviser.getPrezime(), serviser.getJMBG(), serviser.getPol().toString(), serviser.getAdresa(), serviser.getBrojTelefona(), serviser.getKorisnickoIme(), serviser.getLozinka(), String.valueOf(serviser.isObrisan()), serviser.getSpecijalizacija().toString(), String.valueOf(serviser.getPlata())));
	}
	
	//konvertuje iz niza stringova u niz servisera
	public static void konvertujSveAutomobile() {
		serviseri.clear();
		for (ArrayList<String> serv : podaci) {
			serviseri.add(stringUServisera(serv));
		}
	}
	
	public static ArrayList<Servis> listaServisaServisera(Serviser serviser) {
		ArrayList<Servis> listaTrazenihServisa = new ArrayList<Servis>();
		
		for(Servis servis:ServisController.servisi) {
			if(servis.getServiser().getOznaka().equalsIgnoreCase(serviser.getOznaka())) {
				listaTrazenihServisa.add(servis);
			}
		}
		
		System.out.printf("Broj servisa servisera: %s je %d", serviser.getOznaka(), listaTrazenihServisa.size());
		return listaTrazenihServisa;
	}
	
	public static void inicijalizujServisere() {
		System.out.println("Inicijalizacija servisera");
		serviseri.clear();
		podaci.clear();
		procitajFajl();
		konvertujSveServisere();
	}
	
	//konvertuje iz niza stringova u niz servisera
	public static void konvertujSveServisere() {
		serviseri.clear();
		for (ArrayList<String> ser : podaci) {
			serviseri.add(stringUServisera(ser));
		}
	}
	
	public static Serviser nadjiServiseraPoOznaci(String oznaka) {
		Serviser trazenServiser = null;
		for(Serviser serviser : serviseri) {
			if(oznaka.equalsIgnoreCase(serviser.getOznaka())) {
				trazenServiser = serviser;
			}
		}
		return trazenServiser;
	}
	
	public static void ispisiSveServisere() {
		for(Serviser ser:serviseri) {
			System.out.println(ser);
		}
	}
	
	
}
