package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Comercio;

public class generarArchivo implements Initializable {
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML 
	private Label resultadoF;
	
	@FXML 
	private TextField numeroFactura;
	
	private Comercio comercio = Comercio.getInstancia();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	}
	
	public void volverPrincipal(ActionEvent Event) throws IOException {
	 	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PrimeraPantalla.fxml"));
	    Parent root = loader.load();
	    Scene scene = new Scene(root);
	    Stage stageActual = (Stage) botonVolver.getScene().getWindow(); // obtener la instancia actual de Stage
	    stageActual.close(); // cerrar la pantalla actual
	    stageActual.setScene(scene); // cargar la pantalla principal en la instancia actual de Stage
	    stageActual.setTitle("Agregar Producto");
	    stageActual.show();
	}
	
	public void generaArchivo() throws IOException {
		String numFactura = numeroFactura.getText();
		String resultado = comercio.generarFacturaArchivo(numFactura);
		resultadoF.setText(resultado);
	}
}
