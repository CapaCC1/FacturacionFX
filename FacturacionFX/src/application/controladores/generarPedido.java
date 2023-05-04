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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.modelo.Comercio;

public class generarPedido implements Initializable {
	
	@FXML
	private Button botonVolver;
	
	@FXML
	private Button botonEnviar;
	
	@FXML
	private Button botonMostrarProductos;
	
	@FXML
	private TextField tfDNI;
	
	@FXML
	private TextField tfCantidadRP;
	
	@FXML 
	private Label resultadoP;
	
	@FXML 
	private Label cantidadesProductos;
	
	@FXML 
	private Label productosDisponibles;
	
	
	@FXML
	private ChoiceBox<String> deslizableProductos;
	
	private Comercio comercio = Comercio.getInstancia();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		for (String productos : comercio.getProductos()) {
			deslizableProductos.getItems().add(productos);
		}
		deslizableProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		    mostrarDatosProducto();
		});
	}
	
	public void mostrarDatosProducto() {
		String producto = deslizableProductos.getValue();
		String datosProducto = getCantidades(producto);
		resultadoP.setText(datosProducto);
	}
	
	
	public String getCantidades(String nombre) {
		String resultado = "";
		resultado = comercio.getCantidadPrecio(nombre);
		return resultado;
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
	
	public void mostrarProductos() {
		productosDisponibles.setText(comercio.mostrarProductos());
	}

	public void generaPedido(ActionEvent Event) {
		String dni = tfDNI.getText();
		String nombreProducto = deslizableProductos.getValue();
		
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
		tfCantidadRP.setText("");
		
	}
	
}
