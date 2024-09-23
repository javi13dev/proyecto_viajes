package init.service;

import java.util.List;

import init.model.HotelDto;

public interface HotelService {
	
	HotelDto getHotel(int idHotel);
	List<HotelDto> getHoteles(String localizacion);
}
