package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Season;
import telran.seder.entities.Sizes;
import telran.seder.service.SeasonServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_SEASON)
@Api(value="onlinestore", description="methods that collect data about the seasons ")
public class SeasonController {
	@Autowired
	SeasonServer seasonServer;
	
	
	//========POST============
	@ApiOperation(value = "Adding a new season to DataBase", response = SederReturneCode.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully added season"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public SederReturneCode addSeason(@RequestBody Season season) {
		return seasonServer.addSeason(season);
	}
	
	
	//========DELITE============
	@ApiOperation(value = "Delete season", response = Season.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully deleted season"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{seasonName}", method = RequestMethod.DELETE, produces = "application/json")
	public Season removeSeason(@PathVariable String seasonName) {
		return seasonServer.removeSeason(seasonName);
	}
	
	
	//========GET============

	@ApiOperation(value = "Get season", response = Season.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received season"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "{seasonName}", method = RequestMethod.GET, produces = "application/json")
	Season getSeason(@PathVariable String seasonName) {
		return seasonServer.getSeason(seasonName);
	}

	@ApiOperation(value = "Get list of all seasons in the room", response = Sizes.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully received all seasons"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	@RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
	public List<Season> getAllSeason(){
		return seasonServer.getAllSeason();
	}
}
