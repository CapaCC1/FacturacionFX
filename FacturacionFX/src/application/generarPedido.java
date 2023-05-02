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

public class generarPedido implements Initializable {
	
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private TextField tfDNI;
	
	@FXML
	private TextField tfNombreP;
	
	@FXML
	private TextField tfCantidadRP;
	
	@FXML 
	private Label resultadoP;
	
	@FXML 
	private Label productosDisponibles;
	
	private Comercio comercio = Comercio.getInstancia();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		mostrarProductos();
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
	
	public void mostrarProductos() {
		productosDisponibles.setText(comercio.mostrarProductos());
	}

	public void generaPedido(ActionEvent Event) {
		String dni = tfDNI.getText();
		System.out.println(comercio.mostrarProductos());
		String nombreProducto = tfNombreP.getText();
		int cantidad = Integer.parseInt(tfCantidadRP.getText());
		
		int codigo = comercio.generarPedido(dni, nombreProducto, cantidad);
		
		if(codigo == 0) {
			 resultadoP.setText("Pedido Generado Correctamente!");
			 System.out.println("\nPedido generado correctamente.");
		}else if(codigo == 1) {
			resultadoP.setText("El Producto NO Existe o No Existe Stock SUFICIENTE!");
			System.out.println("\nEl Producto NO Existe o No Existe Stock SUFICIENTE!");
		}else if(codigo == 2) {
			resultadoP.setText("No existe un cliente con ese DNI.");
			System.out.println("\nNo existe un cliente con ese DNI.");
		}
		tfDNI.setText("");
		tfNombreP.setText("");
		tfCantidadRP.setText("");
		
	}
	
}
