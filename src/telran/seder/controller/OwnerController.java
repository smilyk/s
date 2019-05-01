package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Owner;
import telran.seder.service.OwnerServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_OWNER)
@Api(value="onlinestore", description="methods that collect data about the owners ")
public class OwnerController {
	@Autowired
	OwnerServer ownerServer;
	
	//=============POST====================

	@ApiOperation(value = "Adding a new owners to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added owners"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addOwner(@RequestBody Owner owner) {
		return ownerServer.addOwner(owner);
	}
	
	
	//=============DELITE====================

	@ApiOperation(value = "Delete owner", response = Owner.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted owner"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{ownerName}", method = RequestMethod.DELETE, produces = "application/json")
	public Owner removeOwner(@PathVariable String ownerName) {
		return ownerServer.removeOwner(ownerName);
	}
	
	
	//=============GET====================

	@ApiOperation(value = "Get owner", response = Owner.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received owner"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{ownerName}", method = RequestMethod.GET, produces = "application/json")
	public Owner getOwner(@PathVariable String ownerName) {
		return ownerServer.getOwner(ownerName);
	}



	@ApiOperation(value = "Get list of all sizes in the owners", response = Owner.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all owners"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public List<Owner> getAllOwner(){
		return ownerServer.getAllOwner();
	}
}
