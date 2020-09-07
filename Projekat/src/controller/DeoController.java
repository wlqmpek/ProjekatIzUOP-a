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
import model.Automobil;
import model.Deo;
import model.Servis;
import model.ServisnaKnjizica;


public class DeoController {
	private static File file = new File(".\\podaci\\delovi.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Deo> delovi = new ArrayList<Deo>();
	
	static {
		inicijalizujDelove();
	}
	
	public static void upisiDeoUFajl(Deo deo) {
		DeoController.delovi.add(deo);
		String deoKaoString = String.join("|", deoUStringArray(deo));
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(deoKaoString);
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
			for(Deo deo : DeoController.delovi) {
				pw.append(String.join("|", deoUStringArray(deo)));
				pw.append("\r\n");
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void procitajFajl() {
		DeoController.podaci.clear();
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
	
	public static Deo stringUDeo(ArrayList<String> podaci) {
		System.out.println("Podaci " +podaci);
		return new Deo(podaci.get(0), Marka.valueOf(podaci.get(1)), Model.valueOf(podaci.get(2)), podaci.get(3), Double.valueOf(podaci.get(4)), podaci.get(5), Boolean.valueOf(podaci.get(6)));
	}
	
	public static ArrayList<String> deoUStringArray(Deo deo) {
		return new ArrayList<String>(Arrays.asList(deo.getOznaka(), deo.getMarka().toString(), deo.getModel().toString(), deo.getNaziv(), String.valueOf(deo.getCena()), DeoController.vratiOznakuServisa(deo), String.valueOf(deo.isObrisan())));
	}
	
	public static String vratiOznakuServisa(Deo deo) {
		String oznakaServisa = null;
		if(deo.getIskoriscenUSevisu() != null) {
			oznakaServisa = deo.getIskoriscenUSevisu().getOznaka();
		}	
		return oznakaServisa;
	}
	
	public static Servis nadjiServis(String oznaka) {
		return ServisController.nadjiServisPoOznaci(oznaka);
	}
	
	public static Deo nadjiDeoPoOznaci(String oznaka) {
		Deo trazenDeo = null;
		for(Deo deo : DeoController.delovi) {
			if(oznaka.equalsIgnoreCase(deo.getOznaka())) {
				trazenDeo = deo;
			}
		}
		return trazenDeo;
	}
	
	public static void inicijalizujDelove() {
		System.out.println("Inicijalizacija delova");
		procitajFajl();
		konvertujSveDelove();
	}
	
	//konvertuje iz niza stringova u niz automobila
	public static void konvertujSveDelove() {
		DeoController.delovi.clear();
		for (ArrayList<String> deo : DeoController.podaci) {
			DeoController.delovi.add(stringUDeo(deo));
		}
	}
	
	public static void izbrisiDeo(Deo deo) {
		if(deo == null) {
			System.out.println("Molim vas izaberite validan deo");
		} else {
			deo.setObrisan(true);
		}
		sacuvajIzmeneUFajl();
	}
	
	public static void ispisiSveDelove() {
		for(Deo deo:delovi) {
			System.out.println(deo.toString());
		}
	}
	
	public static ArrayList<Deo> neObrisaniDelovi() {
		ArrayList<Deo> neObrisaniDelovi = new ArrayList<Deo>();
		inicijalizujDelove();
		for(Deo deo : DeoController.delovi) {
			if(deo.isObrisan() == false) {
				neObrisaniDelovi.add(deo);
			}
		}
		return neObrisaniDelovi;
	}
	
	public static void izbrisiIzUcitanihDelovaSaOznakom(String oznaka) {
		DeoController.delovi.remove(nadjiDeoPoOznaci(oznaka));
	}


	public static ArrayList<Deo> getDelovi() {
		inicijalizujDelove();
		return DeoController.delovi;
	}


	public static void setServisi(ArrayList<Servis> servisi) {
		ServisController.servisi = servisi;
	}
	
}
