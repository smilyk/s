package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.ShelfRepository;
import telran.seder.dto.SederReturneCode;
import telran.seder.dto.ShelfDto;
import telran.seder.entities.Cupboard;
import telran.seder.entities.Shelf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service


public class ShelfServerImpl implements ShelfServer {
    @Autowired
    ShelfRepository shelfRepo;

    @Override
    public SederReturneCode addShelf(ShelfDto shelfDto) {
        System.err.println(shelfDto);
        if (shelfRepo.existsById(shelfDto.getNameShelf())) {
            return SederReturneCode.SHELF_EXIST;
        }
        Cupboard cupboard = getCupboardFromShelf(shelfDto);
        List<String> cupboardsNames = shelfRepo.getCupboardsNames();
        if (!cupboardsNames.contains(cupboard.getNameCupboard())) {
            return SederReturneCode.NO_CUPBOARD;
        }
        Shelf shelfJpi = Shelf.builder()
                .nameShelf(shelfDto.getNameShelf())
                .description(shelfDto.getDescription())
                .cupboard(cupboard)
                .fool(false)
                .thing(new ArrayList<>())
                .build();
        shelfRepo.save(shelfJpi);
        return SederReturneCode.OK;
    }

    private Cupboard getCupboardFromShelf(ShelfDto shelfDto) {
        Cupboard cupboard = Cupboard.builder()
                .nameCupboard(shelfDto.getNameCupboard())
                .build();
        return cupboard;
    }

    @Override
    public ShelfDto removeShelf(String shelfName) {
        Shelf shelfJpi = shelfRepo.findById(shelfName).orElse(null);
        if (shelfJpi == null) {
            return null;
        }

        ShelfDto shelfDto = getShelfDtoFromShelf(shelfJpi);
        shelfRepo.delete(shelfJpi);
        return shelfDto;
    }

    private ShelfDto getShelfDtoFromShelf(Shelf shelfJpi) {

        List<String> things = shelfRepo.getThingsNamefromShelf(shelfJpi.getNameShelf());
        ShelfDto shelfDto = ShelfDto.builder()
                .nameShelf(shelfJpi.getNameShelf())
                .description(shelfJpi.getDescription())
                .nameCupboard(shelfJpi.getCupboard().getNameCupboard())
                .things(things)
                .fool(shelfJpi.getFool())
                .build();
        return shelfDto;
    }

    @Override
    public ShelfDto getShelf(String shelfName) {
        Shelf shelfJpi = shelfRepo.findById(shelfName).orElse(null);
        if (shelfJpi == null) {
            return null;
        }
        ShelfDto shelfDto = getShelfDtoFromShelf(shelfJpi);
        return shelfDto;
    }

    @Override
    public List<ShelfDto> getAllShelf() {
       List<Shelf> shelfs = shelfRepo.findAll();
       List<ShelfDto> shelfsDto = shelfs.stream().map(this::getShelfDtoFromShelf).collect(Collectors.toList());
    return shelfsDto;
    }

    @Override
    public SederReturneCode pointShelfFuul(String shelfName) {
        Shelf shelfJpi = shelfRepo.findById(shelfName).orElse(null);
        if (shelfJpi == null){
            return SederReturneCode.NO_SHELF;
        }
        shelfJpi.setFool(true);
        shelfRepo.save(shelfJpi);
        return  SederReturneCode.SHELF_FUUL;
    }

    @Override
    public SederReturneCode pointShelfNotFuul(String shelfName) {
        Shelf shelfJpi = shelfRepo.findById(shelfName).orElse(null);
        if (shelfJpi == null){
            return SederReturneCode.NO_SHELF;
        }
        shelfJpi.setFool(false);
        shelfRepo.save(shelfJpi);
        return  SederReturneCode.SHELF_NOT_FUUL;
    }

    @Override
    public List<ShelfDto> getAllShelfNotFool() {
        return shelfRepo.findAllByFoolFalse().stream()
                .map(this::getShelfDtoFromShelf).collect(Collectors.toList());
    }
}
