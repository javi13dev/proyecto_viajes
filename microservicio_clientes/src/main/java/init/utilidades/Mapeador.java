package init.utilidades;

import org.springframework.stereotype.Component;

import init.entities.Cliente;
import init.model.ClienteDto;


@Component
public class Mapeador {
	
	public  ClienteDto clienteEntityToDto(Cliente cliente) {
		return new ClienteDto(cliente.getUsuario(),
							cliente.getPassword(),
							cliente.getDireccion(),
							cliente.getTarjeta(),
							cliente.getDni());
	}
	
	public Cliente clienteDtoToEntity(ClienteDto cliente) {
		return new Cliente(cliente.getUsuario(),
							cliente.getPassword(),
							cliente.getDireccion(),
							cliente.getTarjeta(),
							cliente.getDni());
	}

}
