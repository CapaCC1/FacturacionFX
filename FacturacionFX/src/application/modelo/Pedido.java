package application.modelo;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pedido {
	private int cantidad;
	private LinkedHashMap<Producto,Integer> productos;
	private boolean pedidoAbierto;

	public Pedido(int cantidad, LinkedHashMap<Producto, Integer> productos) {
		this.cantidad = cantidad;
		this.productos = new LinkedHashMap<Producto, Integer>();
	}
	
	public Pedido(int cantidad) {
		this.cantidad = 0;
		this.productos = new LinkedHashMap<Producto, Integer>();
		this.pedidoAbierto = true;
	}
	
	public Pedido() {
		this.cantidad = 0;
		this.productos = new LinkedHashMap<Producto, Integer>();
		this.pedidoAbierto = true;
	}
	
	public LinkedHashMap<Producto, Integer> getProductos() {
		return productos;
	}

	public void setProductos(LinkedHashMap<Producto, Integer> productos) {
		this.productos = productos;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public boolean getEstadoPedido() {
		return pedidoAbierto;
	}

	public void setEstadoPedido(boolean pedidoAbierto) {
		this.pedidoAbierto = pedidoAbierto;
	}

	public void agregarProducto(String nombreProducto, int cantidad, double precio) {
		Producto nuevoProducto = new Producto(nombreProducto,precio);
		productos.put(nuevoProducto, cantidad);
		aumentarCantidadProducto(nombreProducto, cantidad);
	    }
	
	public void aumentarCantidadProducto(String nombre, int cantidad) {
		int cantidadActual = 0;
		Producto producto = buscarProductoPedido(nombre);
	    for (Producto pc : productos.keySet()) {
	        if(pc.equals(producto)) {
	        	cantidadActual = getCantidad();
	        	setCantidad(cantidadActual + cantidad);
	        }
	    }
	}
	
	public Producto buscarProductoPedido(String nombre) {
	    for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
	        if (entry.getKey().getNombre().equals(nombre)) {
	            return entry.getKey();
	        }
	    }
	    return null;
	}
	
	public Producto getProducto() {
		Producto producto = null;
		for (Producto p : productos.keySet()) {
			producto = p;
		}
		return producto;
	}
	
	public int getCantidadPedida(Producto producto) {
	    for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
	        if (entry.getKey().equals(producto)) {
	            return entry.getValue();
	        }
	    }
	    return 0;
	}
	
	public boolean existeProductoPedido(String nombre) {
		
		Producto producto = buscarProductoPedido(nombre);
		    for (Producto p : productos.keySet()) {
		        if (p.equals(producto)) {
		            return true;
		        }
		    }
		    return false;
		}
	
	public void cerrarPedido() {
		setEstadoPedido(false);
	}
	
	@Override
	public String toString() {
		return "Producto: " + productos + "\nCantidad: " + cantidad;
	}
}