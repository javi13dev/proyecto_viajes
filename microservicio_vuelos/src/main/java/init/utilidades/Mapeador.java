package init.utilidades;

import org.springframework.stereotype.Component;

import init.entities.Vuelo;
import init.model.VueloDto;

@Component
public class Mapeador {
	
	public VueloDto vueloEntityToDto(Vuelo vuelo) {
		return new VueloDto(vuelo.getIdvuelo(),
							vuelo.getCompany(),
							vuelo.getFecha(),
							vuelo.getPrecio(),
							vuelo.getPlazas(),
							vuelo.getDestino());
	}
	
	public Vuelo vueloDtoToEntity(VueloDto vuelo) {
		return new Vuelo(vuelo.getIdvuelo(),
						vuelo.getCompany(),
						vuelo.getFecha(),
						vuelo.getPrecio(),
						vuelo.getPlazas(),
						vuelo.getDestino());
	}
	
	
}
