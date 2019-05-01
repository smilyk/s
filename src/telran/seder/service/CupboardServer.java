package telran.seder.service;

import telran.seder.dto.CupboardDto;
import telran.seder.dto.SederReturneCode;

import java.util.List;


public interface CupboardServer {

	SederReturneCode addCupboard(CupboardDto cupboardJpa);
	CupboardDto removeCupboard(String cupboardName);
	CupboardDto getCupboard(String cupboardName);
	List<CupboardDto> getCupboardsNotFool();
	SederReturneCode moveCupboard(String cupboardName, String roomNameFrom, String roomNameTo);
	List<CupboardDto> getAllCupboards();
	List<CupboardDto> getAllCupboardsInTheRoom(String roomName);
	List<CupboardDto> getAllNotFoolCupboardsInTheRoom(String roomName);
	SederReturneCode pointCuppboardFool(String cupboardName);
	SederReturneCode pointCuppboardNotFool(String cupboardName);

}
