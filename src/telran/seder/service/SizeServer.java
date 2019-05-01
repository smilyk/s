package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Sizes;

import java.util.List;

@Service
public interface SizeServer {
SederReturneCode addSize(Sizes size);
	
	Sizes getSize(String sizeName);
	List<Sizes> getAllSizes();
	Sizes removeSize(String sizeName);
	
	
}
