package init.service;

import init.model.ClienteDto;

public interface ClienteService {
	
	ClienteDto getCliente(String usuario);
	ClienteDto getClienteUserPass(String usuario, String password);
	boolean registroCliente(ClienteDto cliente);
}
