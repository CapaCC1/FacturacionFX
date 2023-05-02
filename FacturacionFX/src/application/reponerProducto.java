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

public class reponerProducto implements Initializable{
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField tfNombreP;
	
	@FXML
	private TextField tfCantidadRP;
	
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
	
	public void reponProducto(ActionEvent Event) {
		String nombre = tfNombreP.getText();
		int cantidad = Integer.parseInt(tfCantidadRP.getText());
		
		int codigo = comercio.actualizarStock(nombre, cantidad);
		
		if(codigo == 0) {
			resultadoP.setText("Stock Actualizado");
			System.out.println("\nStock Actualizado!");
		}else if(codigo == 1) {
			resultadoP.setText("El Producto No Existe!");
			System.out.println("\nERROR El Producto No Existe!");
		}
		tfNombreP.setText("");
		tfCantidadRP.setText("");
		
	}
}
