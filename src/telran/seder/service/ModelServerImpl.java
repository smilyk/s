package telran.seder.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.ModelRepository;
import telran.seder.dto.*;
import telran.seder.entities.Cupboard;
import telran.seder.entities.Model;

import telran.seder.entities.Shelf;
import telran.seder.entities.Thing;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServerImpl implements ModelServer {

    @Autowired
    ModelRepository modelRepo;


    @Override
    public SederReturneCode addModel(ModelDto modelDto) {
        if (modelRepo.existsById(modelDto.getNameModel())) {
            return SederReturneCode.MODEL_EXIST;
        }
        Model model = getModelFromDto(modelDto);
        if (model == null) {
            return SederReturneCode.THING_OR_MODEL_WRONG;
        }
        modelRepo.save(model);
        return SederReturneCode.OK;
    }

    private Model getModelFromDto(ModelDto modelDto) {

        Thing thing = getThingFromModelDto(modelDto);
        String thingName = modelDto.getThing().getNameThing();
        Boolean test = testThing(thingName);
        if (!test) {
            return null;
        }
        Model model = Model.builder()
                .things(thing)
                .description(modelDto.getDescription())
                .seazon(modelDto.getSeazon())
                .owner(modelDto.getOwner())
                .nameModel(modelDto.getNameModel())
                .description(modelDto.getDescription())
                .color(modelDto.getColor())
                .size(modelDto.getSize())
                .build();

        return model;
    }

    private Thing getThingFromModelDto(ModelDto modelDto) {

        String thingName = modelDto.getThing().getNameThing();
        Boolean test = testThing(thingName);
        if (!test) {
            return null;
        }
        String shelfName = modelRepo.getShelfyNameThing(thingName);
        test = testSshelf(shelfName);
        if (!test) {

            shelfName = null;
        }
        Shelf shelf = Shelf.builder()
                .nameShelf(shelfName)
                .build();
        Thing thing = Thing.builder()
                .nameThing(thingName)
                .onTheShelf(modelRepo.getOnTheShelfaByNameThing(thingName))
                .shelf(shelf)
                .description(modelRepo.getDescriptionNameThing(thingName))
                .build();

        return thing;
    }

    private Boolean testThing(String thingName) {
        List<String> thingsName = modelRepo.getStringThingsName();
        if (thingsName.contains(thingName)) {
            return true;
        }
        return false;
    }

    private boolean testSshelf(String shelfName) {
        List<String> shelfsName = modelRepo.getStringShelfsName();
        if (shelfsName.contains(shelfName)) {
            return true;
        }
        return false;
    }


    @Override
    public ModelDto removeModel(String modelfName) {
        Model model = modelRepo.findById(modelfName).orElse(null);
        if (model == null) {
            return null;
        }
        ModelDto modelDto = getModelDtoFromModel(model);
        modelRepo.delete(model);
        return modelDto;
    }

    @Override
    public ModelDto getModel(String modelName) {
        Model model = modelRepo.findById(modelName).orElse(null);

        if (model == null) {
            return null;
        }
        ModelDto modelDto = getModelDtoFromModel(model);

        return modelDto;
    }

    private ModelDto getModelDtoFromModel(Model model) {
        Thing thing = getThingFromModel(model);

//        if (thing == null) {
//            return null;
//        }
        ThingDto thingDto = getThingDtoFromThing(thing);
        ModelDto modelDto = ModelDto.builder()
                .size(model.getSize())
                .seazon(model.getSeazon())
                .owner(model.getOwner())
                .nameModel(model.getNameModel())
                .description(model.getDescription())
                .color(model.getColor())
                .thing(thingDto)
                .build();
        if (modelDto == null) {
            return null;
        }
        return modelDto;
    }

    private ThingDto getThingDtoFromThing(Thing thing) {
        ThingDto thingDto = null;
        if(thing == null){
            return thingDto;
        }
        ShelfDto shelfDto = getShelfFromThing(thing);
        List<String> photo = modelRepo.getPhotoNameByThing(thing.getNameThing());
        CupboardDto cupboard = getCupboardFtomShelf(shelfDto);
         thingDto = ThingDto.builder()
                .onTheShelf(thing.getOnTheShelf())
                .description(thing.getDescription())
                .nameThing(thing.getNameThing())
                .shelf(shelfDto)
                .namePhoto(photo)
                .cupboard(cupboard)
                .build();
        return thingDto;
    }

    private CupboardDto getCupboardFtomShelf(ShelfDto shelfDto) {
        CupboardDto cupboardDto = null;
        if( shelfDto == null){
            return cupboardDto ;
        }
        Cupboard cupboard = Cupboard.builder()
                .nameCupboard(shelfDto.getNameCupboard())
                .build();

            cupboardDto = CupboardDto.builder()
                    .nameCupboard(cupboard.getNameCupboard())
                    .build();

        return cupboardDto;
    }


    private ShelfDto getShelfFromThing(Thing thing) {
        ShelfDto shelfDto = null;
        if(thing == null){
           return shelfDto;
       }
       Boolean onTheShelf = thing.getOnTheShelf();
       String shelfName = null;
        Shelf shelf = null;

       if(onTheShelf){
           shelfName = thing.getShelf().getNameShelf();
           shelf = Shelf.builder()
                   .nameShelf(shelfName)
                   .build();
           shelfDto = getShelfDtoFromShelf(shelf);
       }

        return shelfDto;
    }

    private ShelfDto getShelfDtoFromShelf(Shelf shelfJpi) {
        ShelfDto shelfDto = ShelfDto.builder()
                .nameShelf(shelfJpi.getNameShelf())
                .build();
        return shelfDto;
    }


    private Thing getThingFromModel(Model model) {
        Thing thing = model.getThings();
        return thing;
    }

    @Override
    public List<ModelDto> getModelBySeazon(String sezonName) {
        List<Model> models = modelRepo.findBySeazon(sezonName);
        List<ModelDto> modelsDto = models.stream().map(this::getModelDtoFromModel)
                .collect(Collectors.toList());
        return modelsDto;
    }

    @Override
    public List<ModelDto> getModelByOwner(String ownerName) {
        List<Model> models = modelRepo.findByOwner(ownerName);
        List<ModelDto> modelsDto = models.stream().map(this::getModelDtoFromModel)
                .collect(Collectors.toList());
        return modelsDto;
    }

    @Override
    public List<ModelDto> getModelBySize(String sizeNname) {
        List<Model> models = modelRepo.findBySize(sizeNname);
        List<ModelDto> modelsDto = models.stream().map(this::getModelDtoFromModel)
                .collect(Collectors.toList());
        return modelsDto;
    }


    @Override
    public List<ModelDto> getAllModels() {
        List<Model> models = modelRepo.findAll();
        List<ModelDto> modelsDto = models.stream().map(this::getModelDtoFromModel)
                .collect(Collectors.toList());
        return modelsDto;
    }


}
