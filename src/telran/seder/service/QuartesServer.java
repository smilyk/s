package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.QuartesDto;
import telran.seder.dto.SederReturneCode;

import java.util.List;

@Service
public interface QuartesServer {

	SederReturneCode addQuartes( QuartesDto quartesJpa);
	QuartesDto removeQuartes( String nameQuartes);
	QuartesDto getQuartes(String nameQuartes);
	List<QuartesDto> getAllQuartes();
}
