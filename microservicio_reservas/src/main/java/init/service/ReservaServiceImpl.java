package init.service;

import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import init.dao.ReservaDao;
import init.model.ReservaDto;
import init.utilidades.Mapeador;

@Service
public class ReservaServiceImpl implements ReservaService {
	
	
	// Para usar las variables de properties, mediante @Value
	@Value("${usuario}")
	String usuario;
	@Value("${contra}")
	String contra;
	//@Value("${admin.user}")
	//String userAdmin;
	//@Value("${admin.pass}")
	//String passAdmin;
	
	
	ReservaDao reservaDao;
	Mapeador mapeador;
	
	String url_servicio_vuelo = "http://servicio-vuelos/vuelos/vuelo";
	String url_servicio_vuelo_securizado = "http://servicio-vuelos/vuelos/vuelosec";
	String url_servicio_hotel = "http://servicio-hoteles/hoteles/hoteles/";
	
	// Para consumirlo necesito un Cliente
	RestClient restClient;
	
	public ReservaServiceImpl(RestClient restClient, ReservaDao reservaDao, Mapeador mapeador) {
		this.restClient = restClient;
		this.reservaDao = reservaDao;
		this.mapeador = mapeador;
	}
	
	// para codificar en base64, vamos a tener un método:
	private String getBase64(String usuario, String contra) {
		System.out.println("Entra en getbase64");
		String cad=usuario+":"+contra;
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
		
		double preciofinal = reserva.getVuelo().getPrecio()*plazas;
		
		if(plazas>0) {			
			reserva.setPrecio(preciofinal);
			reservaDao.save(mapeador.reservaDtotoEntity(reserva));
			updatePlazas(reserva.getVuelo().getIdvuelo(), plazas); 
			return true;
		}
		return false;
		
	};
	
	public void updatePlazas(int idVuelo, int plazas) {
		URI uri = UriComponentsBuilder
		        .fromHttpUrl(url_servicio_vuelo_securizado)
		        .queryParam("idVuelo", idVuelo)
		        .queryParam("plazas", plazas)
		        .build()
		        .toUri();
		
		restClient.get()
        .uri(uri)
        .header("Authorization", "Basic "+getBase64(usuario,contra))
        .retrieve()
        .toBodilessEntity();  
	}

}
