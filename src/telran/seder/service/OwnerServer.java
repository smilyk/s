package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.SederReturneCode;
import telran.seder.entities.Owner;

import java.util.List;

@Service
public interface OwnerServer {

	SederReturneCode addOwner(Owner owner);
	Owner getOwner(String ownerName);
	List<Owner> getAllOwner();
	Owner removeOwner(String ownerName);
	
}
