package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.SederReturneCode;

@Service
public interface RemovAllServer {

	SederReturneCode removeAll();
	SederReturneCode removeAllModels();
	SederReturneCode removeAllThings();
	SederReturneCode removeAllPolkas();
	SederReturneCode removeAllCupboards();
	SederReturneCode removeAllPhotos();
	SederReturneCode removeAllRooms();
	SederReturneCode removeAllQuartes();
	SederReturneCode removeAllSize();
	SederReturneCode removeAllOwner();
	SederReturneCode removeAllSeason();
}
