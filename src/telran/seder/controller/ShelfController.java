package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.dto.ShelfDto;
import telran.seder.service.ShelfServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_SHELF)
@Api(value="onlinestore", description="methods that collect data about the shelves ")
public class ShelfController {
	@Autowired
    ShelfServer shelfServer;
	
	
	//=============POST===============


	@ApiOperation(value = "Adding a new shelf to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added shelf"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addShelf(@RequestBody ShelfDto polkaDto) {
		return shelfServer.addShelf(polkaDto);
	}


	@ApiOperation(value = "Make point shelf NOT fool", response = SederReturneCode.class )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully pointed shelf"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "point/notfool/{shelfName}", method = RequestMethod.PUT, produces = "application/json")
	public SederReturneCode pointShelfNotFuul(@PathVariable String shelfName){
		return shelfServer.pointShelfNotFuul(shelfName);
	}

	@ApiOperation(value = "Make point shelf fool", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully pointed shelf"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "point/fool/{shelfName}", method = RequestMethod.PUT, produces = "application/json")
	public SederReturneCode pointShelfFuul(@PathVariable String shelfName){
		return shelfServer.pointShelfFuul(shelfName);
	}
	
	//=============DELITE===============

	@ApiOperation(value = "Delete shelf", response = ShelfDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted shelf"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{shelfName}", method = RequestMethod.DELETE, produces = "application/json")
	public ShelfDto removeShelf(@PathVariable String shelfName) {
		return shelfServer.removeShelf(shelfName);
	}
	
	//=============GET===============
	@ApiOperation(value = "Get shelf", response = ShelfDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received shelf"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{shelfName}", method = RequestMethod.GET, produces = "application/json")
	public ShelfDto getShelf(@PathVariable String shelfName) {
		return shelfServer.getShelf(shelfName);
	}



	@ApiOperation(value = "Get list of all shelves in the room", response = ShelfDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all shelves in the room"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public List<ShelfDto> getAllShelf(){
		return shelfServer.getAllShelf();
	}


	@ApiOperation(value = "Get list of NOT fool shelves", response = ShelfDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received not fool shelves"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/notfool", method = RequestMethod.GET, produces = "application/json")
	public List<ShelfDto> getAllShelfNotFool() {
		return shelfServer.getAllShelfNotFool();
	}
	

	
}
