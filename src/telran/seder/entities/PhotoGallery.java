package telran.seder.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PhotoGallery {

	@Id
	
	String namePhoto;
	String description;

	
	@ManyToOne
	@JoinColumn
	Cupboard cupboardPhoto;
	
	@ManyToOne
	@JoinColumn
	Thing thingPhoto;
	
}
