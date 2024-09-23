package init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import init.model.HotelDto;
import init.service.HotelService;

@RestController
public class HotelController {
	
	HotelService service;
	
	public HotelController(HotelService service) {
		this.service = service;
	}
	
	@GetMapping(value="hotel/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelDto> buscarPorId(@PathVariable int id) {
		HotelDto hotel = service.getHotel(id);
		if(hotel != null) {
			return new ResponseEntity<>(hotel,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
	}
	
	@GetMapping(value="hoteles/{localizacion}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HotelDto>> buscarPorLocalizacion(@PathVariable String localizacion) {
		List<HotelDto> hoteles = service.getHoteles(localizacion);
		if(hoteles.size() == 0) {
			return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(hoteles,HttpStatus.OK);
	}
}
