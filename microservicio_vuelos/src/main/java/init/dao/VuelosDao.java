package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.entities.Vuelo;

public interface VuelosDao extends JpaRepository<Vuelo, Integer>{
	
	@Query("select v from Vuelo v where v.destino=?1 AND v.plazas>=?2")
	List<Vuelo> findByDestinoAndPlazas(String destino, int plazas);
	
	/*
	 * Actualización de plazas a partir de identificador de vuelo y plazas reservadas 
		(securizado de maneara que solo usuarios de un determinado rol puedan utilizarlo) 
	 */
}
