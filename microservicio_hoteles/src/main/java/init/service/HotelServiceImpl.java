package init.service;

import java.util.List;

import org.springframework.stereotype.Service;

import init.dao.HotelDao;
import init.model.HotelDto;
import init.utilidades.Mapeador;

@Service
public class HotelServiceImpl implements HotelService {
	
	HotelDao dao;
	Mapeador mapeador;
	
	public HotelServiceImpl(HotelDao dao, Mapeador mapeador) {
		this.dao = dao;
		this.mapeador = mapeador;
	}
	
	
	@Override
	public HotelDto getHotel(int idHotel) {
		return dao.findById(idHotel)
		.map(h -> mapeador.hotelEntityToDto(h))
		.orElse(null);
				
	}

	@Override
	public List<HotelDto> getHoteles(String localizacion) {
		return dao.findByLocalizacion(localizacion).stream()
		.map(h -> mapeador.hotelEntityToDto(h))
		.toList();
	}

}
