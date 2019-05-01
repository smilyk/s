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
import telran.seder.entities.Sizes;
import telran.seder.service.SizeServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_SIZE)
@Api(value="onlinestore", description="methods that collect data about the sizes ")
public class SizeController {
	@Autowired
	SizeServer sizeServer;
	
	
	//=====================POST========================

	@ApiOperation(value = "Adding a new sizes to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added sizes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addSize(@RequestBody Sizes size) {
		return sizeServer.addSize(size);
	}
	
	
	//=====================DELITE========================

	@ApiOperation(value = "Delete size", response = Sizes.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted size"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{sizeName}", method = RequestMethod.DELETE, produces = "application/json")
	public Sizes removeSize(@PathVariable String sizeName) {
		return sizeServer.removeSize(sizeName);
	}
	
	
	//=====================GET========================

	@ApiOperation(value = "Get size", response = Sizes.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received size"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{sizeName}", method = RequestMethod.GET, produces = "application/json")
	public Sizes getSize(@PathVariable String sizeName) {
		return sizeServer.getSize(sizeName);
	}


	@ApiOperation(value = "Get list of all sizes in the room", response = Sizes.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all sizes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public List<Sizes> getAllSizes(){
		return sizeServer.getAllSizes();
	}
}
