package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.dto.PhotoGalleryDto;
import telran.seder.entities.PhotoGallery;

import java.util.List;

public interface PhotoGalleryRepository extends JpaRepository<PhotoGallery, String> {

    @Query(value = "select thing.name_thing from thing", nativeQuery = true)
    List<String> getAllThing();

    @Query(value = "select cupboard.name_cupboard from cupboard", nativeQuery = true)
    List<String> getAllCupboard();

    @Query(value = "select thing.name_thing from thing", nativeQuery = true)
    List<String> getAllThingByName(@Param("thingName") String thingName);

    @Query(value = "select cupboard.name_cupboard from cupboard", nativeQuery = true)
    List<String> getAllCupboardByName(@Param("thingName") String thingName);



}
