package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.*;
import telran.seder.service.ThingServer;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_THING)
@Api(value="onlinestore", description="methods that collect data about the thing ")

public class ThingController {
    @Autowired
    ThingServer thingServer;


    //=====================POST========================
    @ApiOperation(value = "Adding a new thing to DataBase", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added thing"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public SederReturneCode addThing(@RequestBody ThingDto thingJpa) {
        return thingServer.addThing(thingJpa);
    }

    //=====================PUT========================

    @ApiOperation(value = "Putting thing on the shelf", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully putted thing on the shelf"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public SederReturneCode putThingOnThePolka(@RequestBody ThingToFromShelf ts) {
        return thingServer.putThingOnTheShelf(ts.thingName, ts.shelfName);
    }

    @ApiOperation(value = "Putting thing from the shelf", response = ThingToFromShelf.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully putted thing from the shelf"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{thingName}", method = RequestMethod.PUT, produces = "application/json")
    public SederReturneCode removeThingFromThePolka(@PathVariable String thingName) {
        return thingServer.removeThingFromTheShelf(thingName);
    }

    //=====================DELITE========================

    @ApiOperation(value = "Delete thing", response = ThingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted thing"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{thingName}", method = RequestMethod.DELETE, produces = "application/json")
    public ThingDto removeThing(@PathVariable String thingName) {
        return thingServer.removeThing(thingName);
    }


    //=====================GET========================
    @ApiOperation(value = "Get thing", response = ThingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received thing"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{thingName}", method = RequestMethod.GET, produces = "application/json")
//    @GetMapping("/thing/{thingName}")
    public DtoForOneThing getThing(@PathVariable String thingName) {
        return thingServer.getThing(thingName);
    }

    @ApiOperation(value = "Get list of all things", response = ThingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received all things"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
//    @GetMapping("/things")
    public List<ThingDto> getAllThings() {
        return thingServer.getAllThings();
    }

    @ApiOperation(value = "Get list of all things that not in the place", response = ThingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received things not in the place"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "withoutplace", method = RequestMethod.GET, produces = "application/json")
//    @GetMapping("/thingsnotinplace")
    public List<ThingDto> getAllThingsNotInThePlace() {
        return thingServer.getAllThingsNotInThePlace();
    }

    @ApiOperation(value = "Get list of all things on the shelf", response = ThingDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received all things on the shelf"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "ontheshelf/{shelfName}", method = RequestMethod.GET, produces = "application/json")
//    @GetMapping("/thingonpolka/{polkaName}")
    public List<ThingDto> getAllThingsByShelf(@PathVariable String shelfName) {
        return thingServer.getAllThingsByShelf(shelfName);
    }




}
