package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
import enumi.Status;
import model.Automobil;
import model.Deo;
import model.Servis;
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
	
	//paziiiiiiiii
	public static Servis stringUServis(ArrayList<String> podaci) {
		return new Servis(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), podaci.get(4), Status.valueOf(podaci.get(5)), Boolean.valueOf(podaci.get(6)));
	}
	
	public static ArrayList<String> servisUStringArray(Servis servis) {
		return new ArrayList<String>(Arrays.asList(servis.getOznaka(), servis.getServisnaKnjizica().getOznaka(), servis.getServiser().getOznaka(), servis.getDatum().toString(), servis.getOpis(), servis.getStatus().toString(), String.valueOf(servis.isObrisan())));
	}
	
	
	public static String vratiOznakuAutomobila(Servis servis) {
		String oznakaAutomobila = null;
		
		if(servis.getServisnaKnjizica() != null) {
			oznakaAutomobila = servis.getServisnaKnjizica().getOznaka();
		}
		
		return oznakaAutomobila;
	}
	
	public static ArrayList<Deo> korisceniDelovi(Servis servis) {
		ArrayList<Deo> listaTrazenihDelova = new ArrayList<Deo>();
		
		for(Deo deo:DeoController.delovi) {
			if(deo.getIskoriscenUSevisu().getOznaka().equalsIgnoreCase(servis.getOznaka())) {
				listaTrazenihDelova.add(deo);
			}
		}
		
		System.out.printf("Broj delova iskoriscenih u servisu: %s je %d", servis.getOznaka(), listaTrazenihDelova.size());
		return listaTrazenihDelova;
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
	
	//konvertuje iz niza stringova u niz servisa
	public static void konvertujSveServise() {
		servisi.clear();
		for (ArrayList<String> ser : podaci) {
			servisi.add(stringUServis(ser));
		}
	}
	
	public static void inicijalizujServise() {
		System.out.println("Inicijalizacija servisa");
		servisi.clear();
		podaci.clear();
		procitajFajl();
		konvertujSveServise();
	}
	
	public static void izbrisiServis(Servis servis) {
		if(servis == null) {
			System.out.println("Molim vas izaberite validan servis");
		} else {
			servis.setObrisan(true);
		}
	}
	
	public static void ispisiSveServise() {
		for(Servis servis:servisi) {
			System.out.println(servis);
		}
	}
}
