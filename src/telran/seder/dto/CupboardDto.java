package telran.seder.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CupboardDto {
	
	String nameCupboard;
	String description;
	String nameRoom;
	Boolean fool;
	List<String> nameShel;
	List<String> namePhoto;
	
}
