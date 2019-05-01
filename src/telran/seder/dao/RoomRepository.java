package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String>{


    @Query(value = "select name_quartes from quartes", nativeQuery = true)
    List<String> getQuartesNames();

    @Query(value = "select name_cupboard from cupboard where room_name_room =:name ", nativeQuery = true)
    List<String> getCupboardsNames(@Param("name") String name);


}
