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
import model.Automobil;
import model.Musterija;
import model.Servis;
import model.ServisnaKnjizica;

public class ServisnaKnjizicaController {
	private static File file = new File(".\\podaci\\servisneKnjizice.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<ServisnaKnjizica> servisneKnjizice = new ArrayList<ServisnaKnjizica>();
	
	static {
		inicijalizujServisneKnjizice();
	}
	
	public static void upisiMusterijuUFajl(ServisnaKnjizica servisnaKnjizica) {
		servisneKnjizice.add(servisnaKnjizica);
		String servisnaKnjizicaKaoString = String.join("|", servisnaKnjizicaUStringArray(servisnaKnjizica)) + "\n";	
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(servisnaKnjizicaKaoString);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//cita fajl i upisuje podatke u niz nizova atributa 
	public static void procitajFajl() {
		podaci.clear();
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
	
	public static ServisnaKnjizica stringUServisnuKnjizicu(ArrayList<String> podaci) {
		return new ServisnaKnjizica(podaci.get(0));
	}
	
	public static ArrayList<String> servisnaKnjizicaUStringArray(ServisnaKnjizica servisnaKnjizica) {
		return new ArrayList<String>(Arrays.asList(servisnaKnjizica.getOznaka()));
	}
	
	public static void konvertujSveServisneKnjizice() {
		servisneKnjizice.clear();
		for (ArrayList<String> serKnji : podaci) {
			servisneKnjizice.add(stringUServisnuKnjizicu(serKnji));
		}
	}
	
	public static void inicijalizujServisneKnjizice() {
		System.out.println("Inicijalizacija servisnih knjizica");
		servisneKnjizice.clear();
		podaci.clear();
		procitajFajl();
		konvertujSveServisneKnjizice();
	}
	
	public static ServisnaKnjizica nadjiServisnuKnjizicuPoOznaci(String oznaka) {
		ServisnaKnjizica trazenaServisnaKnjizica = null;
		for(ServisnaKnjizica servisnaKnjizica : servisneKnjizice) {
			if(oznaka.equalsIgnoreCase(servisnaKnjizica.getOznaka())) {
				trazenaServisnaKnjizica = servisnaKnjizica;
			}
		}
		return trazenaServisnaKnjizica;
	}
	
	public static ArrayList<Servis> listaRadjenihServisa(ServisnaKnjizica servisnaKnjizica) {
		ArrayList<Servis> listaTrazenihServisa = new ArrayList<Servis>();
		
		for(Servis servis:ServisController.servisi) {
			if(servis.getServisnaKnjizica().getOznaka().equalsIgnoreCase(servisnaKnjizica.getOznaka())) {
				listaTrazenihServisa.add(servis);
			}
		}
		
		System.out.printf("Servisna knizica: %s ima %d uradjenih servisa", servisnaKnjizica.getOznaka(), listaTrazenihServisa.size());
		return listaTrazenihServisa;
	}
	
	public static Automobil nadjiAutomobil(String oznaka) {
		return AutomobilController.nadjiAutomobilPoOznaci(oznaka);
	}
	
	public static void ispisiSveServisneKnjizice() {
		for(ServisnaKnjizica servisna:servisneKnjizice) {
			System.out.println(servisna.toString());
		}
	}
	
}
