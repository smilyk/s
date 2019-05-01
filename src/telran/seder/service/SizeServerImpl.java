package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.SizeRepozitory;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Sizes;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeServerImpl implements SizeServer {
	@Autowired
	SizeRepozitory sizeRepo;

	@Override
	public SederReturneCode addSize(Sizes size) {
		if(sizeRepo.existsById(size.getSize())) {
			return SederReturneCode.SIZE_EXIST;
		}
		sizeRepo.save(size);
		return SederReturneCode.OK;
	}

	@Override
	public Sizes getSize(String sizeName) {
	Sizes size = sizeRepo.findById(sizeName).orElse(null);
	if(size == null) {
		return null;
	}
		return size;
	}

	@Override
	public List<Sizes> getAllSizes() {
		List<Sizes> sizes = sizeRepo.findAll();
		if(sizes.isEmpty()) {
			sizes = new ArrayList<>();
		}
		return sizes;
	}

	@Override
	public Sizes removeSize(String sizeName) {
		Sizes size = sizeRepo.findById(sizeName).orElse(null);
		if(size == null) {
			return null;
		}

		sizeRepo.deleteById(sizeName);
		return size;
	}

	
	
}
