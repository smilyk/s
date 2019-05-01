package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.PhotoGalleryRepository;
import telran.seder.dto.PhotoGalleryDto;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Cupboard;
import telran.seder.entities.PhotoGallery;
import telran.seder.entities.Thing;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PhotoGalleryServerImpl implements PhotoGalleryServer {

	@Autowired
	PhotoGalleryRepository photoRepo;

	@Override
	public SederReturneCode addThingPhoto(PhotoGalleryDto photoDto, String thingName) {
		if (photoRepo.existsById(photoDto.getNamePhoto())) {
			return SederReturneCode.PHOTO_EXIST;
		}
		PhotoGallery photo = getPhotoFromPhotoDto(photoDto, thingName);
		if (photo == null) {
			return SederReturneCode.THING_IS_WRONG;
		}
		photoRepo.save(photo);
		return SederReturneCode.OK;


	}

	private PhotoGallery getPhotoFromPhotoDto(PhotoGalleryDto photoDto, String thingName) {
		Boolean test = thingName(thingName);
		if (!test) {
			return null;
		}
		Thing thing = getThingFromThingNameAndPhoto(photoDto, thingName);
		PhotoGallery photoGallery = PhotoGallery.builder()
				.thingPhoto(thing)
				.namePhoto(photoDto.getNamePhoto())
				.description(photoDto.getDescription())
				.build();
		return photoGallery;
	}

	private Boolean thingName(String thingName) {
		List<String> thingsName = photoRepo.getAllThingByName(thingName);
		if (thingsName.contains(thingName)) {
			return true;
		}
		return false;
	}

	private Thing getThingFromThingNameAndPhoto(PhotoGalleryDto photoDto, String thingName) {

		Thing thing = Thing.builder()
				.nameThing(thingName)
				.build();
		return thing;
	}

	@Override
	public SederReturneCode addCupboardPhoto(PhotoGalleryDto photodto, String cupboardName) {
		if (photoRepo.existsById(photodto.getNamePhoto())) {
			return SederReturneCode.PHOTO_EXIST;
		}
		PhotoGallery photo = getPhotoFromPhotoDtoByCupboard(photodto, cupboardName);
		if (photo == null) {
			return SederReturneCode.CUPBOARD_IS_WRONG;
		}
		photoRepo.save(photo);
		return SederReturneCode.OK;

	}

	private PhotoGallery getPhotoFromPhotoDtoByCupboard(PhotoGalleryDto photoDto, String cupboardName) {
		Boolean test = cupboardName(cupboardName);
		if (!test) {
			return null;
		}
		Cupboard cupboard = getCupboardFromCupboardNameAndPhoto(photoDto, cupboardName);
		PhotoGallery photoGallery = PhotoGallery.builder()
				.cupboardPhoto(cupboard)
				.namePhoto(photoDto.getNamePhoto())
				.description(photoDto.getDescription())
				.build();
		return photoGallery;
	}

	private Cupboard getCupboardFromCupboardNameAndPhoto(PhotoGalleryDto photoDto, String cupboardName) {

		Cupboard cupboard = Cupboard.builder()
				.nameCupboard(cupboardName)
				.build();
		return cupboard;
	}

	private Boolean cupboardName(String cupboardName) {
		List<String> cupboardsName = photoRepo.getAllCupboardByName(cupboardName);
		if (cupboardsName.contains(cupboardName)) {
			return true;
		}
		return false;
	}


	@Override
	public SederReturneCode removPhoto(String photoName) {
		PhotoGallery photoGallery = photoRepo.findById(photoName).orElse(null);
		if (photoGallery == null) {
			return SederReturneCode.NO_PHOTO;
		}
		photoRepo.delete(photoGallery);
		return SederReturneCode.PHOTO_DELITED;
	}

	@Override
	public List<PhotoGalleryDto> getAllPhoto() {
		List<PhotoGallery> photos = photoRepo.findAll();
		if(photos.size() == 0){
			return null;
		}
		List<PhotoGalleryDto> photosDto = photos.stream()
				.map(this::getPhotoDtoFromPhoto)
				.collect(Collectors.toList());
		return photosDto;
	}

	@Override
	public PhotoGalleryDto getPhoto(String photoName) {
		PhotoGallery photo = photoRepo.findById(photoName).orElse(null);
		PhotoGalleryDto photoDto = getPhotoDtoFromPhoto(photo);
		return photoDto;
	}

	private PhotoGalleryDto getPhotoDtoFromPhoto(PhotoGallery photoGallery) {


		String nameThing = null;
		if( photoGallery.getThingPhoto()!=null){
			nameThing = photoGallery.getThingPhoto().getNameThing();
		}
		Boolean test = testThingByPhoto(nameThing);
		if(!test){
			nameThing = null;
		}
		String nameCupboard = null;
				if( photoGallery.getCupboardPhoto()!= null){
					nameCupboard = photoGallery.getCupboardPhoto().getNameCupboard();
				}
		test = testCupboardPhoto(nameCupboard);
		if(!test){
			nameCupboard = null;
		}
		PhotoGalleryDto photoGalleryDto = PhotoGalleryDto.builder()
				.namePhoto(photoGallery.getNamePhoto())
				.description(photoGallery.getDescription())
				.nameCupboard(nameCupboard)
				.nameThing(nameThing)
				.build();
		return photoGalleryDto;
	}

	private Boolean testCupboardPhoto(String nameCupboard) {
		List<String> nameCupboards = photoRepo.getAllCupboard();
		if(nameCupboards.contains(nameCupboard)){
			return true;
		}
		return false;
	}


	private Boolean testThingByPhoto(String nameThing) {
		List<String> nameThings = photoRepo.getAllThing();
		if(nameThings.contains(nameThing)){
			return true;
		}
		return false;
	}


}