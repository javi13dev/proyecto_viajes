package init.service;

import java.util.List;

import init.model.ReservaDto;

public interface ReservaService {
	List<ReservaDto> getReservas(String usuario);
	boolean altaReserva(ReservaDto reserva, int plazas);

}
