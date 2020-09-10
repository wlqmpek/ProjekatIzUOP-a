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
import javafx.scene.control.Button;
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

public class MusterijaGlavniMeniView extends Stage {
	private TableView<Automobil> tabela = new TableView<Automobil>();
	private TableColumn<Automobil, String> kolonaOznaka;
	private TableColumn<Automobil, Marka> kolonaMarka;
	private TableColumn<Automobil, Model> kolonaModel;
	private TableColumn<Automobil, Short> kolonaGodina;
	private TableColumn<Automobil, Short> kolonaZapremina;
	private TableColumn<Automobil, Short> kolonaSnaga;
	private TableColumn<Automobil, Gorivo> kolonaGorivo;
	
	private Button dugmeSacuvaj;
	private TextField tfKratakOpis;
	private Button dugmeOznaka, dugmeMarka, dugmeModel, dugmeGodina, dugmeZapremina, dugmeSnaga, dugmeGorivo;
	private static ObservableList<Automobil> automobili = FXCollections.observableArrayList();
	private ContextMenu meni = new ContextMenu(new MenuItem("Zakazi Servis"));
	
	public MusterijaGlavniMeniView() {
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
		kolonaOznaka.setPrefWidth(SIRINA_KOLONA);
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
		
		tabela.getColumns().addAll(kolonaMarka, kolonaModel, kolonaGodina, kolonaZapremina, kolonaSnaga, kolonaGorivo);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(automobili);
	}
	
	private VBox kreirajRaspored() {
		VBox vb = new VBox();
		HBox hb1 = new HBox();
		HBox hb2 = new HBox();
		
		//kreiranje dugmeta
		final int SIRINA = 110;
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(SIRINA);
		dugmeOznaka = new Button("Oznaka");
		dugmeOznaka.setPrefWidth(SIRINA);
		dugmeMarka = new Button("Marka");
		dugmeMarka.setPrefWidth(SIRINA);
		dugmeModel = new Button("Model");
		dugmeModel.setPrefWidth(SIRINA);
		dugmeGodina = new Button("Godina");
		dugmeGodina.setPrefWidth(SIRINA);
		dugmeZapremina = new Button("Zapremina");
		dugmeZapremina.setPrefWidth(SIRINA);
		dugmeSnaga = new Button("Snaga");
		dugmeSnaga.setPrefWidth(SIRINA);
		dugmeGorivo = new Button("Gorivo");
		dugmeGorivo.setPrefWidth(SIRINA);
		tfKratakOpis = new TextField("Kratak opis");
		tfKratakOpis.setEditable(false);
		tfKratakOpis.setPrefWidth(SIRINA);
		
		hb1.getChildren().addAll(dugmeMarka, dugmeModel, dugmeGodina, dugmeZapremina, dugmeSnaga, dugmeGorivo, tfKratakOpis, dugmeSacuvaj);
		
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
		MusterijaGlavniMeniView.automobili = FXCollections.observableArrayList(automobili);
		tabela.setItems(MusterijaGlavniMeniView.automobili);
	}
	
	public void resetujPolja() {
		dugmeOznaka.setText("Oznaka");
		dugmeMarka.setText("Marka");
		dugmeModel.setText("Model");
		dugmeGodina.setText("Godina");
		dugmeZapremina.setText("Zapremina");
		dugmeSnaga.setText("Snaga");
		dugmeGodina.setText("Gorivo");
		tfKratakOpis.setText("Kratak opis");
		tfKratakOpis.setPromptText("Kratak opis");
		tfKratakOpis.setEditable(false);
	}
	
	public void izbaciPorukuOGresci(String poruka) {
		new Alert(Alert.AlertType.ERROR, poruka).showAndWait();
	}
	
	public void dodeliFunkcionalnostOpcijiZakaziServis(EventHandler<ActionEvent> event) {
		meni.getItems().get(0).setOnAction(event);
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

	public TextField getTfKratakOpis() {
		return tfKratakOpis;
	}

	public void setTfKratakOpis(TextField tfKratakOpis) {
		this.tfKratakOpis = tfKratakOpis;
	}

	public ContextMenu getMeni() {
		return meni;
	}

	public void setMeni(ContextMenu meni) {
		this.meni = meni;
	}

	public Button getDugmeSacuvaj() {
		return dugmeSacuvaj;
	}

	public void setDugmeSacuvaj(Button dugmeSacuvaj) {
		this.dugmeSacuvaj = dugmeSacuvaj;
	}

	public Button getDugmeOznaka() {
		return dugmeOznaka;
	}

	public void setDugmeOznaka(Button dugmeOznaka) {
		this.dugmeOznaka = dugmeOznaka;
	}

	public Button getDugmeMarka() {
		return dugmeMarka;
	}

	public void setDugmeMarka(Button dugmeMarka) {
		this.dugmeMarka = dugmeMarka;
	}

	public Button getDugmeModel() {
		return dugmeModel;
	}

	public void setDugmeModel(Button dugmeModel) {
		this.dugmeModel = dugmeModel;
	}

	public Button getDugmeGodina() {
		return dugmeGodina;
	}

	public void setDugmeGodina(Button dugmeGodina) {
		this.dugmeGodina = dugmeGodina;
	}

	public Button getDugmeZapremina() {
		return dugmeZapremina;
	}

	public void setDugmeZapremina(Button dugmeZapremina) {
		this.dugmeZapremina = dugmeZapremina;
	}

	public Button getDugmeSnaga() {
		return dugmeSnaga;
	}

	public void setDugmeSnaga(Button dugmeSnaga) {
		this.dugmeSnaga = dugmeSnaga;
	}

	public Button getDugmeGorivo() {
		return dugmeGorivo;
	}

	public void setDugmeGorivo(Button dugmeGorivo) {
		this.dugmeGorivo = dugmeGorivo;
	}
	
	
}
