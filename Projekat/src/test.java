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
		
//		Musterija m = MusterijaController.stringUMusteriju(new ArrayList<String>(Arrays.asList("asdasda", "Milos", "Vlku", "11111111111111111", "MUSKI", "Nikole Tesle 1", "0603433426", "wlqmpek", "lozinka", "10", "true")));
//		MusterijaController.upisiMusterijuUFajl(m);
		MusterijaController.inicijalizujMusterije();
		AutomobilController.inicijalizujAutomobile();
		
//		Musterija m = MusterijaController.musterije.get(1);
//		Automobil a = new Automobil("auto001", m, Marka.valueOf("TESLA"), Model.valueOf("MODEL_S"), (short)1996, (short)100, (short)130, Gorivo.valueOf("STRUJA"), true);
//		AutomobilController.upisiAutomobilUFajl(a);
//		System.out.println("Done");
	}

}
