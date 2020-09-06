package view;

import java.util.ArrayList;
import enumi.Pol;
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
import model.Administrator;

public class RadSaAdministratorimaView extends Stage {
	
	private TableView<Administrator> tabela = new TableView<Administrator>();
	private TableColumn<Administrator, String> kolonaOznaka;
	private TableColumn<Administrator, String> kolonaIme;
	private TableColumn<Administrator, String> kolonaPrezime;
	private TableColumn<Administrator, String> kolonaJMBG;
	private TableColumn<Administrator, Pol> kolonaPol;
	private TableColumn<Administrator, String> kolonaAdresa;
	private TableColumn<Administrator, String> kolonaBrojTelefona;
	private TableColumn<Administrator, String> kolonaKorisnickoIme;
	private TableColumn<Administrator, String> kolonaLozinka;
	private TableColumn<Administrator, Double> kolonaPlata;
	private ComboBox<Pol> polBox;
	private TextField tfOznaka, tfIme, tfPrezime, tfJMBG, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, tfPlata;
	private Button dugmeSacuvaj;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"));
	private static ObservableList<Administrator> administratori = FXCollections.observableArrayList();
	
	public RadSaAdministratorimaView() {
		super();
		kreirajTabeluAdministratora();
		Scene scene = new Scene(kreirajRaspored(),1370,700);
		this.setScene(scene);
		this.setResizable(true);
		this.show();
	}
	
	private void kreirajTabeluAdministratora() {
		
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Administrator, String>("Oznaka");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Administrator, String>("oznaka"));
		kolonaOznaka.setPrefWidth(SIRINA_KOLONA);
		kolonaIme = new TableColumn<Administrator, String>("Ime");
		kolonaIme.setCellValueFactory(new PropertyValueFactory<Administrator, String>("ime"));
		kolonaIme.setPrefWidth(SIRINA_KOLONA);
		kolonaPrezime = new TableColumn<Administrator, String>("Prezime");
		kolonaPrezime.setCellValueFactory(new PropertyValueFactory<Administrator, String>("prezime"));
		kolonaPrezime.setPrefWidth(SIRINA_KOLONA);
		kolonaJMBG = new TableColumn<Administrator, String>("JMBG");
		kolonaJMBG.setCellValueFactory(new PropertyValueFactory<Administrator, String>("JMBG"));
		kolonaJMBG.setPrefWidth(SIRINA_KOLONA);
		kolonaPol = new TableColumn<Administrator, Pol>("Pol");
		kolonaPol.setCellValueFactory(new PropertyValueFactory<Administrator, Pol>("pol"));
		kolonaPol.setPrefWidth(SIRINA_KOLONA - 20);
		kolonaAdresa = new TableColumn<Administrator, String>("Adresa");
		kolonaAdresa.setCellValueFactory(new PropertyValueFactory<Administrator, String>("adresa"));
		kolonaAdresa.setPrefWidth(SIRINA_KOLONA);
		kolonaBrojTelefona = new TableColumn<Administrator, String>("Broj Telefona");
		kolonaBrojTelefona.setCellValueFactory(new PropertyValueFactory<Administrator, String>("brojTelefona"));
		kolonaBrojTelefona.setPrefWidth(SIRINA_KOLONA);
		kolonaKorisnickoIme = new TableColumn<Administrator, String>("Korisnicko Ime");
		kolonaKorisnickoIme.setCellValueFactory(new PropertyValueFactory<Administrator, String>("korisnickoIme"));
		kolonaKorisnickoIme.setPrefWidth(SIRINA_KOLONA);
		kolonaLozinka = new TableColumn<Administrator, String>("Lozinka");
		kolonaLozinka.setCellValueFactory(new PropertyValueFactory<Administrator, String>("lozinka"));
		kolonaLozinka.setPrefWidth(SIRINA_KOLONA);
		kolonaPlata = new TableColumn<Administrator, Double>("Plata");
		kolonaPlata.setCellValueFactory(new PropertyValueFactory<Administrator, Double>("plata"));
		kolonaPlata.setPrefWidth(SIRINA_KOLONA);
		
		tabela.getColumns().addAll(kolonaIme, kolonaPrezime,
				kolonaJMBG, kolonaPol, kolonaAdresa, kolonaBrojTelefona, kolonaKorisnickoIme,
				kolonaLozinka, kolonaPlata, kolonaOznaka);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabela.setItems(administratori);
	}
	
	private VBox kreirajRaspored() {
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		
		//kreiranje txt fieldova
		final int SIRINA_TEKSTUALNIH = 110;
		tfOznaka = new TextField();
		tfOznaka.setPromptText("Oznaka");
		tfOznaka.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfIme = new TextField();
		tfIme.setPromptText("Ime");
		tfIme.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfPrezime = new TextField();
		tfPrezime.setPromptText("Prezime");
		tfPrezime.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfJMBG = new TextField();
		tfJMBG.setPromptText("JMBG");
		tfJMBG.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfAdresa = new TextField();
		tfAdresa.setPromptText("Adresa");
		tfAdresa.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfBrojTelefona = new TextField();
		tfBrojTelefona.setPromptText("Broj Telefona");
		tfBrojTelefona.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfKorisnickoIme = new TextField();
		tfKorisnickoIme.setPromptText("Korisnicko Ime");
		tfKorisnickoIme.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfLozinka = new TextField();
		tfLozinka.setPromptText("Lozinka");
		tfLozinka.setPrefWidth(SIRINA_TEKSTUALNIH);
		tfPlata = new TextField();
		tfPlata.setPromptText("Plata");
		tfPlata.setPrefWidth(SIRINA_TEKSTUALNIH);
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA_TEKSTUALNIH);
		//kreiranje combo boxa
		ObservableList<Pol> listaPolova = FXCollections.observableArrayList(Pol.MUSKI, Pol.ZENSKI);
		polBox = new ComboBox<>(listaPolova);
		polBox.setPromptText("Pol");
		polBox.setPrefWidth(SIRINA_TEKSTUALNIH - 20);
		hb1.getChildren().addAll(tfIme, tfPrezime, tfJMBG, polBox, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, tfPlata, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				meni.show(tabela, event.getScreenX(), event.getScreenY());
			}
		});
		
		hb2.getChildren().addAll(tabela);
		vb.getChildren().addAll(hb1,hb2);
		
		return vb;
	}
	
	public void popuniTabelu(ArrayList<Administrator> administratori) {
		RadSaAdministratorimaView.administratori = FXCollections.observableArrayList(administratori);
		tabela.setItems(RadSaAdministratorimaView.administratori);
	}
	
	public void resetujPolja() {
		tfIme.clear();
		tfPrezime.clear();
		tfJMBG.clear();
		polBox.setPromptText("Pol");
		tfAdresa.clear();
		tfBrojTelefona.clear();
		tfKorisnickoIme.clear();
		tfLozinka.clear();
		tfPlata.clear();
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

	public TableView<Administrator> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Administrator> tabela) {
		this.tabela = tabela;
	}

	public ComboBox<Pol> getPolBox() {
		return polBox;
	}

	public void setPolBox(ComboBox<Pol> polBox) {
		this.polBox = polBox;
	}

	public TextField getTfOznaka() {
		return tfOznaka;
	}

	public void setTfOznaka(TextField tfOznaka) {
		this.tfOznaka = tfOznaka;
	}

	public TextField getTfIme() {
		return tfIme;
	}

	public void setTfIme(TextField tfIme) {
		this.tfIme = tfIme;
	}

	public TextField getTfPrezime() {
		return tfPrezime;
	}

	public void setTfPrezime(TextField tfPrezime) {
		this.tfPrezime = tfPrezime;
	}

	public TextField getTfJMBG() {
		return tfJMBG;
	}

	public void setTfJMBG(TextField tfJMBG) {
		this.tfJMBG = tfJMBG;
	}

	public TextField getTfAdresa() {
		return tfAdresa;
	}

	public void setTfAdresa(TextField tfAdresa) {
		this.tfAdresa = tfAdresa;
	}

	public TextField getTfBrojTelefona() {
		return tfBrojTelefona;
	}

	public void setTfBrojTelefona(TextField tfBrojTelefona) {
		this.tfBrojTelefona = tfBrojTelefona;
	}

	public TextField getTfKorisnickoIme() {
		return tfKorisnickoIme;
	}

	public void setTfKorisnickoIme(TextField tfKorisnickoIme) {
		this.tfKorisnickoIme = tfKorisnickoIme;
	}

	public TextField getTfLozinka() {
		return tfLozinka;
	}

	public void setTfLozinka(TextField tfLozinka) {
		this.tfLozinka = tfLozinka;
	}

	public TextField getTfPlata() {
		return tfPlata;
	}

	public void setTfPlata(TextField tfPlata) {
		this.tfPlata = tfPlata;
	}
	
	
}
