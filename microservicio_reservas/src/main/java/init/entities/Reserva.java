package init.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idreserva;
	
	// Relaciones
	//	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel", referencedColumnName = "idHotel")
	private Hotel hotel;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vuelo", referencedColumnName = "idvuelo")
	private Vuelo vuelo;
	private double precio;
	private String usuario;
	
	public Reserva () {
		
	}
	public Reserva(int idreserva, Hotel hotel, Vuelo vuelo, double precio, String usuario) {
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
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
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
