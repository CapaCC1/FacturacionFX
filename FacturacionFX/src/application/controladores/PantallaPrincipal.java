package application.controladores;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import application.modelo.Comercio;

public class PantallaPrincipal implements Initializable {

	   @FXML
	   private Button botonRCliente;
	 
	   @FXML
	   private Button botonIProducto;
	   
	   @FXML
	   private Button botonRProducto;
	   
	   @FXML
	   private Button botonGPedido;
	   
	   @FXML
	   private Button botonGFactura;
	   
	   @FXML
	   private Button botonVFactura;
	   
	   @FXML
	   private Button botonVFacturaCliente;
	   
	   @FXML
	   private Button botonGArchivo;
	   
	   public Comercio comercio;
	   
	   public void setComercio(Comercio comercio) {
	        this.comercio = comercio;
	    }
	   
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		 
	   }
	   
	   public void registrarCliente(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaCliente.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Agregar Cliente");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void incorporarProducto(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaProducto.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Agregar Producto");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonIProducto.getScene().getWindow();
		   stageActual.hide();
		   
		   
	   }
	   
	   public void reponerProducto(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaReponerProducto.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Reponer Producto");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRProducto.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void generarPedido(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaGPedido.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Generar Pedido");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void generarFactura(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaGFactura.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Generar Factura");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void visualizarFactura(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaVFactura.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Visualizar Factura");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void visualizarFacturaCliente(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaFCliente.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Visualizar Facturas Cliente");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }
	   
	   public void generarArchivo(ActionEvent Event) throws IOException {
		   FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/vistas/PantallaGArchivo.fxml"));
		   Parent root = loader.load();
		   Scene scene = new Scene(root);
		   Stage stage = new Stage();
		   stage.setScene(scene);
		   stage.setTitle("Generar Archivo de Factura");
		   Image icono = new Image("/application/media/factura.png");
		   stage.getIcons().add(icono);
		   stage.show();
		   Stage stageActual = (Stage)botonRCliente.getScene().getWindow();
		   stageActual.hide();
	   }

	
}
