package init.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import init.dao.ReservaDao;
import init.entities.Reserva;
import init.model.HotelDto;
import init.model.ReservaDto;
import init.model.VueloDto;
import init.utilidades.Mapeador;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	
	// Para usar las variables de properties, mediante @Value
	@Value("${usuario}")
	String user;
	@Value("${contra}")
	String contra;
	//@Value("${admin.user}")
	//String userAdmin;
	//@Value("${admin.pass}")
	//String passAdmin;
	
	
	ReservaDao reservaDao;
	Mapeador mapeador;
	
	String url_servicio_vuelo = "http://localhost:9000/vuelos/vuelo";
	String url_servicio_vuelo_securizado = "http://localhost:9000/vuelos/vuelosec";
	String url_servicio_hotel = "http://localhost:8000/hoteles/hoteles/";
	
	// Para consumirlo necesito un Cliente
	RestClient restClient;
	
	public ReservaServiceImpl(RestClient restClient, ReservaDao reservaDao, Mapeador mapeador) {
		this.restClient = restClient;
		this.reservaDao = reservaDao;
		this.mapeador = mapeador;
	}
	
	// para codificar en base64, vamos a tener un método:
	private String getBase64(String user, String contra) {
		String cad=user+":"+contra;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	} 
	
	// Listas de reservas por cliente
	@Override
	public List<ReservaDto> getReservas(String usuario) {
		return reservaDao.findByusuario(usuario).stream()
		.map(r -> mapeador.reservaEntityToDto(r))
		.toList();
	}
	
	// Para reserva, va a llamar a los microservicios vuelo y hotel para obtener los datos para Vuelo y Hotel
	@Override
	public boolean altaReserva(ReservaDto reserva, int plazas) {
		System.out.println("Entra en alta reserva");
		VueloDto vuelo = getVuelo(reserva.getIdvuelo());
		if(vuelo == null) {
			return false;
		}
		
		HotelDto hotel = getHotel(vuelo.getDestino());
		if(hotel == null) {
			return false;		
		}
		
		// Construyo la reserva:
		reservaDao.save(new Reserva(0,
				mapeador.hotelDtoToEntity(hotel),
				mapeador.vueloDtoToEntity(vuelo),
				vuelo.getPrecio()*plazas,
				reserva.getUsuario()));
		updatePlazas(vuelo.getIdvuelo(),plazas);
		return true;
		
		
	};
	
	// Métodos de apoyo
	
	public VueloDto getVuelo(int id) {
		// Construcción segura de la URL, la concatenación da problemas
	    URI uri = UriComponentsBuilder
	        .fromHttpUrl(url_servicio_vuelo)
	        .pathSegment(String.valueOf(id))
	        .build()
	        .toUri();

		return restClient
			.get()
			.uri(uri)
			.header("Authorization", "Basic "+getBase64(user,contra))
			.retrieve()
			.body(VueloDto.class);
	}
	
	public HotelDto getHotel(String localizacion) {
		// Construcción segura de la URL, la concatenación da problemas
	    URI uri = UriComponentsBuilder
	        .fromHttpUrl(url_servicio_hotel)
	        .pathSegment(localizacion)
	        .build()
	        .toUri();

	    // Obtengo los hoteles de la lozalización y me quedo con el primero.
		return Arrays.stream(restClient
			.get()
			.uri(uri)
			.retrieve()
			.body(HotelDto[].class))
			.findFirst()
			.orElse(null);
	}
	
	public void updatePlazas(int idVuelo, int plazas) {
		URI uri = UriComponentsBuilder
		        .fromHttpUrl(url_servicio_vuelo_securizado)
		        .queryParam("idVuelo", idVuelo)
		        .queryParam("plazas", plazas)
		        .build()
		        .toUri();
		
		restClient.post()
        .uri(uri)
        .header("Authorization", "Basic "+getBase64(user,contra))
        .retrieve()
        .toBodilessEntity();  
	}

}
