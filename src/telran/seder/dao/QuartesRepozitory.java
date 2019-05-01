package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Quartes;

import java.util.List;

public interface QuartesRepozitory extends JpaRepository<Quartes, String> {


    @Query(value = "select name_room from room where quartes_name_quartes =:quartesName", nativeQuery = true)
    List<String> getRoomNamesFromQuartes(@Param("quartesName") String quartesName);

}
