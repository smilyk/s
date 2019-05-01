package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.RoomDto;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.service.RoomServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_ROOM)
@Api(value="onlinestore", description="methods that collect data about the room ")

public class RoomController {

	@Autowired
	RoomServer roomServer;
	
	
	//================POST==========
	@ApiOperation(value = "Adding a new room to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successfully added room"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addRoom(@RequestBody RoomDto room) {
		return roomServer.addRoom(room);
	}


	//================DELITE==========
	@ApiOperation(value = "Delete room", response = RoomDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted room"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{nameRoom}", method = RequestMethod.DELETE, produces = "application/json")
	public RoomDto removeRoom(@PathVariable String nameRoom) {
		return roomServer.removeRoom(nameRoom);
	}

	//================GET==========
	@ApiOperation(value = "Get room", response = RoomDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received room"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{name}", method = RequestMethod.GET, produces = "application/json")

	public RoomDto getRoom(@PathVariable String name) {
		return roomServer.getRoom(name);
	}
	@ApiOperation(value = "Get all rooms", response = RoomDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all rooms"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<RoomDto> getAllRooms(){
		return roomServer.getAllRooms();
	}


	
}
