package view;

import java.util.ArrayList;

import enumi.Marka;
import enumi.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.Deo;

public class PopupTabelaDelovaView extends Popup{
	
	private TableView<Deo> tabela = new TableView<Deo>();
	private TableColumn<Deo, String> kolonaOznaka;
	private TableColumn<Deo, Marka> kolonaMarka;
	private TableColumn<Deo, Model> kolonaModel;
	private TableColumn<Deo, String> kolonaNaziv;
	private TableColumn<Deo, Double> kolonaCena;
	
	private Button dugmeZavrsi, dugmeOtkazi;
	private static ObservableList<Deo> delovi = FXCollections.observableArrayList();
	
	public PopupTabelaDelovaView() {
		kreirajTabeluSaDelovima();
		kreirajRaspored();
		this.getContent().addAll(kreirajRaspored());
	}
	
	private VBox kreirajRaspored() {
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		dugmeZavrsi = new Button("Zavrsi");
		dugmeOtkazi = new Button("Otkazi");
		hb1.getChildren().addAll(dugmeZavrsi, dugmeOtkazi);
		vb.getChildren().addAll(tabela, hb1);
		return vb;
	}
	
	private void kreirajTabeluSaDelovima() {
		
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
		tabela.setItems(delovi);
		tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public void popuniTabelu(ArrayList<Deo> delovi) {
		PopupTabelaDelovaView.delovi = FXCollections.observableArrayList(delovi);
		tabela.setItems(PopupTabelaDelovaView.delovi);
	}
	
	
	public TableView<Deo> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Deo> tabela) {
		this.tabela = tabela;
	}

	public static ObservableList<Deo> getDelovi() {
		return delovi;
	}

	public static void setDelovi(ObservableList<Deo> delovi) {
		PopupTabelaDelovaView.delovi = delovi;
	}

	public Button getDugmeZavrsi() {
		return dugmeZavrsi;
	}

	public void setDugmeZavrsi(Button dugmeZavrsi) {
		this.dugmeZavrsi = dugmeZavrsi;
	}

	public Button getDugmeOtkazi() {
		return dugmeOtkazi;
	}

	public void setDugmeOtkazi(Button dugmeOtkazi) {
		this.dugmeOtkazi = dugmeOtkazi;
	}
	
	
	
}
