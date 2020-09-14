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
import model.Serviser;
import model.ServisnaKnjizica;

public class ServisnaKnjizicaController {
	private static File file = new File(".\\podaci\\servisneKnjizice.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<ServisnaKnjizica> servisneKnjizice = new ArrayList<ServisnaKnjizica>();
	
	static {
		inicijalizujServisneKnjizice();
	}
	
	public static void upisiServisnuKnjizicuUFajl(ServisnaKnjizica servisnaKnjizica) {
		ServisnaKnjizicaController.servisneKnjizice.add(servisnaKnjizica);
		String servisnaKnjizicaKaoString = String.join("|", servisnaKnjizicaUStringArray(servisnaKnjizica)) +  "\r\n";	
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
	
	public static void sacuvajIzmeneUFajl() {
		FileWriter fw;
		try {
			fw = new FileWriter(file, false);
			PrintWriter pw = new PrintWriter(fw);
			for(ServisnaKnjizica servisnaKnjizica : ServisnaKnjizicaController.servisneKnjizice) {
				pw.append(String.join("|", servisnaKnjizicaUStringArray(servisnaKnjizica)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//cita fajl i upisuje podatke u niz nizova atributa 
	public static void procitajFajl() {
		ServisnaKnjizicaController.podaci.clear();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine()) {
				ServisnaKnjizicaController.podaci.add(new ArrayList<String>(Arrays.asList((sc.nextLine()).split("\\|"))));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ServisnaKnjizica stringUServisnuKnjizicu(ArrayList<String> podaci) {
		System.out.println("Podaci servisne " +podaci);
		return new ServisnaKnjizica(podaci.get(0));
	}
	
	public static ArrayList<String> servisnaKnjizicaUStringArray(ServisnaKnjizica servisnaKnjizica) {
		return new ArrayList<String>(Arrays.asList(servisnaKnjizica.getOznaka()));
	}
	
	public static void konvertujSveServisneKnjizice() {
		ServisnaKnjizicaController.servisneKnjizice.clear();
		for (ArrayList<String> serKnji : ServisnaKnjizicaController.podaci) {
			ServisnaKnjizicaController.servisneKnjizice.add(stringUServisnuKnjizicu(serKnji));
		}
	}
	
	public static void inicijalizujServisneKnjizice() {
		System.out.println("Inicijalizacija servisnih knjizica");
		procitajFajl();
		konvertujSveServisneKnjizice();
	}
	
	public static ServisnaKnjizica nadjiServisnuKnjizicuPoOznaci(String oznaka) {
		ServisnaKnjizica trazenaServisnaKnjizica = null;
		for(ServisnaKnjizica servisnaKnjizica : ServisnaKnjizicaController.servisneKnjizice) {
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
		return listaTrazenihServisa;
	}
	
	public static Automobil nadjiAutomobilPoOznaci(String oznaka) {
		return AutomobilController.nadjiAutomobilPoOznaci(oznaka);
	}
	
	public static Automobil automobilServsneKnjizice(ServisnaKnjizica servisnaKnjizica) {
		return AutomobilController.nadjiAutomobilPoOznaci(servisnaKnjizica.getOznaka());
	}
	
	// treba pozvati metodu iz automobila koja brise automobile a ne oov
	public static void izbrisiServisnuKnjizicu(ServisnaKnjizica servisnaKnjizica) {
		
		System.out.println("Pogresna metoda pozvana");
	}
	
	public static void ispisiSveServisneKnjizice() {
		for(ServisnaKnjizica servisna:servisneKnjizice) {
			System.out.println(servisna.toString());
		}
	}
	
	public static ServisnaKnjizica generisiServisnuKnjizicuZaAutomobil(Automobil automobil) {
		ServisnaKnjizica generisanaServisnaKnjizica = new ServisnaKnjizica(automobil);
		upisiServisnuKnjizicuUFajl(generisanaServisnaKnjizica);
		return generisanaServisnaKnjizica;
	}
	
	public static ServisnaKnjizica servisnaKnjizicaAutomobila(Automobil automobil) {
		ServisnaKnjizica nadjenaServisnaKnjizica = null;
		
		nadjenaServisnaKnjizica = nadjiServisnuKnjizicuPoOznaci(automobil.getOznaka());
		
		if(nadjenaServisnaKnjizica == null) {
			System.out.println("Generisemo servisnu knjizicu");
			nadjenaServisnaKnjizica = generisiServisnuKnjizicuZaAutomobil(automobil);
		}
		
		return nadjenaServisnaKnjizica;
	}

	
	public static ArrayList<ServisnaKnjizica> getNeObrisaneServisneKnjizice() {
		ArrayList<ServisnaKnjizica> neObrisaneServisneKnjizice = new ArrayList<ServisnaKnjizica>();
		inicijalizujServisneKnjizice();
		for(ServisnaKnjizica sk : ServisnaKnjizicaController.servisneKnjizice) {
			if(sk.isObrisan() == false) {
				neObrisaneServisneKnjizice.add(sk);
			}
		}
		return neObrisaneServisneKnjizice;
	}
	
	public static void izbrisiIzUcitanihServisnihKnjizicaSaOznakom(String oznaka) {
		ServisnaKnjizicaController.servisneKnjizice.remove(nadjiServisnuKnjizicuPoOznaci(oznaka));
		AutomobilController.automobili.remove(nadjiAutomobilPoOznaci(oznaka));
	}

	public static ArrayList<ServisnaKnjizica> getServisneKnjizice() {
		inicijalizujServisneKnjizice();
		return ServisnaKnjizicaController.servisneKnjizice;
	}
	
	public static void setServisneKnjizice(ArrayList<ServisnaKnjizica> servisneKnjizice) {
		ServisnaKnjizicaController.servisneKnjizice = servisneKnjizice;
	}
	
	
}
