package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.entities.Cliente;

public interface ClienteDao extends JpaRepository<Cliente, String>{

}
