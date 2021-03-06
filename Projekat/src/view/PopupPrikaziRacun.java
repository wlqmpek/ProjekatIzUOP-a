package view;

import java.util.ArrayList;

import enumi.Marka;
import enumi.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.Deo;

public class PopupPrikaziRacun extends Popup{

	private TableView<Deo> tabela = new TableView<Deo>();
	private TableColumn<Deo, String> kolonaOznaka;
	private TableColumn<Deo, Marka> kolonaMarka;
	private TableColumn<Deo, Model> kolonaModel;
	private TableColumn<Deo, String> kolonaNaziv;
	private TableColumn<Deo, Double> kolonaCena;
	private TextField tfCenaDelova, tfCenaUsluge, tfUkupno;
	private final Label labelaPlus = new Label("+");
	private final Label labelaJednako = new Label("=");
	private double cenaDelova, cenaUsluge, ukupno;
	
	private Button dugmeZavrsi;
	
	public PopupPrikaziRacun(ArrayList<Deo> deloviUServisu, Double cenaDelova, Double cenaUsluge, Double ukupno) {
		
		this.cenaDelova = cenaDelova;
		this.cenaUsluge = cenaUsluge;
		this.ukupno = ukupno;
		kreirajTabeluSaDelovima(deloviUServisu);
		this.getContent().addAll(kreirajRaspored());
		
	}
	
	private void kreirajTabeluSaDelovima(ArrayList<Deo> deloviUServisu) {
		
		//kreiranje kolona
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Deo, String>("Oznaka");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Deo, String>("oznaka"));
		kolonaOznaka.setMinWidth(SIRINA_KOLONA);
		kolonaMarka = new TableColumn<Deo, Marka>("Marka");
		kolonaMarka.setCellValueFactory(new PropertyValueFactory<Deo, Marka>("marka"));
		kolonaMarka.setMinWidth(SIRINA_KOLONA);
		kolonaModel = new TableColumn<Deo, Model>("Model");
		kolonaModel.setCellValueFactory(new PropertyValueFactory<Deo, Model>("model"));
		kolonaModel.setMinWidth(SIRINA_KOLONA);
		kolonaNaziv = new TableColumn<Deo, String>("Naziv");
		kolonaNaziv.setCellValueFactory(new PropertyValueFactory<Deo, String>("naziv"));
		kolonaNaziv.setMinWidth(SIRINA_KOLONA);
		kolonaCena = new TableColumn<Deo, Double>("Cena");
		kolonaCena.setCellValueFactory(new PropertyValueFactory<Deo, Double>("cena"));
		kolonaCena.setMinWidth(SIRINA_KOLONA);
		

		tabela.getColumns().addAll(kolonaMarka, kolonaModel, kolonaNaziv, kolonaCena, kolonaOznaka);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(FXCollections.observableArrayList(deloviUServisu));
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	private VBox kreirajRaspored() {
		VBox vb = new VBox();
		HBox hb = new HBox();
		dugmeZavrsi = new Button("Zavrsi");
		tfCenaDelova = new TextField(String.valueOf(this.cenaDelova));
		tfCenaUsluge = new TextField(String.valueOf(this.cenaUsluge));
		tfUkupno = new TextField(String.valueOf(this.ukupno));
		hb.getChildren().addAll(tfCenaDelova, labelaPlus, tfCenaUsluge, labelaJednako, tfUkupno, dugmeZavrsi);
		
		vb.getChildren().addAll(tabela, hb);
		return vb;
	}
	
	public void dodeliFunkcionalnostDugmetuZavrsi(EventHandler<ActionEvent> eh) {
		dugmeZavrsi.setOnAction(eh);
	}
	
}
