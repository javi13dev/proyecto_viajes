package init.utilidades;

import org.springframework.stereotype.Component;

import init.entities.Hotel;
import init.entities.Reserva;
import init.entities.Vuelo;
import init.model.HotelDto;
import init.model.ReservaDto;
import init.model.VueloDto;

@Component
public class Mapeador {
	
	public ReservaDto reservaEntityToDto(Reserva reserva) {
		
		return new ReservaDto(reserva.getIdreserva(),
							reserva.getHotel().getNombre(),
							reserva.getVuelo().getIdvuelo(),
							reserva.getPrecio(),
							reserva.getUsuario());
	}
	
	/*
	public Reserva reservaDatoToEntity(ReservaDto reserva) {
		return new Reserva(0,
							reserva.)
	}
	*/
	
	public Vuelo vueloDtoToEntity(VueloDto vuelo) {
		return new Vuelo(vuelo.getIdvuelo(),
						vuelo.getCompany(),
						vuelo.getFecha(),
						vuelo.getPrecio(),
						vuelo.getPlazas(),
						vuelo.getDestino());
	}
	
	
	public  Hotel hotelDtoToEntity(HotelDto hotel) {
		return new Hotel(hotel.getIdHotel(),
							hotel.getNombre(),
							hotel.getCategoria(),
							hotel.getPrecio(),
							hotel.isDisponible(),
							hotel.getLocalizacion());
	}
	


}
