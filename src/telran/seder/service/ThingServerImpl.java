package telran.seder.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.ThingRepository;
import telran.seder.dto.*;
import telran.seder.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ThingServerImpl implements ThingServer {
    @Autowired
    ThingRepository thingRepo;

    @Override
    public SederReturneCode addThing(ThingDto thingDto) {

        if (thingRepo.existsById(thingDto.getNameThing())) {
            return SederReturneCode.THIND_EXIST;
        }
        Thing thing = getThingFromthingDto(thingDto);

        thingRepo.save(thing);

        return SederReturneCode.OK;
    }

    private Thing getThingFromthingDto(ThingDto thingDto) {
        Thing thing = Thing.builder()
                .nameThing(thingDto.getNameThing())
                .description(thingDto.getDescription())
                .model(null)
                .photo(new ArrayList<>())
                .onTheShelf(false)
                .build();
        return thing;
    }


    @Override
    public ThingDto removeThing(String thingName) {
        Thing thing = thingRepo.findById(thingName).orElse(null);
        if(thing == null){
            return null;
        }
        ThingDto thingDto = getThingDtoFromThing(thing);
        thingRepo.delete(thing);
        return thingDto;
    }

    @Override
    public DtoForOneThing getThing(String thingName) {
        Thing thing = thingRepo.findById(thingName).orElse(null);
        if (thing == null) {
            return null;
        }

        List<String> nameModel = thingRepo.getNamesOfModelFromTingName(thing.getNameThing());
            List<String> photo = thingRepo.getNamesOfPhotoFromThing(thingName);
            Boolean onTheShelf = thing.getOnTheShelf();

        if (!onTheShelf) {
            DtoForOneThing dtoForOneThing = DtoForOneThing.builder()
                        .nameModel(nameModel)
                        .cupboard(null)
                        .description(thing.getDescription())
                        .namePhoto(photo)
                        .nameThing(thingName)
                        .onTheShelf(thing.getOnTheShelf())
                        .shelf(null)
                        .room(null)
                        .build();
                return dtoForOneThing;
            }
            CupboardDto cupboardDto = getCupboardDtoFromThing(thing);
            String nameCupboard = cupboardDto.getNameCupboard();
            String nameRoom = cupboardDto.getNameRoom();

            DtoForOneThing dtoForOneThing = DtoForOneThing.builder()
                    .nameThing(thingName)
                    .cupboard(nameCupboard)
                    .description(thing.getDescription())
                    .namePhoto(photo)
                    .nameModel(nameModel)
                    .onTheShelf(thing.getOnTheShelf())
                    .shelf(thing.getShelf().getNameShelf())
                    .room(nameRoom)
                    .build();
            if (dtoForOneThing == null) {
                return null;
            }

            return dtoForOneThing;

    }

    @Override
    public ThingDto getAllForOneThing(String thingName) {
        Thing thing = thingRepo.findById(thingName).orElse(null);
        if (thing == null) {
            return null;
        }
        if(!thing.getOnTheShelf()){
            ThingDto thingDto = ThingDto.builder()
                    .nameThing(thingName)
                    .description(thing.getDescription())
                    .onTheShelf(false)
                    .build();
            return thingDto;
        }
        ThingDto thingDto = getThingDtoFromThing(thing);
        return thingDto;
    }

    private ThingDto getThingDtoFromThing(Thing thing) {
        CupboardDto cupboardDto = null;
        ShelfDto shelfDto = null;
        RoomDto room = null;
        List<String> photo = thingRepo.getNamesOfPhotoFromThing(thing.getNameThing());
        if(thing.getOnTheShelf() != false) {
            cupboardDto = getCupboardDtoFromThing(thing);
            shelfDto = getShelfDtoFromThing(thing);
            room = getRoomDtoFromThing(thing);
//            List<ModelDto> model = getModeDtolFromThing(thing);

        }
        ThingDto thingDto = ThingDto.builder()
                .nameThing(thing.getNameThing())
                .cupboard(cupboardDto)
                .description(thing.getDescription())
                .namePhoto(photo)
                .onTheShelf(thing.getOnTheShelf())
                .shelf(shelfDto)
                .room(room)
                .build();
        return thingDto;
    }

    private RoomDto getRoomDtoFromThing(Thing thing) {
        Shelf shelfJpi = thing.getShelf();
        if(shelfJpi == null){
            return null;
        }
        Cupboard cupboard = shelfJpi.getCupboard();
        if(cupboard == null){
            return null;
        }
        Room room = cupboard.getRoom();
        if(room == null){
            return null;
        }

        List<String> cupboardsList = thingRepo.getCupboardsNameFromShelf(shelfJpi.getNameShelf());
        RoomDto roomDto = RoomDto.builder()
                .nameRoom(room.getNameRoom())
                .cupboards(cupboardsList)
                .nameQuartes(room.getQuartes().getNameQuartes())
                .description(room.getDescription())
                .build();
        if(roomDto == null){
            return null;
        }
        return roomDto;
    }

    private CupboardDto getCupboardDtoFromThing(Thing thing) {
      Shelf shelfJpi = thing.getShelf();
      if(shelfJpi == null){
          return null;
      }
        Cupboard cupboard = shelfJpi.getCupboard();
        if(cupboard == null){
            return null;
        }
        List<String> photos = thingRepo.getNamesOfPhotoFromThing(thing.getNameThing());
        List<String> shelfs = thingRepo.getShelfFromCupboards(cupboard.getNameCupboard());
        CupboardDto cupboardDto =
                CupboardDto.builder()
                        .nameCupboard(cupboard.getNameCupboard())
                        .fool(cupboard.getFool())
                        .namePhoto(photos)
                        .nameShel(shelfs)
                        .description(cupboard.getDescription())
                        .nameRoom(cupboard.getRoom().getNameRoom())
                        .build();
        if(cupboardDto == null){
            return null;
        }
        return cupboardDto;
    }

    private ShelfDto getShelfDtoFromThing(Thing thing) {
        Shelf shelfJpi = thing.getShelf();
        if (shelfJpi == null) {
            return null;
        }

        List<String> thingsNames = thingRepo.getNamesOfThingsOnTheShelf(shelfJpi.getNameShelf());
        ShelfDto shelfDto = ShelfDto.builder()
                .description(shelfJpi.getDescription())
                .nameCupboard(shelfJpi.getCupboard().getNameCupboard())
                .things(thingsNames)
                .nameShelf(shelfJpi.getNameShelf())
                .fool(shelfJpi.getFool())
                .build();
        if (shelfDto == null) {
            return null;
        }
        return shelfDto;
    }

    private ModelDto getModelDtoFromModel(Model model) {
        ThingDto thingDto = null;

        if(model.getThings() == null){
            thingDto = null;
        }else{
            Thing thing = model.getThings();
            CupboardDto cupboard = getCupboardDtoFromThing(thing);
            List<String> photo = getPhotoDtoFromThing(thing);
            ShelfDto shelfDto = getShelfDtoFromThing(thing);
            RoomDto room = getRoomDtoFromThing(thing);
            thingDto = ThingDto.builder()
                    .cupboard(cupboard)
                    .namePhoto(photo)
                    .shelf(shelfDto)
                    .room(room)
                    .nameThing(model.getThings().getNameThing())
                    .description(model.getThings().getDescription())
                    .onTheShelf(model.getThings().getOnTheShelf())
                    .build();
        }


        ModelDto modelDto = ModelDto.builder()
                .size(model.getSize())
                .seazon(model.getSeazon())
                .owner(model.getOwner())
                .nameModel(model.getNameModel())
                .description(model.getDescription())
                .color(model.getColor())
                .thing(thingDto)
                .build();
        return modelDto;
    }

    private List<String> getPhotoDtoFromThing(Thing thing) {
        List<PhotoGallery> photosFromThing = thing.getPhoto();
        List<String> photosNameFromThing = null;
        if(photosFromThing != null){
            photosNameFromThing = photosFromThing.stream().map(p->p.getNamePhoto())
                    .collect(Collectors.toList());
        }
        return photosNameFromThing;
    }

    private List<Model> getModelFromThing(Thing thing) {
        List<Model> model = thing.getModel();
        if(model == null){
            return null;
        }
        return model;
    }

    @Override
    public List<ThingDto> getAllThings() {
       List<Thing> things = thingRepo.findAll();
       if(things.size()==0){
           return null;
       }
       List<ThingDto> thingsDto = things.stream().map(this::getThingDtoFromThing)
               .collect(Collectors.toList());
       if(thingsDto.size() == 0){
           return null;
       }
        return thingsDto;
    }

    @Override
    public List<ThingDto> getAllThingsByShelf(String nameShelfs) {
       List<String> namesOfThings = thingRepo.getNamesOfThingsOnTheShelf(nameShelfs);
       List<Thing> things = thingRepo.findAllById(namesOfThings);

       List<ThingDto> thingsDto = things.stream()
               .map(this::getThingDtoFromThing)
               .collect(Collectors.toList());
        System.err.println(thingsDto+ "  kkk");
        return thingsDto;
    }

    @Override
    public List<ThingDto> getAllThingsNotInThePlace() {
        List<Thing> things = thingRepo.findAllByonTheShelfFalse();
        if(things.size()==0){
            return null;
        }
        List<ThingDto> thingsDto = things.stream()
                .map(this::getThingDtoFromThing).collect(Collectors.toList());
        return thingsDto;
    }

    @Override
    public SederReturneCode putThingOnTheShelf(String thingName, String shelfName) {
       Thing thing = thingRepo.findById(thingName).orElse(null);

        if(thing == null){
            return SederReturneCode.NO_THINGS;
        }
        Boolean testShelf = thing.getOnTheShelf();
        if( testShelf){
            return SederReturneCode.THING_ALREADY_ON_THE_SHELF;
        }

        List<String> shelfsName = thingRepo.getShelfsNameFromThing();


        if(!shelfsName.contains(shelfName)){
            return SederReturneCode.NO_SHELF;
        }

        Shelf shelfJpi = Shelf.builder()
                .nameShelf(shelfName)
                .description(thingRepo.getDescriptionShelfFromThing(shelfName))
                .fool(thingRepo.getFoolFromShelf(shelfName))
                .build();
       testShelf = shelfJpi.getFool();


        if(testShelf == true){
            return SederReturneCode.SHELF_FUUL;
        }
        thing.setShelf(shelfJpi);
        thing.setOnTheShelf(true);
        thingRepo.save(thing);
        return SederReturneCode.THING_ON_THE_PLACE;
    }



    @Override
    public SederReturneCode removeThingFromTheShelf(String thingName) {
        Thing thing = thingRepo.findById(thingName).orElse(null);
        if(thing == null){
            return SederReturneCode.NO_THINGS;
        }
        thing.setOnTheShelf(false);
        thing.setShelf(null);
        thingRepo.save(thing);
        return SederReturneCode.OK;
    }

}
