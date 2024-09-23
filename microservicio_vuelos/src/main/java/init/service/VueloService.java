package init.service;

import java.util.List;

import init.model.VueloDto;

public interface VueloService {
	
	List<VueloDto> getVuelos(String destino, int plazas);
	VueloDto getVuelo(int idvuelo);
	boolean updatePlazas(int idVuelo, int plazas);
}
