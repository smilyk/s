package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.RoomDto;
import telran.seder.dto.SederReturneCode;

import java.util.List;

@Service
public interface RoomServer {

	SederReturneCode addRoom(RoomDto roomDto);
	RoomDto removeRoom(String nameRoom);
	RoomDto getRoom(String nameRoom);
	List<RoomDto> getAllRooms();
}
