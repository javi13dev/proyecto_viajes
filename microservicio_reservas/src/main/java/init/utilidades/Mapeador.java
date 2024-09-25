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
							hotelEntityToDto(reserva.getHotel()),
							vueloEntityToDto(reserva.getVuelo()),
							reserva.getVuelo().getPrecio(),
							reserva.getUsuario());
	}
	
	public Reserva reservaDtotoEntity(ReservaDto reserva) {
		
		return new Reserva(reserva.getIdreserva(),
							hotelDtoToEntity(reserva.getHotel()),
							vueloDtoToEntity(reserva.getVuelo()),
							reserva.getPrecio(),
							reserva.getUsuario());
	}
	
	public Vuelo vueloDtoToEntity(VueloDto vuelo) {
		return new Vuelo(vuelo.getIdvuelo(),
						vuelo.getCompany(),
						vuelo.getFecha(),
						vuelo.getPrecio(),
						vuelo.getPlazas(),
						vuelo.getDestino());
	}
	
	public VueloDto vueloEntityToDto(Vuelo vuelo) {
		return new VueloDto(vuelo.getIdvuelo(),
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
	
	public  HotelDto hotelEntityToDto(Hotel hotel) {
		return new HotelDto(hotel.getIdHotel(),
							hotel.getNombre(),
							hotel.getCategoria(),
							hotel.getPrecio(),
							hotel.isDisponible(),
							hotel.getLocalizacion());
	}

	


}
