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

public class RadSaServisnimKnjizicamaView extends Stage {

	//kolone
	private TableView<ServisnaKnjizica> tabela = new TableView<ServisnaKnjizica>();
	private TableColumn<ServisnaKnjizica, String> kolonaOznaka;
	//txtFildovi
	private Button dugmeSacuvaj;
	private ContextMenu meni = new ContextMenu(new MenuItem("Izbrisi"), new MenuItem("Izmeni"));
	private static ObservableList<ServisnaKnjizica> servisneKnjizice = FXCollections.observableArrayList();
	
	public RadSaServisnimKnjizicamaView() {
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
		
		dugmeSacuvaj = new Button("Sacuvaj");
		dugmeSacuvaj.setPrefWidth(220);
		
		hb1.getChildren().addAll(dugmeSacuvaj);
		
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
		
		kolonaOznaka = new TableColumn<ServisnaKnjizica, String>("Oznaka ServisneKnjizice");
		kolonaOznaka.setCellValueFactory(new PropertyValueFactory<ServisnaKnjizica, String>("oznaka"));
		kolonaOznaka.setMinWidth(220);
		

		tabela.getColumns().add(kolonaOznaka);
		tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);	
		tabela.setItems(servisneKnjizice);
	}
	
	public void popuniTabelu(ArrayList<ServisnaKnjizica> servisneKnjizice) {
		RadSaServisnimKnjizicamaView.servisneKnjizice = FXCollections.observableArrayList(servisneKnjizice);
		tabela.setItems(RadSaServisnimKnjizicamaView.servisneKnjizice);
	}
	
	public void resetujPolja() {
		//ovo ne radi nista
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

	public TableView<ServisnaKnjizica> getTabela() {
		return tabela;
	}

	public void setTabela(TableView<ServisnaKnjizica> tabela) {
		this.tabela = tabela;
	}
	
}
