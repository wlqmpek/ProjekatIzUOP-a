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

public class AdministratorController {
	private static File file = new File(".\\podaci\\administratori.txt");
	public static ArrayList<ArrayList<String>> podaci = new ArrayList<ArrayList<String>>();
	public static ArrayList<Administrator> administratori = new ArrayList<Administrator>();
	

	static {
		inicijalizujAdministratore();
	}
	
	
	public static void upisiAdministratoraUFajl(Administrator administrator) {
		administratori.add(administrator);
		String administratorKaoString = String.join("|", administratorUStringArray(administrator)) + "\n";	
		try {
			FileWriter fw = new FileWriter(file, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.append(administratorKaoString);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//cita fajl i stavlja podatke u niz nizova atributa 
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
	
	public static Administrator stringUAdministratora(ArrayList<String> podaci) {
		return new Administrator(podaci.get(0), podaci.get(1), podaci.get(2), podaci.get(3), Pol.valueOf(podaci.get(4)), podaci.get(5), podaci.get(6), podaci.get(7), podaci.get(8), Boolean.valueOf(podaci.get(9)), Double.valueOf(podaci.get(10)));
	}
	
	public static ArrayList<String> administratorUStringArray(Administrator administrator) {
		return new ArrayList<String>(Arrays.asList(administrator.getOznaka(), administrator.getIme(), administrator.getPrezime(), administrator.getJMBG(), administrator.getPol().toString(), administrator.getAdresa(), administrator.getBrojTelefona(), administrator.getKorisnickoIme(), administrator.getLozinka(), String.valueOf(administrator.isObrisan()), String.valueOf(administrator.getPlata())));
	}
	
	public static void konvertujSveAdministratore() {
		administratori.clear();
		for (ArrayList<String> admin : podaci) {
			administratori.add(stringUAdministratora(admin));
		}
	}
	
	public static void inicijalizujAdministratore() {
		System.out.println("Inicijalizacija administratora");
		administratori.clear();
		podaci.clear();
		procitajFajl();
		konvertujSveAdministratore();
	}
	
	public static Administrator nadjiAdministratoraPoOznaci(String oznaka) {
		Administrator trazenAdministrator = null;
		for(Administrator administrator : administratori) {
			if(oznaka.equalsIgnoreCase(administrator.getOznaka())) {
				trazenAdministrator = administrator;
			}
		}
		return trazenAdministrator;
	}
	
	public static Administrator nadjiAdministratoraPoKorisnickomImenu(String korisnickoIme) {
		Administrator trazenAdministrator = null;
		for(Administrator administrator : administratori) {
			if(korisnickoIme.equalsIgnoreCase(administrator.getKorisnickoIme())) {
				trazenAdministrator = administrator;
			}
		}
		return trazenAdministrator;
	}
	
	public static void izbrisiAdministratora(Administrator administrator) {
		if(administrator == null) {
			System.out.println("Molim vas izaberite validnog administratora");
		} else {
			administrator.setObrisan(true);
		}
	}
	
	public static void ispisiSveAdministratore() {
		for(Administrator admin : administratori) {
			System.out.println(admin.toString());
		}
	}
	
}
