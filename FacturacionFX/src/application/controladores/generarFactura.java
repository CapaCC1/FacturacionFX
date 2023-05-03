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
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import application.modelo.Comercio;


public class generarFactura implements Initializable{
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonCopiar;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField tfDNI;
	
	@FXML 
	private Label resultadoP;
	
	@FXML
	private TextArea resultadoTX;
	
	@FXML
	private Label resultadoNotificacion;
	
	private Comercio comercio = Comercio.getInstancia();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		botonCopiar.setOnAction(event -> {
		    final Clipboard clipboard = Clipboard.getSystemClipboard();
		    final ClipboardContent content = new ClipboardContent();
		    content.putString(resultadoTX.getText());
		    clipboard.setContent(content);
		    resultadoNotificacion.setText("Copiado al Portapapeles");
		    
		});
		
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
	
	public void generaFactura(ActionEvent Event) {
		String dni = tfDNI.getText();
		String resultado = comercio.generarFacturaCliente(dni);
		resultadoP.setText("Factura Generado: " + resultado);
		resultadoTX.setText(resultado);
		tfDNI.setText("");
	}
}
