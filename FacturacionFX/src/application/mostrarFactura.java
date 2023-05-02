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

public class mostrarFactura implements Initializable {
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField numeroFac;
	
	@FXML 
	private Label resultadoP;
	
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
	
	public void abrirFacturaGenerada() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaVFactura.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stageActual = (Stage) botonVolver.getScene().getWindow(); // obtener la instancia actual de Stage
        stageActual.close(); // cerrar la pantalla actual
        stageActual.setScene(scene); // cargar la pantalla principal en la instancia actual de Stage
        stageActual.setTitle("Factura Generada");
        stageActual.show();
        numeroFac.setText("");
    }

    public void muestraFactura() throws IOException {
        String numeroFactura = numeroFac.getText();
        String resultado = comercio.imprimirFactura(numeroFactura);
        resultadoP.setText(resultado);
    }
}
