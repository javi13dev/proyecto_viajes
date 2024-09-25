package init.model;


public class ReservaDto {
	
	private int idreserva;
	private HotelDto hotel;
	private VueloDto vuelo;
	private double precio;
	private String usuario;
	
	public ReservaDto() {
		
	}
	
	public ReservaDto(int idreserva, HotelDto hotel, VueloDto vuelo, double precio, String usuario) {
		super();
		this.idreserva = idreserva;
		this.hotel = hotel;
		this.vuelo = vuelo;
		this.precio = precio;
		this.usuario = usuario;
	}

	public int getIdreserva() {
		return idreserva;
	}

	public void setIdreserva(int idreserva) {
		this.idreserva = idreserva;
	}

	public HotelDto getHotel() {
		return hotel;
	}

	public void setHotel(HotelDto hotel) {
		this.hotel = hotel;
	}

	public VueloDto getVuelo() {
		return vuelo;
	}

	public void setVuelo(VueloDto vuelo) {
		this.vuelo = vuelo;
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
