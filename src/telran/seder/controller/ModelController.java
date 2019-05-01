package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.ModelDto;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.dto.ThingDto;
import telran.seder.service.ModelServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_MODEL)
@Api(value="onlinestore", description="methods that collect data about the model ")
public class ModelController {
	@Autowired
	ModelServer modelServer;
	
	
	
	//=====================POST========================
	@ApiOperation(value = "Adding a new model to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added model"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
//	@PostMapping("/model")
	public SederReturneCode addModel(@RequestBody ModelDto modelJpa) {

		return modelServer.addModel(modelJpa);
	}
	
	
	//=====================DELITE========================

	@ApiOperation(value = "Delete thing", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted model"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{modelName}", method = RequestMethod.DELETE, produces = "application/json")
	public ModelDto removeModel(@PathVariable String modelName) {
		return modelServer.removeModel(modelName);
	}
	
	
	//=====================GET========================

	@ApiOperation(value = "Get model", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received model"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{modelName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/model/{modelName}")
	public ModelDto getModel(@PathVariable String modelName) {
		return modelServer.getModel(modelName);
	}


	@ApiOperation(value = "Get model by seazon", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received model by seazon"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/seazon/{sezonName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/model/seazon/{sezonName}")
	public List<ModelDto> getModelBySeazon(@PathVariable String sezonName){
		return modelServer.getModelBySeazon(sezonName);
	}


	@ApiOperation(value = "Get model by owner", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received model by owner"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/owner/{ownerName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/model/owner/{ownerName}")
	public List<ModelDto> getModelByOwner(@PathVariable String ownerName){
		return modelServer.getModelByOwner(ownerName);
	}


	@ApiOperation(value = "Get model by size", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received model by size"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "/size/{sizeName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/model/size/{sizeName}")
	public List<ModelDto> getModelBySize(@PathVariable String sizeName){
		return modelServer.getModelBySize(sizeName);
	}


	@ApiOperation(value = "Get list of all models", response = ModelDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all models"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/models")
	public List<ModelDto> getAllModels(){
		return modelServer.getAllModels();
	}
}
