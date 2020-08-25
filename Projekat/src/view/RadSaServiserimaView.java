package view;

import enumi.Pol;
import enumi.Specijalizacija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Administrator;
import model.Serviser;

public class RadSaServiserimaView  extends Stage {
	
	private Button dugmeOk = new Button("Okay");
	private TableView<Serviser> tabela;
	private TableColumn<Serviser, String> oznaka;
	private TableColumn<Serviser, String> ime;
	private TableColumn<Serviser, String> prezime;
	private TableColumn<Serviser, String> JMBG;
	private TableColumn<Serviser, Pol> pol;
	private TableColumn<Serviser, String> adresa;
	private TableColumn<Serviser, String> brojTelefona;
	private TableColumn<Serviser, String> korisnickoIme;
	private TableColumn<Serviser, String> lozinka;
	private TableColumn<Serviser, Specijalizacija> specijalizacija;
	private TableColumn<Serviser, Double> plata;
	
	private ObservableList<Serviser> lista = FXCollections.observableArrayList(
			new Serviser("asdadssd", "Milos", "Vlku", "1111111111111", Pol.MUSKI, "Nikole Tesle", "000123123312", "wlqmpek68", "pwwwww", true, Specijalizacija.AUTOELEKTRICAR, 10.33)
			);
	
	public RadSaServiserimaView() {
		super();
		kreirajTabelu();
		HBox hBox = new HBox(dugmeOk);
		hBox.getChildren().add(tabela);
		Scene scene = new Scene(hBox,300,250);
		
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setScene(scene);
		this.setResizable(false);
		this.show();
	}
	
	public void kreirajTabelu() {

		tabela.setItems(lista);
	}
	
	public void dodeliFunkcionalnostDugmetuOk(EventHandler<ActionEvent> event) {
		dugmeOk.setOnAction(event);
	}
}
