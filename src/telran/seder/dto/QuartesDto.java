package telran.seder.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuartesDto {
	
	String nameQuartes;
	String description;
	List<String> nameRoom;
	
}
