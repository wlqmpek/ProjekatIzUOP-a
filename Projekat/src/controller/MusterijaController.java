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
import view.MusterijaView;

public class MusterijaController {
	
	private Musterija model;
	private MusterijaView view;
	private static File file = new File(".\\podaci\\musterije.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Musterija> musterije = new ArrayList<Musterija>();
	
	public MusterijaController(Musterija model, MusterijaView view) {
		super();
		this.model = model;
		this.view = view;
	}
	
	//privremen
	public MusterijaController() {
		super();
	}
	
	public static void upisiMusterijuUFajl(Musterija musterija) {	
		String musterijaKaoString = String.join("|", musterijaUStringArray(musterija)) + "\n";	
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
	
	public static Musterija stringUMusteriju(ArrayList<String> podaci) {
		return new Musterija(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), Pol.valueOf(podaci.get(4)), podaci.get(5), podaci.get(6), podaci.get(7), podaci.get(8), Byte.valueOf(podaci.get(9)), false);
	}
	
	public static ArrayList<String> musterijaUStringArray(Musterija musterija) {
		return new ArrayList<String>(Arrays.asList(musterija.getOznaka(), musterija.getIme(), musterija.getPrezime(), musterija.getJMBG(), musterija.getPol().toString(), musterija.getAdresa(), musterija.getBrojTelefona(), musterija.getKorisnickoIme(), musterija.getLozinka(), String.valueOf(musterija.getBrojPoena()), String.valueOf(musterija.isObrisan())));
	}
	
	//konvertuje iz niza stringova u niz musterija
	public static void konvertujSveMusterije() {
		musterije.clear();
		for (ArrayList<String> mus : podaci) {
			musterije.add(stringUMusteriju(mus));
		}
	}
	
	
	public static ArrayList<Automobil> nadjiAutomobilePoIdVlasnika(String oznaka) {
		return AutomobilController.nadjiAutomobilePoIdVlasnika(oznaka);
	}
	
	public static void inicijalizujMusterije() {
		procitajFajl();
		konvertujSveMusterije();
	}
	
	public static Musterija nadjiMusteriju(String oznaka) {
		inicijalizujMusterije();
		Musterija trazenaMusterija = null;
		for(Musterija musterija : musterije) {
			if(oznaka.equals(musterija.getOznaka())) {
				trazenaMusterija = musterija;
			}
		}
		return trazenaMusterija;
	}
	
	//metode za demonstraciju funkcionalnosti
	
	public static void ispisiSveMusterije() {
		for(Musterija musterija : musterije) {
			System.out.println(musterija);
		}
	}
	
	public static void ispisiSveAutomobileMusterije(String oznaka) {
		for(Automobil auto : MusterijaController.nadjiAutomobilePoIdVlasnika(oznaka)) {
			System.out.println(auto);
		}
	}
	
}
