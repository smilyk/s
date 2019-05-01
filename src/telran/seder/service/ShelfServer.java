package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.SederReturneCode;
import telran.seder.dto.ShelfDto;

import java.util.List;

@Service
public interface ShelfServer {

	
	SederReturneCode addShelf(ShelfDto pshelfJpa);
	ShelfDto removeShelf(String shelfName);
	ShelfDto getShelf(String shelfName);
	List<ShelfDto> getAllShelf();
	List<ShelfDto> getAllShelfNotFool();
	SederReturneCode pointShelfFuul(String shelfName);
	SederReturneCode pointShelfNotFuul(String shelfName);
}
