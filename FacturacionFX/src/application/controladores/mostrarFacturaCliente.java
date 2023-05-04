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

public class mostrarFacturaCliente implements Initializable {
	
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML 
	private Label resultadoF;
	
	@FXML 
	private TextField tfDNI;
	
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
	
	public void facturaCliente() {
		String dni = tfDNI.getText();
		String resultado = comercio.buscaFacturaDniCliente(dni);
		resultadoF.setText(resultado);
	}
	
	public void abrirFacturas(ActionEvent event) throws IOException {
		String dni = tfDNI.getText();
		String resultado = comercio.buscaFacturaDniCliente(dni);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/facturasCliente.fxml"));
        Parent root = loader.load();
        facturasCliente controller = loader.getController();
        controller.muestraFacturaCliente(resultado);
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        Image icono = new Image("/application/media/factura.png");
		stage.getIcons().add(icono);
        stage.show();
        tfDNI.setText("");
    }
	
	
}
