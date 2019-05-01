package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.*;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.*;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RemovAllServerImpl implements RemovAllServer{

	@Autowired
	CupboardRepository cupboardRepo;
	@Autowired
	ModelRepository modelRepo;
	@Autowired
	PhotoGalleryRepository photoRepo;
	@Autowired
	QuartesRepozitory quartesRepo;
	@Autowired
	RoomRepository roomRepo;
	@Autowired
	ShelfRepository polkaRepo;
	@Autowired
	ThingRepository thingRepo;
	@Autowired
	OwnerRepozitory ownerRepo;
	@Autowired
	SizeRepozitory sizeRepo;
	@Autowired
	SeasonRepozitory seasonRepo;
	
	
	
	
	@Override
	@Transactional
	public SederReturneCode removeAll() {
	removeAllPhotos();
	removeAllModels();
	removeAllThings();
	removeAllPolkas();
	removeAllCupboards();
	removeAllRooms();
	removeAllSeason();
	removeAllQuartes();
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllModels() {
		List<Model> models = modelRepo.findAll();
		modelRepo.deleteAll(models);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllThings() {
		List<Thing> tjings = thingRepo.findAll();
		thingRepo.deleteAll(tjings);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllPolkas() {
		List<Shelf> shelfs = polkaRepo.findAll();
		polkaRepo.deleteAll(shelfs);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllCupboards() {
		List<Cupboard> cupboards = cupboardRepo.findAll();
		cupboardRepo.deleteAll(cupboards);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllPhotos() {
		List<PhotoGallery> photos = photoRepo.findAll();
		photoRepo.deleteAll(photos);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllRooms() {
		List<Room> rooms = roomRepo.findAll();
		roomRepo.deleteAll(rooms);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllQuartes() {
		List<Quartes> quartes = quartesRepo.findAll();
		quartesRepo.deleteAll(quartes);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllSize() {
		List<Sizes> sizes = sizeRepo.findAll();
		sizeRepo.deleteAll(sizes);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllOwner() {
		List<Owner> owners = ownerRepo.findAll();
		ownerRepo.deleteAll(owners);
		return SederReturneCode.OK;
	}




	@Override
	public SederReturneCode removeAllSeason() {
		List<Season> seasons = seasonRepo.findAll();
		seasonRepo.deleteAll(seasons);
		return SederReturneCode.OK;
	}
	
	
	
}
