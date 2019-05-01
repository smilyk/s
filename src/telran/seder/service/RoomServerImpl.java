package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.RoomRepository;
import telran.seder.dto.RoomDto;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Quartes;
import telran.seder.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServerImpl implements RoomServer {

	@Autowired
	RoomRepository roomRepo;

	@Override
	public SederReturneCode addRoom(RoomDto roomDto) {
		if( roomRepo.existsById(roomDto.getNameRoom())){
			return SederReturneCode.ROOM_EXIST;
		}
		Quartes quartes = getQuartesFromRoom(roomDto);

		List<String> quartesName = roomRepo.getQuartesNames();
		if(!quartesName.contains(roomDto.getNameQuartes())){
			return SederReturneCode.WRONG_NAME_OF_QUARTES;
		}
		Room roomJpa = Room.builder()
				.nameRoom(roomDto.getNameRoom())
				.description(roomDto.getDescription())
				.quartes(quartes)
				.cupboard(new ArrayList<>())
				.build();
		roomRepo.save(roomJpa);
		return SederReturneCode.OK;
	}

	private Quartes getQuartesFromRoom(RoomDto roomDto) {
		Quartes quartes = Quartes.builder()
				.nameQuartes(roomDto.getNameQuartes())
				.build();
		return quartes;
	}

	@Override
	public RoomDto removeRoom(String nameRoom) {
		Room roomJpi = roomRepo.findById(nameRoom).orElse(null);
			if (roomJpi == null) {
				return null;
			}
			RoomDto roomDto = getRoomDtoFromRoom(roomJpi);
		roomRepo.deleteById(nameRoom);
		return roomDto;
	}

	private RoomDto getRoomDtoFromRoom(Room roomJpi) {
		List<String> cupboardsNames = roomRepo.getCupboardsNames(roomJpi.getNameRoom());
		RoomDto roomDto = RoomDto.builder()
				.nameRoom(roomJpi.getNameRoom())
				.description(roomJpi.getDescription())
				.nameQuartes(roomJpi.getQuartes().getNameQuartes())
				.cupboards(cupboardsNames)
				.build();
		return roomDto;
	}

	@Override
	public RoomDto getRoom(String nameRoom) {
		Room roomJpi = roomRepo.findById(nameRoom).orElse(null);
			if (roomJpi == null) {
				return null;
			}
			RoomDto roomDto = getRoomDtoFromRoom(roomJpi);
		return roomDto;
	}

	@Override
	public List<RoomDto> getAllRooms() {
		List<Room> rooms = roomRepo.findAll();
		List<RoomDto> roomsDto = rooms.stream().map(this::getRoomDtoFromRoom).collect(Collectors.toList());
		return roomsDto;
	}
}
