package init.service;

import org.springframework.stereotype.Service;

import init.dao.ClienteDao;
import init.model.ClienteDto;
import init.utilidades.Mapeador;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	ClienteDao dao;
	Mapeador mapeador;
	
	public ClienteServiceImpl(ClienteDao dao, Mapeador mapeador) {
		this.dao = dao;
		this.mapeador = mapeador;
	}
	@Override
	public ClienteDto getCliente(String usuario) {
		return dao.findById(usuario)
		.map(c -> mapeador.clienteEntityToDto(c))
		.orElse(null);
	}

	@Override
	public ClienteDto getClienteUserPass(String usuario, String password) {
		ClienteDto cliente = getCliente(usuario);
		if(cliente != null && cliente.getPassword().equals(password)) {
			return cliente;
		}
		return null;
	}
	@Override
	public boolean registroCliente(ClienteDto cliente) {
		ClienteDto c = getCliente(cliente.getUsuario());
		if(c==null){
			dao.save(mapeador.clienteDtoToEntity(cliente));
			return true;
		}
		return false;
	}

}
