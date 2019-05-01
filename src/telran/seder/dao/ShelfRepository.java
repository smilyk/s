package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Shelf;

import java.util.List;

public interface ShelfRepository extends JpaRepository<Shelf, String>{
    @Query(value = "select name_cupboard from cupboard", nativeQuery = true)
    List<String> getCupboardsNames();

    @Query(value = "select name_shelf from shelf join cupboard on cupboard.name_cupboard= shelf.cupboard_name_cupboard where shelf.cupboard_name_cupboard =:name ", nativeQuery = true)
    List<String> getCupboardsNamefromShelf(@Param("name") String name);

	List<Shelf> findAllByFoolFalse();

    @Query(value = "select name_thing from thing where shelf_name_shelf= :name ", nativeQuery = true)
    List<String> getThingsNamefromShelf(@Param("name") String name);

}
