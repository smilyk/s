package telran.seder.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomDto {
	
	String nameRoom;
	String description;
	String nameQuartes;
	List<String> cupboards;
	
	
}
