import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import controller.MusterijaController;
import enumi.Pol;
import enumi.Specijalizacija;
import model.Musterija;
import view.MusterijaView;

public class test {

	private static File file = new File("."+System.getProperty("file.separator")+ "podaci\\musterije.txt");
	
	public static void main(String[] args) {
		
//		Musterija m = MusterijaController.stringUMusteriju(new ArrayList<String>(Arrays.asList("asdasda", "Milos", "Vlku", "11111111111111111", "MUSKI", "Nikole Tesle 1", "0603433426", "wlqmpek", "lozinka", "10", "true")));
//		MusterijaController.upisiMusterijuUFajl(m);
		System.out.println("Done!");
		MusterijaController.procitajFajl();
		MusterijaController.konvertujSveMusterije();
	}

}
