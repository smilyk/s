package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.seder.entities.Sizes;

public interface SizeRepozitory extends JpaRepository<Sizes, String>{

}
