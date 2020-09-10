package view;

import java.util.ArrayList;

import controller.ServisController;
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
import model.Servis;
import model.Serviser;
import model.ServisnaKnjizica;

public class ServiserGlavniMeniView extends Stage{

	private TableView<Servis> tabela = new TableView<Servis>();
	private TableColumn<Servis, String> kolonaOznaka;
	private TableColumn<Servis, String> kolonaServisnaKnjizica;
	private TableColumn<Servis, String> kolonaDatum;
	private TableColumn<Servis, String> kolonaOpis;
	private TableColumn<Servis, Status> kolonaStatus;
	private TableColumn<Servis, Double> kolonaCenaServisa;
	private TableColumn<Servis, Double> kolonaTroskoviServisa;
	//comboBoxovi
	private ComboBox<ServisnaKnjizica> servisnaKnjiizcaBox;
	private ComboBox<Status> statusBox;
	//txtFildovi
	private TextField tfDatum, tfOpis;
	private Button dugmeSacuvaj, dugmeTroskoviServisa, dugmeCenaServisa;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izmeni"), new MenuItem("Dodaj Novi"), new MenuItem("Izaberi delove"));
	private static ObservableList<Servis> servisi = FXCollections.observableArrayList();
	private static ObservableList<ServisnaKnjizica> servisneKnjizice = FXCollections.observableArrayList();
	
	
	public ServiserGlavniMeniView() {
		super();
		kreirajTabeluSaServisima();
		Scene scene = new Scene(kreirajRaspored(),1370,700);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
		servisnaKnjiizcaBox = new ComboBox<ServisnaKnjizica>(servisneKnjizice);
		servisnaKnjiizcaBox.setPromptText("Servisna Knjizica");
		servisnaKnjiizcaBox.setPrefWidth(SIRINA_BOXOVA);
		statusBox = new ComboBox<Status>(FXCollections.observableArrayList(Status.values()));
		statusBox.setPromptText("Status");
		statusBox.setPrefWidth(SIRINA_BOXOVA);
		
		//kreiranje txt fieldova
		final int SIRINA_TEKSTUALNIH = 110;
		tfDatum = new TextField();
		tfDatum.setPromptText("Datum");
		tfDatum.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfOpis = new TextField();
		tfOpis.setPromptText("Opis");
		tfOpis.setPrefWidth(SIRINA_TEKSTUALNIH);
		dugmeTroskoviServisa = new Button("Troskovi ser.");
		dugmeTroskoviServisa.setPrefWidth(SIRINA_TEKSTUALNIH);
		dugmeCenaServisa = new Button("Cena ser.");
		dugmeCenaServisa.setPrefWidth(SIRINA_TEKSTUALNIH);
		
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA_TEKSTUALNIH);
		
		hb1.getChildren().addAll(servisnaKnjiizcaBox, tfDatum, tfOpis, statusBox, dugmeTroskoviServisa, dugmeCenaServisa, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				meni.show(tabela, event.getScreenX(), event.getScreenY());
			}
		});
		hb2.getChildren().addAll(tabela);
		vb.getChildren().addAll(hb1,hb2);
		return vb;
	}
	
	private void kreirajTabeluSaServisima() {
		
		//kreiranje kolona
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Servis, String>("Oznaka Servisa");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Servis, String>("oznaka"));
		kolonaOznaka.setMinWidth(SIRINA_KOLONA);
		kolonaServisnaKnjizica = new TableColumn<Servis, String>("Oznaka Servisne/Automobila");
		kolonaServisnaKnjizica.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getServisnaKnjizica().getOznaka()));
		kolonaServisnaKnjizica.setMinWidth(SIRINA_KOLONA);
		kolonaDatum = new TableColumn<Servis, String>("Datum");
		kolonaDatum.setCellValueFactory(new PropertyValueFactory<Servis, String>("datum"));
		kolonaDatum.setMinWidth(SIRINA_KOLONA);
		kolonaOpis = new TableColumn<Servis, String>("Opis");
		kolonaOpis.setCellValueFactory(new PropertyValueFactory<Servis, String>("opis"));
		kolonaOpis.setMinWidth(SIRINA_KOLONA);
		kolonaStatus = new TableColumn<Servis, Status>("Status");
		kolonaStatus.setCellValueFactory(new PropertyValueFactory<Servis, Status>("status"));
		kolonaStatus.setMinWidth(SIRINA_KOLONA);
		//ovde obrati paznju
		kolonaCenaServisa = new TableColumn<Servis, Double>("Cena ser.");
		kolonaCenaServisa.setCellValueFactory(cellData -> ServisController.cenaServisa(cellData.getValue()).asObject());
		kolonaCenaServisa.setMinWidth(SIRINA_KOLONA);
		kolonaTroskoviServisa = new TableColumn<Servis, Double>("Troskovi ser.");
		kolonaTroskoviServisa.setCellValueFactory(cellData -> ServisController.cenaServisa(cellData.getValue()).asObject());
		kolonaTroskoviServisa.setMinWidth(SIRINA_KOLONA);
		
		tabela.getColumns().addAll(kolonaServisnaKnjizica, kolonaDatum, kolonaOpis, kolonaStatus, kolonaTroskoviServisa, kolonaCenaServisa, kolonaOznaka);
		//tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(servisi);
	}
	
	
	public void resetujPolja() {
		servisnaKnjiizcaBox.setPromptText("Servisna Knjizica");
		tfDatum.clear();
		tfOpis.clear();
	}
	
	public void izbaciPorukuOGresci(String poruka) {
		new Alert(Alert.AlertType.ERROR, poruka).showAndWait();
	}

	
	public void popuniTabelu(ArrayList<Servis> servisi) {
		ServiserGlavniMeniView.servisi = FXCollections.observableArrayList(servisi);
		tabela.setItems(ServiserGlavniMeniView.servisi);
	}
	
	public void dodeliFunkcionalnostOpcijiIzmeni(EventHandler<ActionEvent> event) {
		meni.getItems().get(0).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostOpcijiDodajNovi(EventHandler<ActionEvent> event) {
		meni.getItems().get(1).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostOpcijiDodajDelove(EventHandler<ActionEvent> event) {
		meni.getItems().get(2).setOnAction(event);
	}
	
	public void dodeliFunkcionalnostDugmetuSacuvaj(EventHandler<ActionEvent> event) {
		dugmeSacuvaj.setOnAction(event);
	}
	
	public ComboBox<ServisnaKnjizica> getServisnaKnjiizcaBox() {
		return servisnaKnjiizcaBox;
	}

	public void setServisnaKnjiizcaBox(ComboBox<ServisnaKnjizica> servisnaKnjiizcaBox) {
		this.servisnaKnjiizcaBox = servisnaKnjiizcaBox;
	}

	public ComboBox<Status> getStatusBox() {
		return statusBox;
	}

	public void setStatusBox(ComboBox<Status> statusBox) {
		this.statusBox = statusBox;
	}

	public TextField getTfDatum() {
		return tfDatum;
	}

	public void setTfDatum(TextField tfDatum) {
		this.tfDatum = tfDatum;
	}

	public TextField getTfOpis() {
		return tfOpis;
	}

	public void setTfOpis(TextField tfOpis) {
		this.tfOpis = tfOpis;
	}

	public static ObservableList<ServisnaKnjizica> getServisneKnjizice() {
		return servisneKnjizice;
	}
	
	
	
	public Button getDugmeTroskoviServisa() {
		return dugmeTroskoviServisa;
	}

	public void setDugmeTroskoviServisa(Button dugmeTroskoviServisa) {
		this.dugmeTroskoviServisa = dugmeTroskoviServisa;
	}

	public Button getDugmeCenaServisa() {
		return dugmeCenaServisa;
	}

	public void setDugmeCenaServisa(Button dugmeCenaServisa) {
		this.dugmeCenaServisa = dugmeCenaServisa;
	}

	public static void setServisneKnjizice(ObservableList<ServisnaKnjizica> servisneKnjizice) {
		ServiserGlavniMeniView.servisneKnjizice = servisneKnjizice;
	}

	public TableView<Servis> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Servis> tabela) {
		this.tabela = tabela;
	}
	
	
	
}
