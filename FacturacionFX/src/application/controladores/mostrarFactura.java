package application.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import application.modelo.Comercio;

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
	 	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PrimeraPantalla.fxml"));
	    Parent root = loader.load();
	    Scene scene = new Scene(root);
	    Stage stageActual = (Stage) botonVolver.getScene().getWindow(); // obtener la instancia actual de Stage
	    stageActual.close(); // cerrar la pantalla actual
	    stageActual.setScene(scene); // cargar la pantalla principal en la instancia actual de Stage
	    stageActual.setTitle("Agregar Producto");
	    stageActual.show();
	}
	
	public void abrirFacturaGenerada(ActionEvent event) throws IOException {
		String numeroFactura = numeroFac.getText();
		String resultado = comercio.imprimirFactura(numeroFactura);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/facturaMostrada.fxml"));
        Parent root = loader.load();
        facturaMostrada controller = loader.getController();
        controller.muestraFactura(resultado);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        Image icono = new Image("/application/media/factura.png");
		stage.getIcons().add(icono);
        stage.show();
        numeroFac.setText("");
    }
}
