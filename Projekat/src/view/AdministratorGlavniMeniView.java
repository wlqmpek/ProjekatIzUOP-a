package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdministratorGlavniMeniView extends Stage {


	public Button radSaAdministratorima = new Button("Rad sa Administratorima");
	public Button radSaServiserima = new Button("Rad sa Serviserima");
	public Button radSaMusterijama = new Button("Rad sa Musterijama");
	public Button radSaServisima = new Button("Rad sa Servisima");
	public Button radSaAutomobilima = new Button("Rad sa Automobilima");
	public Button radSaServisnimKnjizicama = new Button("Rad sa Servisnim Knjizicama");
	public Button radSaDelovima = new Button("Rad sa Delovima");
	public final int VELICINA_DUGMETA = 180;
	
	public AdministratorGlavniMeniView() {
		super();
		VBox vBox = new VBox();
		kreirajRaspored(vBox);
		Scene scene = new Scene(vBox,300,250);
		
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.setScene(scene);
		this.setResizable(false);
		this.show();
	}

	private void kreirajRaspored(VBox vBox) {
		vBox.getChildren().addAll(radSaAdministratorima, radSaServiserima, radSaMusterijama, radSaServisima, radSaAutomobilima, radSaServisnimKnjizicama, radSaDelovima);
		for(int i = 0; i < vBox.getChildren().size(); i++) {
			if(vBox.getChildren().get(i) instanceof Button) {
				((Button)vBox.getChildren().get(i)).setMinWidth(VELICINA_DUGMETA);
			}
		}
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
	}
	
	public void dodajFunkcionalnostDugmetuRadSaServiserima(EventHandler<ActionEvent> event) {
		radSaServiserima.setOnAction(event);
	}
	
	
}


