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

public class agregarProducto implements Initializable{

	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField tfNombreP;
	
	@FXML
	private TextField tfPrecio;
	
	@FXML
	private TextField tfExistencias;
	
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
	
	public void incorporarProducto(ActionEvent Event) {
		String nombre = tfNombreP.getText();
		double precio = Double.parseDouble(tfPrecio.getText());
		int existencias = Integer.parseInt(tfExistencias.getText());
		
		int codigo = comercio.incorporarProducto(nombre, precio, existencias);
		if(codigo == 0) {
			resultadoP.setText("Producto Incorporado con EXITO!");
			System.out.println("\nProducto Incorporado con EXITO!");
			
		}else if(codigo == 1) {
			resultadoP.setText("ERROR el Producto Ya Existe!");
			System.out.println("\nERROR el Producto Ya Existe!");
		}
		 tfNombreP.setText("");
		 tfPrecio.setText("");
		 tfExistencias.setText("");

	}
	
}
