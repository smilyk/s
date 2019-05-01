package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.CupboardDto;
import telran.seder.dto.QuartesDto;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.service.QuartesServer;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_QUARTES)
@Api(value="onlinestore", description="methods that collect data about the quartes ")
public class QuartesController {
	@Autowired
	QuartesServer quartesServer;

	//=============POST==============
	@ApiOperation(value = "Adding a new quartes to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Successfully added quartes"),
	@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addQuartes(@RequestBody QuartesDto quartes) {
		System.err.println(quartes);
		return quartesServer.addQuartes(quartes);
	}

	//=============DELITE==============

	@ApiOperation(value = "Delete quartes", response = QuartesDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted quartes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{nameQuartes}", method = RequestMethod.DELETE, produces = "application/json")
	@DeleteMapping("/quartes/{nameQuartes}")
	public QuartesDto removeQuartes(@PathVariable String nameQuartes) {
		System.err.println(nameQuartes);
		return quartesServer.removeQuartes(nameQuartes);
	}
	
	
	//=============GET==============
	@ApiOperation(value = "Get quartes", response = QuartesDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received quartes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{nameQuartes}", method = RequestMethod.GET, produces = "application/json")
	@GetMapping("/quartes/{nameQuartes}")
	public QuartesDto getQuartes(@PathVariable String nameQuartes) {
		return quartesServer.getQuartes(nameQuartes);
	}


	@ApiOperation(value = "Get all quartes", response = QuartesDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all quartes"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping( value = "/all", method = RequestMethod.GET, produces = "application/json")
	@GetMapping("quartes/all")
	public List<QuartesDto> getAllQuartes(){
		return quartesServer.getAllQuartes();
	}
}
