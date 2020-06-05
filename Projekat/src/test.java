import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import controller.AutomobilController;
import controller.MusterijaController;
import controller.ServisController;
import controller.ServiserController;
import controller.ServisnaKnjizicaController;
import enumi.Model;
import enumi.Gorivo;
import enumi.Marka;
import enumi.Pol;
import enumi.Specijalizacija;
import model.Automobil;
import model.Musterija;
import model.ServisnaKnjizica;
import view.MusterijaView;

public class test {

	private static File file = new File("."+System.getProperty("file.separator")+ "podaci\\musterije.txt");
	
	public static void main(String[] args) {
		
//		System.out.println("Pre ikakvog dodavanja!");
//		
//		System.out.println("Ispis svih musterija");
//		MusterijaController.ispisiSveMusterije();
//		
//		System.out.println("Ispis svih automobila za odredjenu musteriju");
//		MusterijaController.ispisiSveAutomobileMusterije("13763d23-0198-4195-92ad-ffe122e041db");
//		
//		System.out.println("Ispis svih automobila");
//		AutomobilController.ispisiSveAutomobile();
//		
//		System.out.println("Ispis vlasnika automobila");
//		AutomobilController.ispisiVlasnikaAutomobila("e252633f-d8f2-4d2c-bf22-25bdfd9829b1");
//		
//		System.out.println("Posle dodavanja!");
//		
//		MusterijaController.upisiMusterijuUFajl(new Musterija("Branko", "Brankovic", "5555555555555", enumi.Pol.MUSKI, "Velike Livade bb", "030582944", "brankokralj", "lozinkabre", (byte)0, false));
//		AutomobilController.upisiAutomobilUFajl(new Automobil("98c7da43-bc50-471a-92e0-8df70c9ea489", enumi.Marka.VOLVO, enumi.Model.MODEL_Y, (short)2020, (short)2020, (short)99, enumi.Gorivo.PLIN, false));
//		
//		MusterijaController.inicijalizujMusterije();
//		AutomobilController.inicijalizujAutomobile();
//		
//		System.out.println("Ispis svih musterija");
//		MusterijaController.ispisiSveMusterije();
//		
//		System.out.println("Ispis svih automobila za odredjenu musteriju");
//		MusterijaController.ispisiSveAutomobileMusterije("e252633f-d8f2-4d2c-bf22-25bdfd9829b1");
//		
//		System.out.println("Ispis svih automobila");
//		AutomobilController.ispisiSveAutomobile();
//		
//		System.out.println("Ispis vlasnika automobila");
//		AutomobilController.ispisiVlasnikaAutomobila("auto002");
		ServisnaKnjizicaController.ispisiSveServisneKnjizice();
		ServisController.ispisiSveServise();

	}

}
