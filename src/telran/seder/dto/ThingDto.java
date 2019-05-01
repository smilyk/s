package telran.seder.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ThingDto {
	
	String nameThing;
	String description;
	Boolean onTheShelf;
	List<String> namePhoto;
	ModelDto model;
	ShelfDto shelf;
	CupboardDto cupboard;
	RoomDto room;
	
}
