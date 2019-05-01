package telran.seder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.seder.dao.OwnerRepozitory;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Owner;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServerimpl implements OwnerServer {

	@Autowired
	OwnerRepozitory ownerRepo;

	@Override
	public SederReturneCode addOwner(Owner owner) {
		if(ownerRepo.existsById(owner.getOwner())) {
			 return SederReturneCode.OWNER_EXIST;
		}
		ownerRepo.save(owner);
		return SederReturneCode.OK;
	}

	@Override
	public Owner getOwner(String ownerName) {
		Owner owner = ownerRepo.findById(ownerName).orElse(null);
		if(owner == null) {
			return null;
		}
			return owner;
	}

	@Override
	public List<Owner> getAllOwner() {
		List<Owner> owners = ownerRepo.findAll();
		if(owners.isEmpty()) {
			owners = new ArrayList<>();
		}
		return owners;
	}

	@Override
	public Owner removeOwner(String ownerName) {
	Owner owner = ownerRepo.findById(ownerName).orElse(null);
		if(owner == null) {
			return null;
		}
		ownerRepo.deleteById(ownerName);
		return owner;
	}

	
}
