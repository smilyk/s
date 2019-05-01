package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.QuartesRepozitory;
import telran.seder.dto.QuartesDto;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Quartes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuartesServerimpl implements QuartesServer {

	@Autowired
    QuartesRepozitory quartesRepo;

	@Override
	public SederReturneCode addQuartes(QuartesDto quartesDto) {
		if(quartesRepo.existsById(quartesDto.getNameQuartes())){
			return SederReturneCode.QUARTES_EXIST;
		}
		Quartes quartesJpi = Quartes.builder()
                .nameQuartes(quartesDto.getNameQuartes())
                .description(quartesDto.getDescription())
                .room(new ArrayList<>())
                .build();
		quartesRepo.save(quartesJpi);
		return SederReturneCode.OK;
	}

	@Override
	public QuartesDto removeQuartes(String nameQuartes) {
		System.err.println(nameQuartes + " nameQuartes - servise");
		Quartes quartesJpa = quartesRepo.findById(nameQuartes).orElse(null);
		System.err.println(quartesJpa + " quartesJpa - servise");
		if(quartesJpa == null){
		    return null;
        }
        QuartesDto quartesDto = getQuartesDtoFromQuartes(quartesJpa);
        quartesRepo.deleteById(nameQuartes);
		System.err.println("delited");
		return quartesDto;
	}

    private QuartesDto getQuartesDtoFromQuartes(Quartes quartesJpa) {

		List<String> roomNamesFromQuartes = quartesRepo.getRoomNamesFromQuartes(quartesJpa.getNameQuartes());

        QuartesDto quartesDto = QuartesDto.builder()
                .nameQuartes(quartesJpa.getNameQuartes())
                .description(quartesJpa.getDescription())
                .nameRoom(roomNamesFromQuartes)
                .build();
        return quartesDto;
    }


    @Override
	public QuartesDto getQuartes(String nameQuartes) {
        Quartes quartesJpi = quartesRepo.findById(nameQuartes).orElse(null);
        if(quartesJpi==null){
            return null;
        }
        QuartesDto quartesDto= getQuartesDtoFromQuartes(quartesJpi);
		return quartesDto;
	}

	@Override
	public List<QuartesDto> getAllQuartes() {
		List<Quartes> quartesJpi = quartesRepo.findAll();
		List<QuartesDto>quartesDto = quartesJpi.stream().map(this::getQuartesDtoFromQuartes).collect(Collectors.toList());
		return quartesDto;
	}
}
