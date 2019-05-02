package telran.seder.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Thing implements Serializable {

	@Id

	String nameThing;
	String description;
	Boolean onTheShelf;

	@ManyToOne
	@JoinColumn
	Shelf shelf;
	@OneToMany(mappedBy = "thingPhoto",cascade= CascadeType.REMOVE)
	List<PhotoGallery> photo;
	@OneToMany(mappedBy = "things",cascade= CascadeType.REMOVE)
	List<Model> model;
}
