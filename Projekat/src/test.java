import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import controller.AutomobilController;
import controller.MusterijaController;
import enumi.Model;
import enumi.Gorivo;
import enumi.Marka;
import enumi.Pol;
import enumi.Specijalizacija;
import model.Automobil;
import model.Musterija;
import view.MusterijaView;

public class test {

	private static File file = new File("."+System.getProperty("file.separator")+ "podaci\\musterije.txt");
	
	public static void main(String[] args) {
		
		MusterijaController.inicijalizujMusterije();
		AutomobilController.inicijalizujAutomobile();
		
		System.out.println("Pre ikakvog dodavanja!");
		
		System.out.println("Ispis svih musterija");
		MusterijaController.ispisiSveMusterije();
		
		System.out.println("Ispis svih automobila za odredjenu musteriju");
		MusterijaController.ispisiSveAutomobileMusterije("asdasda");
		
		System.out.println("Ispis svih automobila");
		AutomobilController.ispisiSveAutomobile();
		
		System.out.println("Ispis vlasnika automobila");
		AutomobilController.ispisiVlasnikaAutomobila("auto002");
		
		

	}

}
