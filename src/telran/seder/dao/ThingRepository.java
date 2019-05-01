package telran.seder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import telran.seder.entities.Thing;

import java.util.List;

public interface ThingRepository extends JpaRepository<Thing, String>{

	List<Thing> findAllByonTheShelfFalse();

	@Query(value = "select description from shelf where name_shelf = :name", nativeQuery = true)
	String getDescriptionShelfFromThing(@Param("name") String name);

	@Query(value = "select  fool from shelf where name_shelf = :name", nativeQuery = true)
	Boolean getFoolFromShelf( @Param("name") String name);

	@Query(value = "select name_thing from thing where shelf_name_shelf= :name", nativeQuery = true)
	List<String> getNamesOfThingsOnTheShelf(@Param("name") String name);

	@Query(value = "select name_photo from photo_gallery where thing_photo_name_thing= :name", nativeQuery = true)
	List<String> getNamesOfPhotoFromThing(@Param("name") String name);

//	@Query(value = "select name_shelf from shelf where s= :name", nativeQuery = true)
//	List<String> getNamesOfShelfFromThing(@Param("name") String name);


	@Query(value = "select name_shelf from shelf", nativeQuery = true)
	List<String> getShelfsNameFromThing();

	@Query(value = "select name_shelf from shelf where cupboard_name_cupboard =:name", nativeQuery = true)
	List<String> getShelfFromCupboards(@Param("name") String name);

	@Query(value = "select name_thing from thing where shelf_name_shelf =:name", nativeQuery = true)
	List<String> getThingsNameFromShelf(@Param("name") String name);

	@Query(value = "select cupboard_name_cupboard from shelf where name_shelf= :name", nativeQuery = true)
	List<String> getCupboardsNameFromShelf(@Param("name") String name);

	@Query(value = "select name_model from model where things_name_thing =:name", nativeQuery = true)
	List<String> getNamesOfModelFromTingName(@Param("name") String name);
}
