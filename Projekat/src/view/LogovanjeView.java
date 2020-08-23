package view;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LogovanjeView extends Application {

	

	TextField korisnickoIme = new TextField();
	TextField lozinka = new TextField();
	Button dugme = new Button("Uloguj se");
	public Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			VBox vBox = new VBox();
			kreirajRaspored(vBox);
			Scene scene = new Scene(vBox,300,150);
			
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void kreirajRaspored(VBox vBox) {
		Label nalepnica1 = new Label("Molim vas unesite svoje korisnicko ime i lozinku!");
		Label nalepnica2 = new Label("Korisnicko ime: ");
		Label nalepnica3 = new Label("Lozinka: ");
		nalepnica3.setMinWidth(85);
		nalepnica3.setAlignment(Pos.CENTER_RIGHT);
		
		HBox hb1 = new HBox();
		hb1.setAlignment(Pos.CENTER);
		hb1.getChildren().addAll(nalepnica2, korisnickoIme);
		
		HBox hb2 = new HBox();
		hb2.setAlignment(Pos.CENTER);
		hb2.getChildren().addAll(nalepnica3, lozinka);
		
		vBox.setAlignment(Pos.CENTER);
		vBox.setSpacing(10);
		vBox.getChildren().addAll(nalepnica1, hb1, hb2, dugme);
		
	}
	
	public void dodajFunkcionalnostDugmetuOK(EventHandler<ActionEvent> event) {
		dugme.setOnAction(event);
	}
	
	public void izbaciPorukuOGresci(String poruka) {
		new Alert(Alert.AlertType.ERROR, poruka).showAndWait();
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme.getText();
	}
	
	public String getLozinka() {
		return lozinka.getText();
	}
	
}
