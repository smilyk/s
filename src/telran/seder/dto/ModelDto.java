package telran.seder.dto;

import lombok.*;





@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ModelDto {
	String nameModel;
	String description;
	String color;
	String size;
	String seazon;
	String owner;
	ThingDto thing;
}
