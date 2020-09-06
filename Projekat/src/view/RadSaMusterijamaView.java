package view;

import java.util.ArrayList;

import enumi.Pol;
import enumi.Specijalizacija;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
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
import model.Musterija;
import model.Serviser;
import model.Musterija;

public class RadSaMusterijamaView extends Stage {
	
	private TableView<Musterija> tabela = new TableView<Musterija>();
	private TableColumn<Musterija, String> kolonaOznaka;
	private TableColumn<Musterija, String> kolonaIme;
	private TableColumn<Musterija, String> kolonaPrezime;
	private TableColumn<Musterija, String> kolonaJMBG;
	private TableColumn<Musterija, Pol> kolonaPol;
	private TableColumn<Musterija, String> kolonaAdresa;
	private TableColumn<Musterija, String> kolonaBrojTelefona;
	private TableColumn<Musterija, String> kolonaKorisnickoIme;
	private TableColumn<Musterija, String> kolonaLozinka;
	private TableColumn<Musterija, Byte> kolonaPoeni;
	private ComboBox<Pol> polBox;
	private TextField tfOznaka, tfIme, tfPrezime, tfJMBG, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, tfPoeni;
	private Button dugmeSacuvaj;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"));
	private static ObservableList<Musterija> musterije = FXCollections.observableArrayList();
	
	public RadSaMusterijamaView() {
		super();
		kreirajTabeluMusterija();
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
		tfPoeni = new TextField();
		tfPoeni.setPromptText("Poeni");
		tfPoeni.setPrefWidth(SIRINA_TEKSTUALNIH);
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA_TEKSTUALNIH);
		
		//kreiranje combo boxa
		ObservableList<Pol> listaPolova = FXCollections.observableArrayList(Pol.MUSKI, Pol.ZENSKI);
		polBox = new ComboBox<>(listaPolova);
		polBox.setPromptText("Pol");
		polBox.setPrefWidth(SIRINA_TEKSTUALNIH - 20);
		ObservableList<Specijalizacija> listaSpecijalizacija = FXCollections.observableArrayList(Specijalizacija.AUTOELEKTRICAR, Specijalizacija.AUTOMEHANICAR, Specijalizacija.LIMAR, Specijalizacija.VULKANIZER);
		
		hb1.getChildren().addAll(tfIme, tfPrezime, tfJMBG, polBox, tfAdresa, tfBrojTelefona, tfKorisnickoIme, tfLozinka, tfPoeni, dugmeSacuvaj);
		
		tabela.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				meni.show(tabela, event.getScreenX(), event.getScreenY());
			}
		});
		
		hb2.getChildren().addAll(tabela);
		vb.getChildren().addAll(hb1,hb2);
		
		return vb;
	}
	
	
	private void kreirajTabeluMusterija() {
		
		//kreiranje kolona
		final int SIRINA_KOLONA = 110;
		kolonaOznaka = new TableColumn<Musterija, String>("Oznaka");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<Musterija, String>("oznaka"));
		kolonaOznaka.setMinWidth(SIRINA_KOLONA);
		kolonaIme = new TableColumn<Musterija, String>("Ime");
		kolonaIme.setCellValueFactory(new PropertyValueFactory<Musterija, String>("ime"));
		kolonaIme.setMinWidth(SIRINA_KOLONA);
		kolonaPrezime = new TableColumn<Musterija, String>("Prezime");
		kolonaPrezime.setCellValueFactory(new PropertyValueFactory<Musterija, String>("prezime"));
		kolonaPrezime.setMinWidth(SIRINA_KOLONA);
		kolonaJMBG = new TableColumn<Musterija, String>("JMBG");
		kolonaJMBG.setCellValueFactory(new PropertyValueFactory<Musterija, String>("JMBG"));
		kolonaJMBG.setMinWidth(SIRINA_KOLONA);
		kolonaPol = new TableColumn<Musterija,Pol>("Pol");
		kolonaPol.setCellValueFactory(new PropertyValueFactory<Musterija, Pol>("pol"));
		kolonaPol.setPrefWidth(SIRINA_KOLONA - 20);
		kolonaAdresa = new TableColumn<Musterija, String>("Adresa");
		kolonaAdresa.setCellValueFactory(new PropertyValueFactory<Musterija, String>("adresa"));
		kolonaAdresa.setMinWidth(SIRINA_KOLONA);
		kolonaBrojTelefona = new TableColumn<Musterija, String>("Broj Telefona");
		kolonaBrojTelefona.setCellValueFactory(new PropertyValueFactory<Musterija, String>("brojTelefona"));
		kolonaBrojTelefona.setMinWidth(SIRINA_KOLONA);
		kolonaKorisnickoIme = new TableColumn<Musterija, String>("Korisnicko Ime");
		kolonaKorisnickoIme.setCellValueFactory(new PropertyValueFactory<Musterija, String>("korisnickoIme"));
		kolonaKorisnickoIme.setMinWidth(SIRINA_KOLONA);
		kolonaLozinka = new TableColumn<Musterija, String>("Lozinka");
		kolonaLozinka.setCellValueFactory(new PropertyValueFactory<Musterija, String>("lozinka"));
		kolonaLozinka.setMinWidth(SIRINA_KOLONA);
		kolonaPoeni = new TableColumn<Musterija, Byte>("Poeni");
		kolonaPoeni.setCellValueFactory(new PropertyValueFactory<Musterija, Byte>("brojPoena"));
		kolonaPoeni.setMinWidth(SIRINA_KOLONA);
		
		tabela.getColumns().addAll(kolonaIme, kolonaPrezime,
				kolonaJMBG, kolonaPol, kolonaAdresa, kolonaBrojTelefona, kolonaKorisnickoIme,
				kolonaLozinka, kolonaPoeni, kolonaOznaka);
		
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tabela.setItems(RadSaMusterijamaView.musterije);
	}
	
	
	public void popuniTabelu(ArrayList<Musterija> musterije) {
		RadSaMusterijamaView.musterije = FXCollections.observableArrayList(musterije);
		tabela.setItems(RadSaMusterijamaView.musterije);
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
		tfPoeni.clear();
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

	public TableView<Musterija> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<Musterija> tabela) {
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


	public TextField getTfPoeni() {
		return tfPoeni;
	}


	public void setTfPoeni(TextField tfPoeni) {
		this.tfPoeni = tfPoeni;
	}
	
	
	
}
