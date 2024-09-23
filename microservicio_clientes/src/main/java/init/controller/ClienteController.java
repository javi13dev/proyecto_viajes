package init.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.ClienteDto;
import init.service.ClienteService;

@RestController
public class ClienteController {

	ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping(value="cliente/{usuario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDto> getCliente(@PathVariable String usuario){
		ClienteDto cliente = service.getCliente(usuario);
		if(cliente!=null) {
			return new ResponseEntity<>(cliente,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		
	}
	
	@PostMapping(value="cliente", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDto> getCliente(@RequestBody ClienteDto cliente){
		ClienteDto clienteDto = service.getClienteUserPass(cliente.getUsuario(), cliente.getPassword());
		// Si existe cliente lo recuperamos entero.
		if(clienteDto!=null) {
			return new ResponseEntity<>(clienteDto,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.CONFLICT);
		
	}
	
	@PostMapping(value="registro", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> altaCliente(@RequestBody ClienteDto cliente) { // Le indicamos que no hay tipo de devolución
		
		if(service.registroCliente(cliente)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
}
