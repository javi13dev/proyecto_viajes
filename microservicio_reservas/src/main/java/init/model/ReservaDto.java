package init.model;

public class ReservaDto {
	
	private int idreserva;
	private String nombre;
	private int idvuelo;
	private double precio;
	private String usuario;
	
	public ReservaDto() {
		
	}

	public ReservaDto(int idreserva, String nombre, int idvuelo, double precio, String usuario) {
		super();
		this.idreserva = idreserva;
		this.nombre = nombre;
		this.idvuelo = idvuelo;
		this.precio = precio;
		this.usuario = usuario;
	}

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdvuelo() {
		return idvuelo;
	}

	public void setIdvuelo(int idvuelo) {
		this.idvuelo = idvuelo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
