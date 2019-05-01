package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.DtoForOneThing;
import telran.seder.dto.SederReturneCode;
import telran.seder.dto.ThingDto;

import java.util.List;

@Service
public interface ThingServer {

	SederReturneCode addThing(ThingDto thingJpa);
	ThingDto removeThing(String thingName);
	DtoForOneThing getThing(String thingName);
	List<ThingDto> getAllThings();
	List<ThingDto> getAllThingsByShelf(String shelfName);
	List<ThingDto> getAllThingsNotInThePlace();
	SederReturneCode putThingOnTheShelf(String thingName, String shelfName);
	SederReturneCode removeThingFromTheShelf(String thingName);
	ThingDto getAllForOneThing(String thingName);
}
