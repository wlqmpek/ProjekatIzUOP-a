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
		String servisKaoString = String.join("|", servisUStringArray(servis)) + "\r\n";
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
		return new Servis(konvertujStringNull(podaci.get(0)), konvertujStringNull(podaci.get(1)), konvertujStringNull(podaci.get(2)), konvertujStringNull(podaci.get(3)), konvertujStringNull(podaci.get(4)), proveraNullDouble(podaci.get(5)) , Status.valueOf(konvertujStringNull(podaci.get(6))), Boolean.valueOf(konvertujStringNull(podaci.get(7))));
	}
	
	public static ArrayList<String> servisUStringArray(Servis servis) {
		return new ArrayList<String>(Arrays.asList(servis.getOznaka(), servis.getServisnaKnjizica().getOznaka(), vratiOznakuServiseraServisa(servis), vratiDatumServisa(servis), servis.getOpis(), String.valueOf(servis.getTroskoviServisa()), vratiStatusServisa(servis), String.valueOf(servis.isObrisan())));
	}
	
	
	//konvertuje iz niza stringova u niz servisa
	public static void konvertujSveServise() {
		ServisController.servisi.clear();
		for(int i = 0; i < podaci.size(); i++) {
			servisi.add(stringUServis(podaci.get(i)));
		}
	}
	
	public static String konvertujStringNull(String string) {
		String konvertovanString = null;
		if(string == null || string.equalsIgnoreCase("null")) {
			konvertovanString = null;
		} else {
			konvertovanString = string;
		}
		
		return konvertovanString;
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
	
	public static Double proveraNullDouble(String str) {
		Double d = null;
		if(str == null) {
			d = null;
		} else if(str.equalsIgnoreCase("null")) {
			d = null;
		} else {
			d = Double.valueOf(str);
		}
		return d;
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
		if(oznaka == null) {
			//ne radi nista meh
		} else {
			for(Servis servis : servisi) {
				if(oznaka.equalsIgnoreCase(servis.getOznaka())) {
					trazenServis = servis;
				}
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
	
	public static boolean servisImaNullVrednosti(Servis servis) {
		boolean nullVrednosti = true; 
		if(servis.getOznaka() != null && servis.getServisnaKnjizica() != null && servis.getServiser() != null && servis.getDatum() != null && servis.getOpis() != null && servis.getTroskoviServisa() != null && servis.getStatus() != null) {
			nullVrednosti = false;
		}
		return nullVrednosti;
	}
	
	
	public static ArrayList<Servis> getServiseServisera(Serviser serviser) {
		ArrayList<Servis> listaTrazenihServisa = new ArrayList<Servis>();
		
		for(Servis servis : ServisController.getNeObrisaniServisi()) {
			if(servis.getServiser() != null) {
				if(servis.getServiser().getOznaka().equalsIgnoreCase(serviser.getOznaka())) {
					listaTrazenihServisa.add(servis);
				}
			}
		}
		return listaTrazenihServisa;
	}
	
	public static String vratiOznakuServiseraServisa(Servis servis) {
		String oznakaServisera = null;
		if(servis.getServiser() != null) {
			oznakaServisera = servis.getServiser().getOznaka();
		}
		return oznakaServisera;
	}
	
	public static String vratiDatumServisa(Servis servis) {
		String datumServisa = null;
		if(servis.getDatum() != null) {
			datumServisa = servis.getDatum().toString();
		}
		return datumServisa;
	}
	
	public static String vratiStatusServisa(Servis servis) {
		String statusServisa = null;
		if(servis.getStatus() != null) {
			statusServisa = servis.getStatus().toString();
		}
		return statusServisa;
	}
	
	public static String vratiTroskoveServisa(Servis servis) {
		String troskoviServisa = null;
		if(servis.getStatus() != null) {
			troskoviServisa = servis.getTroskoviServisa().toString();
		}
		return troskoviServisa;
	}
	
	public static void izbrisiIzUcitanihServisaSaOznakom(String oznaka) {
		ServisController.servisi.remove(nadjiServisPoOznaci(oznaka));
	}


	public static ArrayList<Servis> getServisi() {
		inicijalizujServise();
		return ServisController.servisi;
	}


	public static void setServisi(ArrayList<Servis> servisi) {
		ServisController.servisi = servisi;
	}
	
	
}
