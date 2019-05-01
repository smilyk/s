package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Model;

import java.util.List;


public interface ModelRepository extends JpaRepository<Model, String> {

    @Query(value = "select thing.on_the_shelf from thing where thing.name_thing =:thingName", nativeQuery = true)
    Boolean getOnTheShelfaByNameThing(@Param("thingName") String thingName);

    @Query(value = "select thing.shelf_name_shelf from thing where thing.name_thing =:thingName", nativeQuery = true)
    String getShelfyNameThing(@Param("thingName") String thingName);

    @Query(value = "select thing.description from thing where thing.name_thing =:thingName", nativeQuery = true)
    String getDescriptionNameThing(@Param("thingName") String thingName);

    @Query(value = "select shelf.name_shelf from shelf", nativeQuery = true)
    List<String> getStringShelfsName();

    @Query(value = "select thing.name_thing from thing", nativeQuery = true)
    List<String> getStringThingsName();

//    @Query(value = "select model.name_model from model", nativeQuery = true)
//    List<String> getStringModelsName();
//
//    @Query(value = "select shelf.fool from shelf join thing on shelf.name_shelf = thing.polka_name_shelf where thing.polka_name_shelf =:thingName limit 1", nativeQuery = true)
//    Boolean getFoolPolkaFromThingName(@Param("thingName") String thingName);
//
//    @Query(value = "select shelf.description from shelf join thing on shelf.name_shelf = thing.polka_name_shelf where thing.polka_name_shelf =:thingName limit 1", nativeQuery = true)
//    String getDescriptionPolkaFromThingName(@Param("thingName") String thingName);

    List<Model> findBySeazon(String sezonName);

    List<Model> findByOwner(String ownerName);

    List<Model> findBySize(String sizeNname);

    @Query(value = "select name_photo from photo_gallery where thing_photo_name_thing= :thingName", nativeQuery = true)
    List<String> getPhotoNameByThing(@Param("thingName") String thingName);
}
