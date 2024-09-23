package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.entities.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Integer> {
	
	@Query("select h from Hotel h where h.localizacion=?1") // Podemos acceder a las propiedades del objeto una vex relacionado.
	List<Hotel> findByLocalizacion(String localizacion);
}
