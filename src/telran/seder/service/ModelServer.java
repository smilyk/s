package telran.seder.service;

import org.springframework.stereotype.Service;
import telran.seder.dto.ModelDto;
import telran.seder.dto.SederReturneCode;

import java.util.List;

@Service
public interface ModelServer {
	SederReturneCode addModel(ModelDto modelJpa);
	ModelDto  removeModel(String modelfName);
	ModelDto getModel(String modelName);
	List<ModelDto> getModelBySeazon(String sezonName);
	List<ModelDto> getModelByOwner(String ownerName);
	List<ModelDto> getAllModels();
	List<ModelDto> getModelBySize(String sizeNname);

}
