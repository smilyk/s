package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.seder.entities.Season;


public interface SeasonRepozitory extends JpaRepository<Season, String> {

}
