package application.modelo;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Cliente {
	private String dni;
	private String nombre;
	private String apellidos;
	private int descuento;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoActual;
	private LinkedHashMap<String, Factura> facturas;
	
	public Cliente(String dni, String nombre, String apellidos, int descuento) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.descuento = descuento;
		this.pedidos = new ArrayList<Pedido>();
		this.facturas = new LinkedHashMap<String, Factura>();
	}
	
	public Cliente() {
		this.pedidos = new ArrayList<Pedido>();
		this.facturas = new LinkedHashMap<String, Factura>();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, dni, nombre);
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public LinkedHashMap<String, Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(LinkedHashMap<String, Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void setPedidoActual(Pedido pedidoActual) {
		this.pedidoActual = pedidoActual;
	}
	
	public Pedido getPedidoActual() {
		return pedidoActual;
	}

	public void agregarNuevoPedido(String nombreProducto, int cantidad, double precio) {
	    if (pedidoActual == null) {
	        crearNuevoPedido();
	        Pedido pedido = getPedidoActual();
		    pedido.agregarProducto(nombreProducto, cantidad, precio);
	    }else {
	    	Pedido pedido = getPedidoActual();
	    	if (pedido.existeProductoPedido(nombreProducto)) {
	            pedido.aumentarCantidadProducto(nombreProducto, cantidad);
	        } else {
	            pedido.agregarProducto(nombreProducto, cantidad, precio);
	        }
	    }
	}
	
	public Factura buscarFactura(String numeroFactura) {
	    for (Map.Entry<String, Factura> f : facturas.entrySet()) {
	        if (f.getValue().getFechaCreacion().equals(numeroFactura)) {
	            return f.getValue();
	        }
	    }
	    return null;
	}
	
	private Pedido buscarPedido() {
	    Pedido pedidoEncontrado = null;
	    for (Pedido pedido : pedidos) {
	        if (pedido.getEstadoPedido() == true) {
	            pedidoEncontrado = pedido;
	            break;
	        }
	    }
	    return pedidoEncontrado;
	}
	
	private void almacenarFactura(Factura factura) {
		facturas.put(factura.getFechaCreacion(), factura);
	}
	
	public Factura crearFactura(Cliente cliente,Pedido pedido) {
		Factura factura = new Factura(cliente, pedido);
		
		return factura;
	}
	
	public String buscaFacturaDni(String dni) {
		String resultado = "";
	    for (Map.Entry<String, Factura> entry : facturas.entrySet()) {
	        Factura factura = entry.getValue();
	        Cliente cliente = factura.getCliente();
	        if (cliente.getDni().equals(dni)) {
	            resultado +="\n" + factura.getFechaCreacion();
	        }
	    }
	    return resultado;
	}
	
	public String generarFactura(String dni) {
	    String resultado = "";
	    if (pedidoActual != null){
	    	Pedido pedidoActual = buscarPedido();
	        Factura factura = crearFactura(this,pedidoActual);
	        almacenarFactura(factura);
	        cerrarPedidoActual();
	        crearNuevoPedido();
	        resultado += factura.getFechaCreacion();
	    } else {
	        resultado += "El pedido esta vacio!";
	    }
	    return resultado;
	}
	
	private void cerrarPedidoActual() {
	    Pedido pedidoActual = getPedidoActual();
	    pedidoActual.cerrarPedido();
	}

	private void crearNuevoPedido() {
	    Pedido nuevoPedido = new Pedido();
	    this.pedidos.add(nuevoPedido);
	    setPedidoActual(nuevoPedido);
	}
	
	public String imprimirFactura(String numeroFactura) {
	    StringBuilder tabla = new StringBuilder(); 
	    Factura factura = buscarFactura(numeroFactura);
	    if (factura != null) {  
	        DecimalFormat df = new DecimalFormat("#.##");
	        Pedido pedido = factura.getPedido();
	        if (pedido != null && pedido.getProductos() != null && !pedido.getProductos().isEmpty()) {
	            tabla.append("Cliente: " + factura.getCliente().getNombre() + " " + factura.getCliente().getApellidos() + "\n");
	            tabla.append("Factura Nº " + factura.getFechaCreacion() + "\n");
	            tabla.append("Producto\tUnidades\tPrecio\t\tDescuento\tSubtotal\n");
	            tabla.append("----------------------------------------------------------------------------------------------------------------\n");
	            double subtotal = 0.0;
	            for (Map.Entry<Producto, Integer> productoPedido : pedido.getProductos().entrySet()) {
	                Producto producto = productoPedido.getKey();
	                int unidades = pedido.getCantidadPedida(producto);
	                double precio = producto.getPrecio();
	                double descuento = factura.getCliente().getDescuento();
	                double subtotalProducto = unidades * precio;
	                tabla.append(String.format("%-19s%-19s%-20s%-20s%-19s\n", producto.getNombre(), unidades + "Uds", df.format(precio) + "€", df.format(descuento) + "%", df.format(subtotalProducto) + "€"));
	                subtotal += subtotalProducto;
	            }
	            double iva = subtotal * 0.21;
	            double descuentoTotal = subtotal * descuento / 100;
	            double total = subtotal + iva - descuentoTotal;
	            tabla.append("----------------------------------------------------------------------------------------------------------------\n");
	            tabla.append(String.format("%-89s%-20s\n", "Subtotal", df.format(subtotal) + "€"));
	            tabla.append(String.format("%-86s%-20s\n", "Descuento", df.format(descuentoTotal) + "€"));
	            tabla.append(String.format("%-88s%-20s\n", "IVA 21%", df.format(iva) + "€"));
	            tabla.append(String.format("%-89s%-20s\n", "TOTAL", df.format(total) + "€"));
	        } else {
	            tabla.append("El pedido No Existe o No contiene productos!"); 
	        }
	    }
	    return tabla.toString();
	}
	
	public String guardarFacturaEnArchivo(String numeroFactura) throws IOException {
		String resultado = "";
	    Factura factura = buscarFactura(numeroFactura);
	    if (factura == null) {
	        throw new IllegalArgumentException("La factura con nï¿½mero " + numeroFactura + " no existe.");
	    }else {	
	    String nombreArchivo = "C:\\Users\\Nicolas\\Desktop\\Martes\\" + factura.getFechaCreacion() +".txt";
	    FileWriter writer = new FileWriter(nombreArchivo);
	    resultado += "Ruta: " + nombreArchivo;
	    writer.write(imprimirFactura(numeroFactura));
	    writer.close();
	    return resultado;
	    }
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	
	public String toString() {
		return nombre + " ";
	}
	
	
}