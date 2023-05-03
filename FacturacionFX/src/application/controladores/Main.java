package application.controladores;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import application.modelo.Comercio;

public class Main extends Application {
	private Comercio comercio;
	@Override
    public void start(Stage primaryStage) throws IOException {
		
		// Crear una instancia de Comercio
		comercio = new Comercio();

        // Cargar la vista de la pantalla principal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PrimeraPantalla.fxml"));
        Parent root = loader.load();

        // Obtener el controlador de la pantalla principal y establecer la instancia de Comercio
        PantallaPrincipal controller = loader.getController();
        controller.setComercio(comercio);

        // Configurar la escena y mostrarla
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Programa Comercio");
        primaryStage.show();
    }
   
    public static void main(String[] args) {
    	
        launch(args);
    }
   
}
