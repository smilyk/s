package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.CupboardDto;
import telran.seder.dto.MoveCupboards;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.service.CupboardServer;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(SederApiConst.API_CUPBOARD)
@Api(value = "onlinestore", description = "methods that collect data about the cupboard ")

public class CupboardController {
    @Autowired
    CupboardServer sederServer;

    @ApiOperation(value = "Adding a new cupboard to DataBase", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public SederReturneCode addCupboard(@RequestBody CupboardDto cupboard) {
        System.err.println(cupboard + " cupboard");
        return sederServer.addCupboard(cupboard);
    }


    @ApiOperation(value = "Moving cupboard from room to room", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully moved cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public SederReturneCode moveCupboard(@RequestBody MoveCupboards mc) {
        System.err.println(mc);
        return sederServer.moveCupboard(mc.nameCupboard, mc.nameRoomFrom, mc.nameRoomTo);
    }

    @ApiOperation(value = "Make point cupboard fool", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully pointed cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "point/fool/{cupboardName}", method = RequestMethod.PUT, produces = "application/json")
    public SederReturneCode pointCuppboardFuul(@PathVariable String cupboardName) {
        return sederServer.pointCuppboardFool(cupboardName);
    }

    @ApiOperation(value = "Make point cupboard NOTfool", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully pointed cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "point/notfool/{cupboardName}", method = RequestMethod.PUT, produces = "application/json")
    public SederReturneCode pointCuppboardNotFuul(@PathVariable String cupboardName) {
        return sederServer.pointCuppboardNotFool(cupboardName);
    }

    //==========DELITE==============
    @ApiOperation(value = "Delete cupboard", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{cupboardName}", method = RequestMethod.DELETE, produces = "application/json")
    public CupboardDto removeCupboard(@PathVariable String cupboardName) {
        return sederServer.removeCupboard(cupboardName);
    }

    //==========++GET++==============
    @ApiOperation(value = "Get cupboard", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{cupboardName}", method = RequestMethod.GET, produces = "application/json")
    public CupboardDto getCupboard(@PathVariable String cupboardName) {
        return sederServer.getCupboard(cupboardName);
    }


    @ApiOperation(value = "Get list of NOTfool cupboards", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received notfool cupboards"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/notfool", method = RequestMethod.GET, produces = "application/json")

    public List<CupboardDto> getCupboardsNotFool() {
        return sederServer.getCupboardsNotFool();
    }

    @ApiOperation(value = "Get list of all cupboards", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received all cupboards"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "all", method = RequestMethod.GET, produces = "application/json")
    public List<CupboardDto> getAllCupboards() {
        return sederServer.getAllCupboards();
    }

    @ApiOperation(value = "Get list of all cupboards in the room", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received all cupboards in the room"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/all/inroom/{roomName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/cupboard/all/inroom/{roomName}")
    public List<CupboardDto> getAllCupboardsInTheRoom(@PathVariable String roomName) {
        return sederServer.getAllCupboardsInTheRoom(roomName);
    }

    @ApiOperation(value = "Get list of NOTfool cupboards in the room", response = CupboardDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received fool cupboards"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "notfool/{roomName}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/cupboard/all/notfuul")
    public List<CupboardDto> getAllNotFuulCupboardsInTheRoom(@PathVariable String roomName) {
        return sederServer.getAllNotFoolCupboardsInTheRoom(roomName);
    }
}
