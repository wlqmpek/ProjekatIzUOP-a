package view;

import java.util.ArrayList;

import enumi.Gorivo;
import enumi.Marka;
import enumi.Model;
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
import model.Automobil;
import model.Musterija;
import model.Servis;

public class RadSaAutomobilimaView extends Stage {
	
	private TableView<Automobil> tabela = new TableView<Automobil>();
	private TableColumn<Automobil, String> kolonaOznaka;
	private TableColumn<Automobil, String> kolonaVlasnik;
	private TableColumn<Automobil, Marka> kolonaMarka;
	private TableColumn<Automobil, Model> kolonaModel;
	private TableColumn<Automobil, Short> kolonaGodina;
	private TableColumn<Automobil, Short> kolonaZapremina;
	private TableColumn<Automobil, Short> kolonaSnaga;
	private TableColumn<Automobil, Gorivo> kolonaGorivo;
	
	private ComboBox<Musterija> vlasnikBox;
	private ComboBox<Marka> markaBox;
	private ComboBox<Model> modelBox;
	private ComboBox<Gorivo> gorivoBox;
	
	private Button dugmeSacuvaj;
	
	private TextField tfGodinaProizvodnje, tfZapreminaMotora, tfSnagaMotora;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"));
	private static ObservableList<Automobil> automobili = FXCollections.observableArrayList();
	private static ObservableList<Musterija> vlasnici = FXCollections.observableArrayList();
	
	public RadSaAutomobilimaView() {
		super();
		kreirajTabeluAutomobila();
		Scene scene = new Scene(kreirajRaspored(),1370,700);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setScene(scene);
		this.setResizable(true);
		this.show();
	}

	private void kreirajTabeluAutomobila() {
		
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Automobil, String>("Oznaka Automobila");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Automobil, String>("oznaka"));
		kolonaOznaka.setPrefWidth(SIRINA_KOLONA + 50);
		kolonaVlasnik = new TableColumn<Automobil, String>("Oznaka Vlasnika");
		kolonaVlasnik.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVlasnik().getOznaka()));
		kolonaVlasnik.setPrefWidth(SIRINA_KOLONA + 50);
		kolonaMarka = new TableColumn<Automobil, Marka>("Marka");
		kolonaMarka.setCellValueFactory(new PropertyValueFactory<Automobil, Marka>("marka"));
		kolonaMarka.setPrefWidth(SIRINA_KOLONA);
		kolonaModel = new TableColumn<Automobil, Model>("Model");
		kolonaModel.setCellValueFactory(new PropertyValueFactory<Automobil, Model>("model"));
		kolonaModel.setPrefWidth(SIRINA_KOLONA);
		kolonaGodina = new TableColumn<Automobil, Short>("Godina Pr.");
		kolonaGodina.setCellValueFactory(new PropertyValueFactory<Automobil, Short>("godinaProizvodnje"));
		kolonaGodina.setPrefWidth(SIRINA_KOLONA);
		kolonaZapremina = new TableColumn<Automobil, Short>("Zapremina");
		kolonaZapremina.setCellValueFactory(new PropertyValueFactory<Automobil, Short>("zapreminaMotora"));
		kolonaZapremina.setPrefWidth(SIRINA_KOLONA);
		kolonaSnaga = new TableColumn<Automobil, Short>("Snaga");
		kolonaSnaga.setCellValueFactory(new PropertyValueFactory<Automobil, Short>("snagaMotora"));
		kolonaSnaga.setPrefWidth(SIRINA_KOLONA);
		kolonaGorivo = new TableColumn<Automobil, Gorivo>("Gorivo");
		kolonaGorivo.setCellValueFactory(new PropertyValueFactory<Automobil, Gorivo>("gorivo"));
		kolonaGorivo.setPrefWidth(SIRINA_KOLONA);
		
		tabela.getColumns().addAll(kolonaVlasnik, kolonaMarka, kolonaModel, kolonaGodina, kolonaZapremina, kolonaSnaga, kolonaGorivo, kolonaOznaka);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(automobili);
	}
	
	private VBox kreirajRaspored() {
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		
		//kreiranje boxova
		final int SIRINA = 110;
		vlasnikBox = new ComboBox<Musterija>(vlasnici);
		vlasnikBox.setPrefWidth(SIRINA + 50);
		markaBox = new ComboBox<Marka>(FXCollections.observableArrayList(Marka.values()));
		markaBox.setValue(Marka.TESLA);
		markaBox.setPrefWidth(SIRINA);
		modelBox = new ComboBox<Model>(FXCollections.observableArrayList(Model.values()));
		modelBox.setValue(Model.MODEL_3);
		modelBox.setPrefWidth(SIRINA);
		gorivoBox = new ComboBox<Gorivo>(FXCollections.observableArrayList(Gorivo.values()));
		gorivoBox.setValue(Gorivo.STRUJA);
		gorivoBox.setPrefWidth(SIRINA);
		
		tfGodinaProizvodnje = new TextField();
		tfGodinaProizvodnje.setPromptText("Godina Pr.");
		tfGodinaProizvodnje.setPrefWidth(SIRINA);
		tfZapreminaMotora = new TextField();
		tfZapreminaMotora.setPromptText("Zapremina");
		tfZapreminaMotora.setPrefWidth(SIRINA);
		tfSnagaMotora = new TextField();
		tfSnagaMotora.setPromptText("Snaga");
		tfSnagaMotora.setPrefWidth(SIRINA);
		
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA + 50);
		
		hb1.getChildren().addAll(vlasnikBox, markaBox, modelBox, getTfGodinaProizvodnje(), tfZapreminaMotora, tfSnagaMotora, gorivoBox, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				meni.show(tabela, event.getScreenX(), event.getScreenY());
			}
		});
		hb2.getChildren().addAll(tabela);
		vb.getChildren().addAll(hb1,hb2);
		return vb;
	}
	
	public void popuniTabelu(ArrayList<Automobil> automobili) {
		RadSaAutomobilimaView.automobili = FXCollections.observableArrayList(automobili);
		tabela.setItems(RadSaAutomobilimaView.automobili);
	}
	
	public void resetujPolja() {
		tfGodinaProizvodnje.clear();
		tfZapreminaMotora.clear();
		tfSnagaMotora.clear();
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
	
	public void dodeliFunkcionalnostDugmetuSacuvaj(EventHandler<ActionEvent> event) {
		dugmeSacuvaj.setOnAction(event);
	}

	public TableView<Automobil> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Automobil> tabela) {
		this.tabela = tabela;
	}

	public ComboBox<Musterija> getVlasnikBox() {
		return vlasnikBox;
	}

	public void setVlasnikBox(ComboBox<Musterija> vlasnikBox) {
		this.vlasnikBox = vlasnikBox;
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

	public ComboBox<Gorivo> getGorivoBox() {
		return gorivoBox;
	}

	public void setGorivoBox(ComboBox<Gorivo> gorivoBox) {
		this.gorivoBox = gorivoBox;
	}

	public TextField getTfGodinaProizvodnje() {
		return tfGodinaProizvodnje;
	}

	public void setTfGodinaProizvodnje(TextField tfGodinaProizvodnje) {
		this.tfGodinaProizvodnje = tfGodinaProizvodnje;
	}

	public TextField getTfZapreminaMotora() {
		return tfZapreminaMotora;
	}

	public void setTfZapreminaMotora(TextField tfZapreminaMotora) {
		this.tfZapreminaMotora = tfZapreminaMotora;
	}

	public TextField getTfSnagaMotora() {
		return tfSnagaMotora;
	}

	public void setTfSnagaMotora(TextField tfSnagaMotora) {
		this.tfSnagaMotora = tfSnagaMotora;
	}

	public static ObservableList<Musterija> getVlasnici() {
		return vlasnici;
	}

	public static void setVlasnici(ObservableList<Musterija> vlasnici) {
		RadSaAutomobilimaView.vlasnici = vlasnici;
	}
	
	
}
