package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.CupboardRepository;
import telran.seder.dto.CupboardDto;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Cupboard;
import telran.seder.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CupboardServerImpl implements CupboardServer {

    @Autowired
    CupboardRepository cupboardRepo;

    @Override
    public SederReturneCode addCupboard(CupboardDto cupboard) {
        if (cupboardRepo.existsById(cupboard.getNameCupboard())) {
            return SederReturneCode.CUPBOARD_EXIST;
        }
        Room room = getRoomFromCupboard(cupboard);
        List<String> roomNames = cupboardRepo.getAllRoomsByName();
        if (!roomNames.contains(cupboard.getNameRoom())) {
            System.err.println(cupboard.getNameRoom() + "  комната");
            System.err.println(roomNames + " spisok komnat");
            return SederReturneCode.NO_ROOM;
        }

        Cupboard cupboardJpa = Cupboard.builder()
                .nameCupboard(cupboard.getNameCupboard())
                .description(cupboard.getDescription())
                .room(room)
                .polka(new ArrayList<>())
                .photo(new ArrayList<>())
                .fool(false)
                .build();
        cupboardRepo.save(cupboardJpa);
        return SederReturneCode.OK;
    }

    private Room getRoomFromCupboard(CupboardDto cupboard) {
        return Room.builder()
                .nameRoom(cupboard.getNameRoom())
                .build();
    }


    @Override
    public CupboardDto removeCupboard(String cupboardName) {
        Cupboard cupboard = cupboardRepo.findById(cupboardName).orElse(null);
        if (cupboard == null) {
            return null;
        }
        CupboardDto cupboardDto = getCupboardDtoFromCupboard(cupboard);
        cupboardRepo.delete(cupboard);
        return cupboardDto;
    }

    private CupboardDto getCupboardDtoFromCupboard(Cupboard cupboard) {
        String cupboardName = cupboard.getNameCupboard();
        List<String> photoCupboard = cupboardRepo.getPhotoFromCupboard(cupboardName);
        List<String> polka = cupboardRepo.getPolkaFromCupboard(cupboardName);
        CupboardDto cupboardDto = CupboardDto.builder()
                .nameCupboard(cupboardName)
                .description(cupboard.getDescription())
                .fool(cupboard.getFool())
                .namePhoto(photoCupboard)
                .nameShel(polka)
                .nameRoom(cupboard.getRoom().getNameRoom())
                .build();
        return cupboardDto;
    }

    @Override
    public CupboardDto getCupboard(String cupboardName) {
        Cupboard cupboard = cupboardRepo.findById(cupboardName).orElse(null);
        if (cupboard == null) {
            return null;
        }
        return getCupboardDtoFromCupboard(cupboard);
    }

    @Override
    public List<CupboardDto> getCupboardsNotFool() {
        return cupboardRepo.findAllByFoolFalse()
                .stream().map(this::getCupboardDtoFromCupboard).collect(Collectors.toList());
    }

    @Override
    public SederReturneCode moveCupboard(String cupboardName, String roomNameFrom, String roomNameTo) {
        Cupboard cupboard = cupboardRepo.findById(cupboardName).orElse(null);
        if (cupboard == null) {
            return SederReturneCode.NO_CUPBOARD;
        }
        Room roomFrom = cupboard.getRoom();
        if (!cupboard.getRoom().getNameRoom().equals(roomNameFrom)) {
            return SederReturneCode.NO_ROOM;
        }
        if (roomFrom == null) {
            return SederReturneCode.NO_ROOM_FROM;
        }
        List<String> allRoomsByName = cupboardRepo.getAllRoomsByName();
        Room roomTo = Room.builder()
                .nameRoom(roomNameTo)
                .build();
        if (!allRoomsByName.contains(roomNameTo)) {
            return SederReturneCode.NO_ROOM_TO;
        }
        cupboard.setRoom(roomTo);
        this.cupboardRepo.save(cupboard);
        return SederReturneCode.OK;
    }

    @Override
    public List<CupboardDto> getAllCupboards() {
        return cupboardRepo.findAll().stream()
                .map(this::getCupboardDtoFromCupboard).collect(Collectors.toList());
    }

    @Override
    public List<CupboardDto> getAllCupboardsInTheRoom(String roomName) {
        Room room = Room.builder()
                .nameRoom(roomName)
                .build();
        if (room == null) {
            return null;
        }
        return cupboardRepo.findByRoomNameRoom(roomName).stream()
                .map(this::getCupboardDtoFromCupboard).collect(Collectors.toList());
    }

    @Override
    public List<CupboardDto> getAllNotFoolCupboardsInTheRoom(String roomName) {
        Room room = Room.builder()
                .nameRoom(roomName)
                .build();
        if (room == null) {
            return null;
        }
        return cupboardRepo.findAllByFoolFalse().stream()
                .map(this::getCupboardDtoFromCupboard).collect(Collectors.toList());
    }

    public SederReturneCode pointCuppboardFool(String cupboardName) {
        Cupboard cupboardJpa = cupboardRepo.findById(cupboardName).orElse(null);
        if (cupboardJpa == null) {
            return SederReturneCode.NO_CUPBOARD;
        }
        cupboardJpa.setFool(true);
        cupboardRepo.save(cupboardJpa);
        return SederReturneCode.CUPBOARD_FOOL;
    }

    @Override
    public SederReturneCode pointCuppboardNotFool(String cupboardName) {
        Cupboard cupboardJpa = cupboardRepo.findById(cupboardName).orElse(null);
        if (cupboardJpa == null) {
            return SederReturneCode.NO_CUPBOARD;
        }
        cupboardJpa.setFool(false);
        cupboardRepo.save(cupboardJpa);
        return SederReturneCode.CUPBOARD_NOT_FOOL;
    }


}
