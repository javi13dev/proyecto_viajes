package init.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.ReservaDto;
import init.service.ReservaService;

@CrossOrigin("*")
@RestController
public class ReservaController {
	
	ReservaService service;
	
	public ReservaController(ReservaService service) {
		this.service = service;
	}
	
	
	@GetMapping(value="reserva/{usuario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReservaDto>> getReservas(@PathVariable String usuario){
		List<ReservaDto> reservas = service.getReservas(usuario);
		if(reservas.size() > 0) {
			return new ResponseEntity<>(reservas,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
	}
	
	
	@PostMapping(value="reserva", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaReserva(@RequestBody ReservaDto reserva, @RequestParam int plazas) { // Le indicamos que no hay tipo de devolución
		System.out.println("Entra en reservaController 83");
		if(service.altaReserva(reserva, plazas)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
