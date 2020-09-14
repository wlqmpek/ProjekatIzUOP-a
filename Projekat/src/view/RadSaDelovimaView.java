package view;

import java.util.ArrayList;

import controller.DeoController;
import enumi.Marka;
import enumi.Model;
import enumi.Status;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Deo;
import model.Servis;

public class RadSaDelovimaView extends Stage {
	
	//kolone
	private TableView<Deo> tabela = new TableView<Deo>();
	private TableColumn<Deo, String> kolonaOznaka;
	private TableColumn<Deo, Marka> kolonaMarka;
	private TableColumn<Deo, Model> kolonaModel;
	private TableColumn<Deo, String> kolonaNaziv;
	private TableColumn<Deo, Double> kolonaCena;
	private TableColumn<Deo, String> kolonaIskoriscenUServisu;
	//comboBoxovi
	private ComboBox<Servis> servisBox;
	private ComboBox<Marka> markaBox;
	private ComboBox<Model> modelBox;
	//txtFildovi
	private TextField tfNaziv, tfCena;
	private Button dugmeSacuvaj;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"), new MenuItem("Kreiraj Simetricni"));
	private static ObservableList<Deo> delovi = FXCollections.observableArrayList();
	private static ObservableList<Servis> servisi = FXCollections.observableArrayList();
	
	public RadSaDelovimaView() {
		super();
		kreirajTabeluSaDelovima();
		Scene scene = new Scene(kreirajRaspored(),1370,700);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setScene(scene);
		this.setResizable(true);
		this.show();
	}
	
	private VBox kreirajRaspored() {
		//kreiranje raspoeda
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		
		//kreiranje boxova
		final int SIRINA_BOXOVA = 110;
		markaBox = new ComboBox<Marka>(FXCollections.observableArrayList(Marka.values()));
		markaBox.setPromptText("Izaberite Marku");
		markaBox.setPrefWidth(SIRINA_BOXOVA);
		modelBox = new ComboBox<Model>(FXCollections.observableArrayList(Model.values()));
		modelBox.setPromptText("Izaberite Model");
		modelBox.setPrefWidth(SIRINA_BOXOVA);
		servisBox = new ComboBox<Servis>(servisi);
		servisBox.setPromptText("Izaberite Servis");
		servisBox.setPrefWidth(SIRINA_BOXOVA);
		
		//kreiranje txt fieldova
		final int SIRINA_TEKSTUALNIH = 110;
		tfNaziv = new TextField();
		tfNaziv.setPromptText("Naziv");
		tfNaziv.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfCena = new TextField();
		tfCena.setPromptText("Cena");
		tfCena.setPrefWidth(SIRINA_TEKSTUALNIH);
		
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA_TEKSTUALNIH);
		
		hb1.getChildren().addAll(markaBox, modelBox, tfNaziv, tfCena, servisBox, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				meni.show(tabela, event.getScreenX(), event.getScreenY());
			}
		});
		hb2.getChildren().addAll(tabela);
		vb.getChildren().addAll(hb1,hb2);
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
		kolonaIskoriscenUServisu = new TableColumn<Deo, String>("Servis");
		kolonaIskoriscenUServisu.setCellValueFactory(cellData -> new SimpleStringProperty(DeoController.vratiOznakuServisa(cellData.getValue())));
		kolonaIskoriscenUServisu.setMinWidth(SIRINA_KOLONA);
		

		tabela.getColumns().addAll(kolonaMarka, kolonaModel, kolonaNaziv, kolonaCena, kolonaIskoriscenUServisu, kolonaOznaka);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(delovi);
	}
	
	public void popuniTabelu(ArrayList<Deo> delovi) {
		RadSaDelovimaView.delovi = FXCollections.observableArrayList(delovi);
		tabela.setItems(RadSaDelovimaView.delovi);
	}
	
	public void resetujPolja() {
		markaBox.setPromptText("Izaberite Marku");
		modelBox.setPromptText("Izaberite Model");
		servisBox.setPromptText("Odaberite Servisera");
		servisBox.setValue(null);
		tfNaziv.clear();
		tfCena.clear();
	}
	
	public void izbaciPorukuOGresci(String poruka) {
		new Alert(Alert.AlertType.ERROR, poruka).showAndWait();
	}
	
	public void dodeliFunkcionalnostOpcijiIzbrisi(EventHandler<ActionEvent> event) {
		meni.getItems().get(0).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostOpcijiIzmeni(EventHandler<ActionEvent> event) {
		meni.getItems().get(1).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostOpcijiKreirajSimetricni(EventHandler<ActionEvent> event) {
		meni.getItems().get(2).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostDugmetuSacuvaj(EventHandler<ActionEvent> event) {
		dugmeSacuvaj.setOnAction(event);
	}

	public TableView<Deo> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Deo> tabela) {
		this.tabela = tabela;
	}

	public ComboBox<Servis> getServisBox() {
		return servisBox;
	}

	public void setServisBox(ComboBox<Servis> servisBox) {
		this.servisBox = servisBox;
	}

	public ComboBox<Marka> getMarkaBox() {
		return markaBox;
	}

	public void setMarkaBox(ComboBox<Marka> markaBox) {
		this.markaBox = markaBox;
	}

	public ComboBox<Model> getModelBox() {
		return modelBox;
	}

	public void setModelBox(ComboBox<Model> modelBox) {
		this.modelBox = modelBox;
	}

	public TextField getTfNaziv() {
		return tfNaziv;
	}

	public void setTfNaziv(TextField tfNaziv) {
		this.tfNaziv = tfNaziv;
	}

	public TextField getTfCena() {
		return tfCena;
	}

	public void setTfCena(TextField tfCena) {
		this.tfCena = tfCena;
	}

	public static ObservableList<Servis> getServisi() {
		return servisi;
	}

	public static void setServisi(ObservableList<Servis> servisi) {
		RadSaDelovimaView.servisi = servisi;
	}
	
	
}
