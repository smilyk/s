package telran.seder.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import telran.seder.dto.ModelDto;
import telran.seder.dto.PhotoGalleryDto;
import telran.seder.dto.SederApiConst;
import telran.seder.dto.SederReturneCode;
import telran.seder.service.PhotoGalleryServer;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(SederApiConst.API_PHOTO)
@Api(value = "onlinestore", description = "methods that collect data about the photo ")
public class PhotoGalleryController {
    @Autowired
    PhotoGalleryServer photoGalleryServer;


    //================POST================
    @ApiOperation(value = "Adding a new photo to DataBase", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added photo by thing"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/photothing/{thingName}", method = RequestMethod.POST, produces = "application/json")
//	@PostMapping("/photo/{thingName}")
    public SederReturneCode addThingPhoto(@RequestBody PhotoGalleryDto photoJpa, @PathVariable String thingName) {
        return photoGalleryServer.addThingPhoto(photoJpa, thingName);
    }

    @ApiOperation(value = "Adding a new photo to DataBase", response = SederReturneCode.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added photo by cupboard"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/photocupboard/{cupboardName}", method = RequestMethod.POST, produces = "application/json")

//	@PostMapping("/photos/{cupboardName}")
    public SederReturneCode addCupboardPhoto(@RequestBody PhotoGalleryDto photoJpa, @PathVariable String cupboardName) {
        return photoGalleryServer.addCupboardPhoto(photoJpa, cupboardName);
    }


    //================Delite================


    @ApiOperation(value = "Delete photo", response = PhotoGalleryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted photo"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "{photoName}", method = RequestMethod.DELETE, produces = "application/json")
//	@DeleteMapping("/photo/{photoName}")
    public SederReturneCode removPhoto(@PathVariable String photoName) {
        return photoGalleryServer.removPhoto(photoName);
    }

    //================GET================

    @ApiOperation(value = "Get all photo", response = PhotoGalleryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received photo"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<PhotoGalleryDto> getAllPhoto() {
        return photoGalleryServer.getAllPhoto();
    }


    @ApiOperation(value = "Get  photo", response = PhotoGalleryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully received photo"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )

    @RequestMapping(value = "/photo/{namePhoto}", method = RequestMethod.GET, produces = "application/json")
//	@GetMapping("/photo/{namePhoto}")
    public PhotoGalleryDto getPhoto(@PathVariable String namePhoto) {
        return photoGalleryServer.getPhoto(namePhoto);}
    }







