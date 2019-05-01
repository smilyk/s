package telran.seder.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Cupboard;

import java.util.List;


public interface CupboardRepository extends JpaRepository<Cupboard, String> {

    List<Cupboard> findAllByFoolFalse();

    List<Cupboard> findByRoomNameRoom(String roomName);

    @Query(value = "select name_room from room", nativeQuery = true)
    List<String>getAllRoomsByName();

    @Query(value = "select name_photo from photo_gallery where cupboard_photo_name_cupboard =:name", nativeQuery = true)
    List<String> getPhotoFromCupboard(@Param("name") String name);

    @Query(value = "select name_shelf from shelf where cupboard_name_cupboard = :name", nativeQuery = true)
    List<String> getPolkaFromCupboard(@Param("name") String name);


}
