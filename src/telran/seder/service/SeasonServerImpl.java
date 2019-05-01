package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.SeasonRepozitory;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Season;

import java.util.List;

@Service
public class SeasonServerImpl implements SeasonServer {
	@Autowired
	SeasonRepozitory seasonRepo;

	@Override
	public SederReturneCode addSeason(Season seazon) {
		if(seasonRepo.existsById(seazon.getSeason())) {
			return SederReturneCode.SEAZON_EXIST;
		}
		seasonRepo.save(seazon);
		return SederReturneCode.OK;
	}

	@Override
	public Season getSeason(String seasonName) {
		Season season = seasonRepo.findById(seasonName).orElse(null);
		if(season == null) {
			return null;
		}
			return season;
	}

	@Override
	public List<Season> getAllSeason() {
		List<Season> seasons = seasonRepo.findAll();
		if(seasons.isEmpty()) {
			seasons = null;
		}
		return seasons;
	}

	@Override
	public Season removeSeason(String seasonName) {
		Season season = seasonRepo.findById(seasonName).orElse(null);
		if(season == null) {
			return null;
		}
		seasonRepo.deleteById(seasonName);
		return season;
	}
	

	
}
