package application.modelo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Factura {
	private String fechaCreacion;
	private Cliente cliente;
	private double total;
	private Pedido pedido;
	
	public Factura(String id, ArrayList<Pedido> pedidos, Cliente cliente, double total) {
		this.fechaCreacion = generarIdUnico();
		this.cliente = cliente;
		this.total = total;
	}
	public Factura(Cliente cliente, Pedido pedido) {
		this.fechaCreacion = generarIdUnico();
		this.cliente = cliente;
		this.pedido = pedido;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	private String generarIdUnico() {
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMHHmm");
        String timestamp = ahora.format(formato);
        return timestamp;
    }
	
	public void agregarPedido(Pedido pedido) {
       pedido = new Pedido();
    }
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		return Objects.equals(fechaCreacion, other.fechaCreacion);
	}
	
	@Override
	public String toString() {
		
		return "Factura Nr: " + fechaCreacion;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fechaCreacion);
	}
}
