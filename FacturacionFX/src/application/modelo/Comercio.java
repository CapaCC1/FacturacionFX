package application.modelo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Comercio {
	private Almacen almacen;
	private HashMap<String, Cliente> clientes;
	private static Comercio instancia;
	
	public Comercio(Almacen almacen, HashMap<String, Cliente> clientes) {
		this.almacen = almacen;
		this.clientes = new HashMap<String, Cliente>();
	}
	
	public Comercio() {
		this.almacen = new Almacen();
		this.clientes = new HashMap<String, Cliente>();
		Cliente cliente1 = new Cliente("1","Paco","Gomez",5);
		Cliente cliente2 = new Cliente("2","Jesus","Lopez",5);
		Cliente cliente3 = new Cliente("3","Fran","Guti",5);
		Cliente cliente4 = new Cliente("4","Lucas","Marco",5);
		clientes.put("1", cliente1);
		clientes.put("2", cliente2);
		clientes.put("3", cliente3);
		clientes.put("4", cliente4);
	}

	public HashMap<String, Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(HashMap<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	public Almacen getAlmacen() {
		return almacen;
	}
	
	public static Comercio getInstancia() {
        if (instancia == null) {
            instancia = new Comercio();
        }
        return instancia;
    }

	public static void setInstancia(Comercio instancia) {
		Comercio.instancia = instancia;
	}

	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	
	public boolean clienteExiste(String dni) {
		boolean resultado = false;
		if(clientes.containsKey(dni)) {
			resultado = true;;
		}
		return resultado;
	}
	
	public Cliente buscaCliente(String dni) {
		
		Cliente cliente = clientes.get(dni);
		
		return cliente;
	}
	
	public int agregarCliente(String nombre,String apellidos,String dni, int descuento) {
		int codigo = -1;
		Cliente cliente = null;
		
		if(!clienteExiste(dni)) {
			cliente = new Cliente(dni,nombre,apellidos,descuento);
			clientes.put(dni, cliente);
			codigo = 0;
		} else {
			codigo = 1;
		}
		return codigo;
	}
	
	public int incorporarProducto(String nombre,double precio,int stock) {
		int codigo = -1;
		if(!almacen.existeProducto(nombre)) {
			almacen.agregarProductos(nombre, precio,stock);
			codigo = 0;
		}else {
			codigo = 1;
		}
		
		return codigo;
	}
	
	public String mostrarProductos() {
		return almacen.toString();
	}
	
	public int actualizarStock(String nombre, int cantidad) {
		int codigo = -1;
		if(almacen.existeProducto(nombre)) {
			almacen.reponerStock(nombre, cantidad);
			codigo = 0;
		}else if(!almacen.existeProducto(nombre)){
			codigo = 1;
		}else if(almacen == null) {
			codigo = 2;
		}
		return codigo;
	}
	
	public int generarPedido(String dniCliente, String nombreProducto, int cantidad) {
    int codigo = -1;
    Cliente cliente = buscaCliente(dniCliente);
    Producto producto = almacen.buscaProducto(nombreProducto);
    if (cliente != null) {
            int stockActual = almacen.consultaStock(nombreProducto);
            if (stockActual >= cantidad) {
                cliente.agregarNuevoPedido(nombreProducto, cantidad, producto.getPrecio());
                almacen.actualizarStock(nombreProducto, cantidad);
                codigo = 0;
            } else {
                codigo = 1;
            }
    } else {
        codigo = 2;
    }
    return codigo;
	}	
	
	public String generarFacturaCliente(String dni) {
		String resultado = "";
		Cliente cliente = buscaCliente(dni);
		if(cliente == null) {
			resultado += "El Cliente NO Existe!";
		}else {
			resultado += cliente.generarFactura(dni);
		}
		return resultado;
	}
		
	public String imprimirFactura(String numeroFactura) {
		String tabla = "";
	   for(Cliente cliente : clientes.values()) {
		   tabla += cliente.imprimirFactura(numeroFactura);
	   }
	   return tabla;
	}
	
	public String buscaFacturaDniCliente(String dni) {
		String resultado = "";
		Cliente cliente = buscaCliente(dni);
		if(cliente != null) {	
			resultado +="\n" + "Facturas del Cliente " + cliente.getNombre()  + " " + cliente.getApellidos() + ": " + "\n\t" + cliente.buscaFacturaDni(dni);
		}else {
			resultado += "El Cliente NO Existe!";
		}
		return resultado;
	}
	
	public String generarFacturaArchivo(String numeroFactura) throws IOException {
		String resultado = "";
		for(Cliente cliente : clientes.values()) {
			Factura factura = cliente.buscarFactura(numeroFactura);
			if(factura.getFechaCreacion().equals(numeroFactura)) {
			   resultado += cliente.guardarFacturaEnArchivo(numeroFactura);
			}
		}
		return resultado;
	}
	
	public ArrayList<String>getProductos(){
		ArrayList<String>productos = new ArrayList<String>();
		LinkedHashMap<String, Producto> productosAlmacen = almacen.getProductos();
		
		for (String producto : productosAlmacen.keySet()) {
	        productos.add(producto);
	    }
	    return productos;
	}
	
	public String getCantidadPrecio(String nombre) {
		return almacen.getCantidadYPrecio(nombre);
	}
	
	
	@Override
	public String toString() {
		return almacen.toString();
	}
	
	
}