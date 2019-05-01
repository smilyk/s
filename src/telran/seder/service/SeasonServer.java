package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Season;

import java.util.List;

@Service
public interface SeasonServer {
	SederReturneCode addSeason(Season season);
	Season getSeason(String seasonName);
	List<Season> getAllSeason();
	Season removeSeason(String seasonName);
	
}
