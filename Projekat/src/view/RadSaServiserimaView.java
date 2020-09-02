package view;

import java.util.ArrayList;

import enumi.Pol;
import enumi.Specijalizacija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Serviser;

public class RadSaServiserimaView  extends Stage {
	
	private Button dugmeOk = new Button("Okay");
	private TableView<Serviser> tabela = new TableView<Serviser>();
	private TableColumn<Serviser, String> kolonaOznaka;
	private TableColumn<Serviser, String> kolonaIme;
	private TableColumn<Serviser, String> kolonaPrezime;
	private TableColumn<Serviser, String> kolonaJMBG;
	private TableColumn<Serviser, Pol> kolonaPol;
	private TableColumn<Serviser, String> kolonaAdresa;
	private TableColumn<Serviser, String> kolonaBrojTelefona;
	private TableColumn<Serviser, String> kolonaKorisnickoIme;
	private TableColumn<Serviser, String> kolonaLozinka;
	private TableColumn<Serviser, Specijalizacija> kolonaSpecijalizacija;
	private TableColumn<Serviser, Double> kolonaPlata;
	private ComboBox<Pol> polBox;
	private ComboBox<Specijalizacija> specijalizacijaBox;
	private TextField tfOznaka, tfIme, tfPrezime, tfJMBG, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, tfPlata;
	private Button dugmeSacuvaj;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"));
	private ObservableList<Serviser> serviseri = FXCollections.observableArrayList();
	

	
	public RadSaServiserimaView() {
		super();
		kreirajTabeluServisera();
		
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
		//POL
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
		ObservableList<Specijalizacija> listaSpecijalizacija = FXCollections.observableArrayList(Specijalizacija.AUTOELEKTRICAR, Specijalizacija.AUTOMEHANICAR, Specijalizacija.LIMAR, Specijalizacija.VULKANIZER);
		specijalizacijaBox = new ComboBox<>(listaSpecijalizacija);
		specijalizacijaBox.setPromptText("Specijalizacija");
		specijalizacijaBox.setPrefWidth(SIRINA_TEKSTUALNIH + 10);
		
		
		hb1.getChildren().addAll(tfIme, tfPrezime, tfJMBG, polBox, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, specijalizacijaBox, tfPlata, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			
			if (event.getButton() == MouseButton.SECONDARY) {
				
				
				meni.show(tabela, event.getScreenX(), event.getScreenY());
				
			}
			
		});
		
		hb2.getChildren().addAll(tabela);
		
		vb.getChildren().addAll(hb1,hb2);
		
		
		return vb;
	}
	
	private void kreirajTabeluServisera() {
		
		//kreiranje kolona
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Serviser, String>("Oznaka");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Serviser, String>("oznaka"));
		kolonaOznaka.setMinWidth(SIRINA_KOLONA);
		kolonaIme = new TableColumn<Serviser, String>("Ime");
		kolonaIme.setCellValueFactory(new PropertyValueFactory<Serviser, String>("ime"));
		kolonaIme.setMinWidth(SIRINA_KOLONA);
		kolonaPrezime = new TableColumn<Serviser, String>("Prezime");
		kolonaPrezime.setCellValueFactory(new PropertyValueFactory<Serviser, String>("prezime"));
		kolonaPrezime.setMinWidth(SIRINA_KOLONA);
		kolonaJMBG = new TableColumn<Serviser, String>("JMBG");
		kolonaJMBG.setCellValueFactory(new PropertyValueFactory<Serviser, String>("JMBG"));
		kolonaJMBG.setMinWidth(SIRINA_KOLONA);
		kolonaPol = new TableColumn<Serviser,Pol>("Pol");
		kolonaPol.setCellValueFactory(new PropertyValueFactory<Serviser, Pol>("pol"));
		kolonaPol.setPrefWidth(SIRINA_KOLONA - 20);
		kolonaAdresa = new TableColumn<Serviser, String>("Adresa");
		kolonaAdresa.setCellValueFactory(new PropertyValueFactory<Serviser, String>("adresa"));
		kolonaAdresa.setMinWidth(SIRINA_KOLONA);
		kolonaBrojTelefona = new TableColumn<Serviser, String>("Broj Telefona");
		kolonaBrojTelefona.setCellValueFactory(new PropertyValueFactory<Serviser, String>("brojTelefona"));
		kolonaBrojTelefona.setMinWidth(SIRINA_KOLONA);
		kolonaKorisnickoIme = new TableColumn<Serviser, String>("Korisnicko Ime");
		kolonaKorisnickoIme.setCellValueFactory(new PropertyValueFactory<Serviser, String>("korisnickoIme"));
		kolonaKorisnickoIme.setMinWidth(SIRINA_KOLONA);
		kolonaLozinka = new TableColumn<Serviser, String>("Lozinka");
		kolonaLozinka.setCellValueFactory(new PropertyValueFactory<Serviser, String>("lozinka"));
		kolonaLozinka.setMinWidth(SIRINA_KOLONA);
		kolonaSpecijalizacija = new TableColumn<Serviser, Specijalizacija>("Specijalizacija");
		kolonaSpecijalizacija.setCellValueFactory(new PropertyValueFactory<Serviser, Specijalizacija>("specijalizacija"));
		kolonaSpecijalizacija.setPrefWidth(SIRINA_KOLONA + 10);
		kolonaPlata = new TableColumn<Serviser, Double>("Plata");
		kolonaPlata.setCellValueFactory(new PropertyValueFactory<Serviser, Double>("plata"));
		kolonaPlata.setMinWidth(SIRINA_KOLONA);
		
		tabela.getColumns().addAll(kolonaIme, kolonaPrezime,
				kolonaJMBG, kolonaPol, kolonaAdresa, kolonaBrojTelefona, kolonaKorisnickoIme,
				kolonaLozinka, kolonaSpecijalizacija, kolonaPlata, kolonaOznaka);
		
		
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		tabela.setItems(serviseri);
	}
	
	public void popuniTabelu(ArrayList<Serviser> serviseri) {
		this.serviseri = FXCollections.observableArrayList(serviseri);
		tabela.setItems(this.serviseri);
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
		specijalizacijaBox.setPromptText("Specijalizacija");
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

	public TableView<Serviser> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Serviser> tabela) {
		this.tabela = tabela;
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

	public ComboBox<Pol> getPolBox() {
		return polBox;
	}

	public void setPolBox(ComboBox<Pol> polBox) {
		this.polBox = polBox;
	}

	public ComboBox<Specijalizacija> getSpecijalizacijaBox() {
		return specijalizacijaBox;
	}

	public void setSpecijalizacijaBox(ComboBox<Specijalizacija> specijalizacijaBox) {
		this.specijalizacijaBox = specijalizacijaBox;
	}
	
	
	
}


















