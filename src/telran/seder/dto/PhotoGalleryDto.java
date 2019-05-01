package telran.seder.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PhotoGalleryDto {
	String namePhoto;
	String description;
	String nameThing;
	String nameCupboard;
}
