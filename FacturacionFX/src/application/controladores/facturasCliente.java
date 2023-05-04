package application.controladores;

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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class facturasCliente implements Initializable {

	@FXML
	private TextArea resultadoF;
	
	@FXML
	private Button botonVolver;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void volverPrincipal(ActionEvent Event) throws IOException {
	 	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PrimeraPantalla.fxml"));
	    Parent root = loader.load();
	    Scene scene = new Scene(root);
	    Stage stageActual = (Stage) botonVolver.getScene().getWindow(); // obtener la instancia actual de Stage
	    stageActual.close(); // cerrar la pantalla actual
	    stageActual.setScene(scene); // cargar la pantalla principal en la instancia actual de Stage
	    stageActual.setTitle("Agregar Producto");
	    stageActual.show();
	}
	
	
	public void muestraFacturaCliente(String texto) throws IOException {
        resultadoF.setText(texto);
    }
	
}
