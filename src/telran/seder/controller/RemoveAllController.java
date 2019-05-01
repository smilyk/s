package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.service.RemovAllServer;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_REMOVE_ALL)
@Api(value="onlinestore", description="methods that removing all database ")

public class RemoveAllController {
	
	@Autowired
	RemovAllServer removAllServer;

	@ApiOperation(value = "Removing all photos from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all photos"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/photo", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllPhotos() {
		return removAllServer.removeAllPhotos();
	}


	@ApiOperation(value = "Removing all rooms from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all rooms"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/room", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllRooms() {
		return removAllServer.removeAllRooms();
	}


	@ApiOperation(value = "Removing all cupboards from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all cupboards"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/cupboard", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllCupboards() {
		return removAllServer.removeAllCupboards();
	}


	@ApiOperation(value = "Removing all shelves from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all shelves"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/shelf", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllPolkas() {
		return removAllServer.removeAllPolkas();
	}


	@ApiOperation(value = "Removing all models from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all models"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/model", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllModels() {
		return removAllServer.removeAllModels();
	}


	@ApiOperation(value = "Removing all things from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all things"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/thing", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllThings() {
		return removAllServer.removeAllThings();
	}


	@ApiOperation(value = "Removing all quarters from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all quarters"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/quarters", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllQuartes() {
		return removAllServer.removeAllQuartes();
	}


	@ApiOperation(value = "Removing all  from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/all", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAll() {
		return removAllServer.removeAll();
	}


	@ApiOperation(value = "Removing all sizes from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all sizes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/size", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllSize(){
		return removAllServer.removeAllSize();
	}


	@ApiOperation(value = "Removing all owners from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all owners"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/owner", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllOwner(){
		return removAllServer.removeAllOwner();
	}


	@ApiOperation(value = "Removing all seasons from DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully removed all seasons"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/season", method = RequestMethod.DELETE, produces = "application/json")
	public SederReturneCode removeAllSeazon(){
		return removAllServer.removeAllSeason();
	}

}
