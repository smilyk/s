package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.PhotoGalleryDto;
import telran.seder.dto.SederReturneCode;

import java.util.List;

@Service
public interface PhotoGalleryServer {

	SederReturneCode addThingPhoto(PhotoGalleryDto photo, String thingName);
	SederReturneCode addCupboardPhoto(PhotoGalleryDto photo, String cupboardName);
	SederReturneCode removPhoto(String photoName);
	List<PhotoGalleryDto> getAllPhoto();
	PhotoGalleryDto getPhoto(String photoName);

}
