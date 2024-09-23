package init.service;

import java.util.List;

import org.springframework.stereotype.Service;

import init.dao.VuelosDao;
import init.model.VueloDto;
import init.utilidades.Mapeador;

@Service
public class VueloServiceImpl implements VueloService {
	
	VuelosDao dao;
	Mapeador mapeador;
	
	public VueloServiceImpl(VuelosDao dao, Mapeador mapeador) {
		this.dao = dao;
		this.mapeador = mapeador;
	}

	@Override
	public List<VueloDto> getVuelos(String destino, int plazas) {
		return dao.findByDestinoAndPlazas(destino, plazas).stream()
				.map(v -> mapeador.vueloEntityToDto(v))
				.toList();
	}
	

	@Override
	public VueloDto getVuelo(int idvuelo) {
		return dao.findById(idvuelo)
		.map(v -> mapeador.vueloEntityToDto(v))
		.orElse(null);
	}
	
	/*
	 * Actualización de plazas a partir de identificador de vuelo y plazas reservadas 
		(securizado de maneara que solo usuarios de un determinado rol puedan utilizarlo) 
	 */
	
	@Override
	public boolean updatePlazas(int idVuelo, int plazas) {
		VueloDto vuelo = getVuelo(idVuelo);
		if(vuelo!=null) {
			vuelo.setPlazas(vuelo.getPlazas()-plazas);
			dao.save(mapeador.vueloDtoToEntity(vuelo));
			return true;
		}
		return false;
	}

}
