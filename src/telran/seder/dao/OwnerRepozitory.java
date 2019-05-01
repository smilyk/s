package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.seder.entities.Owner;

public interface OwnerRepozitory extends JpaRepository<Owner, String>{

	
}
