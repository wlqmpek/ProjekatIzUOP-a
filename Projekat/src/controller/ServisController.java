package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Scanner;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
import enumi.Status;
import javafx.beans.property.SimpleDoubleProperty;
import model.Automobil;
import model.Deo;
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;

public class ServisController {
	private static File file = new File(".\\podaci\\servisi.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Servis> servisi = new ArrayList<Servis>();
	
	static {
		inicijalizujServise();
	}
	
	public static void upisiServisUFajl(Servis servis) {
		servisi.add(servis);
		String servisKaoString = String.join("|", servisUStringArray(servis)) + "\n";
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(servisKaoString);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//zapisuje sve iznova iz servisa u fajl
	public static void sacuvajIzmeneUFajl() {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw);
			for(Servis servis : ServisController.servisi) {
				pw.append(String.join("|", servisUStringArray(servis)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void procitajFajl() {
		ServisController.podaci.clear();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				ServisController.podaci.add(new ArrayList<String>(Arrays.asList((sc.nextLine()).split("\\|"))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//paziiiiiiiii
	public static Servis stringUServis(ArrayList<String> podaci) {
		return new Servis(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), podaci.get(4), Status.valueOf(podaci.get(5)), Boolean.valueOf(podaci.get(6)));
	}
	
	public static ArrayList<String> servisUStringArray(Servis servis) {
		return new ArrayList<String>(Arrays.asList(servis.getOznaka(), servis.getServisnaKnjizica().getOznaka(), servis.getServiser().getOznaka(), servis.getDatum().toString(), servis.getOpis(), servis.getStatus().toString(), String.valueOf(servis.isObrisan())));
	}
	
	
	//konvertuje iz niza stringova u niz servisa
	public static void konvertujSveServise() {
		ServisController.servisi.clear();
		for(int i = 0; i < podaci.size(); i++) {
			servisi.add(stringUServis(podaci.get(i)));
		}
	}
	
	
	public static ArrayList<Deo> korisceniDelovi(Servis servis) {
		ArrayList<Deo> listaTrazenihDelova = new ArrayList<Deo>();
		System.out.println("Servis " + servis);
		System.out.println("Delovi " + DeoController.delovi);
		for(Deo deo:DeoController.delovi) {
			if(deo.getIskoriscenUSevisu() != null) {
				if(deo.getIskoriscenUSevisu().getOznaka().equalsIgnoreCase(servis.getOznaka())) {
					listaTrazenihDelova.add(deo);
				}
			}
		}
		
		System.out.printf("Broj delova iskoriscenih u servisu: %s je %d", servis.getOznaka(), listaTrazenihDelova.size());
		return listaTrazenihDelova;
	}
	
	public static SimpleDoubleProperty cenaServisa(Servis servis) {
		Double cenaServisa = 0.0;
		System.out.println("Kor " + korisceniDelovi(servis));
		for(Deo deo : korisceniDelovi(servis)) {
			cenaServisa += deo.getCena();
		}
		System.out.println("cena " +cenaServisa);
		return new SimpleDoubleProperty(cenaServisa);
	}
	
	
	public static String vratiOznakuAutomobilaServisa(Servis servis) {
		String oznakaAutomobila = null;
		
		if(servis.getServisnaKnjizica() != null) {
			oznakaAutomobila = servis.getServisnaKnjizica().getOznaka();
		}
		
		return oznakaAutomobila;
	}
	

	
	public static Servis nadjiServisPoOznaci(String oznaka) {
		Servis trazenServis = null;
		for(Servis servis : servisi) {
			if(oznaka.equalsIgnoreCase(servis.getOznaka())) {
				trazenServis = servis;
			}
		}
		return trazenServis;
	}
	
	
	public static void inicijalizujServise() {
		System.out.println("Inicijalizacija servisa");
		procitajFajl();
		konvertujSveServise();
	}
	
	
	public static void izbrisiServis(Servis servis) {
		if(servis == null) {
			System.out.println("Molim vas izaberite validan servis");
		} else {
			servis.setObrisan(true);
		}
		sacuvajIzmeneUFajl();
	}
	
	
	public static void ispisiSveServise() {
		for(Servis servis:ServisController.servisi) {
			System.out.println(servis);
		}
	}
	
	
	public static ArrayList<Servis> getNeObrisaniServisi() {
		ArrayList<Servis> neObrisaniServisi = new ArrayList<Servis>();
		inicijalizujServise();
		for(Servis ser : ServisController.servisi) {
			if(ser.isObrisan() == false) {
				neObrisaniServisi.add(ser);
			}
		}
		return neObrisaniServisi;
	}
	
	public static void izbrisiIzUcitanihServisaSaOznakom(String oznaka) {
		ServisController.servisi.remove(nadjiServisPoOznaci(oznaka));
	}


	public static ArrayList<Servis> getServisi() {
		return servisi;
	}


	public static void setServisi(ArrayList<Servis> servisi) {
		ServisController.servisi = servisi;
	}
	
	
}
