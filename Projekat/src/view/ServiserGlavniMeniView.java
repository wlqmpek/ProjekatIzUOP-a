package view;

import enumi.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Servis;

public class ServiserGlavniMeniView extends Stage{

	private TableView<Servis> tabela = new TableView<Servis>();
	private TableColumn<Servis, String> kolonaOznaka;
	private TableColumn<Servis, String> kolonaServisnaKnjizica;
	private TableColumn<Servis, String> kolonaDatum;
	private TableColumn<Servis, String> kolonaOpis;
	private TableColumn<Servis, Status> kolonaStatus;
	private TableColumn<Servis, Double> kolonaCena;
	private TextField tfDatum, tfOpis;
	
	public void dodajFunkcionalnost(EventHandler<ActionEvent> event) {
		
		
		
	}
}
