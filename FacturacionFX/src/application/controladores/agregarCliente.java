package application.controladores;

import javafx.scene.control.Label;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.modelo.Comercio;

public class agregarCliente implements Initializable {
	
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfApellidos;
	
	@FXML
	private TextField tfDNI;
	
	@FXML
	private TextField tfDescuento;
	
	@FXML 
	private Label resultadoC;	
	
	private Comercio comercio = Comercio.getInstancia();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	 public void volverPrincipal(ActionEvent Event) throws IOException {
		 	
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PrimeraPantalla.fxml"));
		    Parent root = loader.load();
		    Scene scene = new Scene(root);
		    Stage stageActual = (Stage) botonVolver.getScene().getWindow(); // obtener la instancia actual de Stage
		    stageActual.close(); // cerrar la pantalla actual
		    stageActual.setScene(scene); // cargar la pantalla principal en la instancia actual de Stage
		    stageActual.setTitle("Agregar Cliente");
		    stageActual.show();
		}
	 
	 public void agregaCliente(ActionEvent Evenet) {
		 
		 String nombre = tfNombre.getText();
		 String apellidos = tfApellidos.getText();
		 String DNI = tfDNI.getText();
		 int descuento = Integer.parseInt(tfDescuento.getText());
		 
		 int codigo = comercio.agregarCliente(nombre, apellidos, DNI, descuento);
		 
		 if(codigo == 0) {
			 resultadoC.setText("Cliente Agregado con exito");
			 System.out.println("\nCliente Agregado con Exito!");
			}else if(codigo == 1) {
				resultadoC.setText("ERROR el Cliente Ya Existe!");
				System.out.println("\nERROR el Cliente Ya Existe!");
			}
		 
		 tfNombre.setText("");
		 tfApellidos.setText("");
		 tfDNI.setText("");
		 tfDescuento.setText("");
	 }
}
