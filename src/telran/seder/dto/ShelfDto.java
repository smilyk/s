package telran.seder.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ShelfDto {
	
	String nameShelf;
	String description;
	String nameCupboard;
	Boolean fool;
	List<String> things;
	
}

