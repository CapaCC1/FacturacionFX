package application.modelo;
import java.util.LinkedHashMap;

public class Almacen {
	private LinkedHashMap<String, Producto> productos;
	private LinkedHashMap<Integer, Producto> stock;
	
	public Almacen(LinkedHashMap<Integer, Producto> stock) {
		this.stock = new LinkedHashMap<Integer, Producto>();
	}
	
	public Almacen() {
		this.productos = new LinkedHashMap<String, Producto>();
		this.stock = new LinkedHashMap<Integer, Producto>();
		agregarProductos("Fanta", 1.20, 10);
		agregarProductos("Coca", 1.30, 8);
		agregarProductos("Pepsi", 1.10, 6);
		agregarProductos("Smint", 1.40, 12);
		
	}
	
	public LinkedHashMap<Integer, Producto> getStock() {
		return stock;
	}

	public void setStock(LinkedHashMap<Integer, Producto> stock) {
		this.stock = stock;
	}

	public LinkedHashMap<String, Producto> getProductos() {
		return productos;
	}

	public void setProductos(LinkedHashMap<String, Producto> productos) {
		this.productos = productos;
	}
	
	public boolean existeProducto(String nombre) {
		
		boolean existe = false;
		if(productos.containsKey(nombre)) {
			existe = true;
		}
		return existe;
	}
	
	public int consultaStock(String nombre) {
		int resultado = -1;
		Producto producto = buscaProducto(nombre);
			for (Integer cantidad : stock.keySet()) {
				if(stock.get(cantidad).equals(producto)) {
					resultado = cantidad;
				}
			}
		return resultado;
	}
	
	public int agregarProductos(String nombre, double precio, int cantidad) {
		
		int codigo = -1;
		Producto producto = buscaProducto(nombre);
		if(producto == null) {
			producto = new Producto(nombre,precio);
			productos.put(nombre, producto);
			stock.put(cantidad, producto);
			codigo = 0;
		}else {
			codigo = 1;
		}
		return codigo;
	}
	
	public Producto buscaProducto(String nombre) {
		
		Producto producto = productos.get(nombre);
		return producto;
	}
	
	public void reponerStock(String nombreProducto, int cantidad) {
	    Producto producto = buscaProducto(nombreProducto);
	    if (producto != null) {
	        int cantidadActual = 0;
	        for (Integer cant : stock.keySet()) {
	            if (stock.get(cant).equals(producto)) {
	                cantidadActual = cant;
	                break;
	            }
	        }
	        stock.put(cantidadActual + cantidad, producto);
	    }
	}
	
	public void actualizarStock(String nombreProducto, int cantidad) {
	    Producto producto = buscaProducto(nombreProducto);
	    if (producto != null) {
	        int cantidadActual = 0;
	        for (Integer cant : stock.keySet()) {
	            if (stock.get(cant).equals(producto)) {
	                cantidadActual = cant;
	                break;
	            }
	        }
	        stock.remove(cantidadActual,producto);
	        stock.put(cantidadActual - cantidad, producto);
	    }
	}
	
	public String getCantidadYPrecio(String nombre) {
			String resultado = "";
			Producto producto = buscaProducto(nombre);
				for (Integer cantidad : stock.keySet()) {
					if(stock.get(cantidad).equals(producto)) {
						resultado = producto.getNombre()+ ":  " + " Precio: " + producto.getPrecio() + "�  " + " Stock: " + cantidad.toString() + " Uds";
					}
				}
			return resultado;
		}
	
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Producto producto : productos.values()) {
	        sb.append(producto.getNombre());
	    }
	    return sb.toString();
	}
	
	
}