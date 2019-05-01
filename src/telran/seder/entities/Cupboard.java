package telran.seder.entities;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Cupboard {
	@Id

	String nameCupboard;
	String description;
	Boolean fool;
	@ManyToOne
	@JoinColumn
	Room room;
	@OneToMany(mappedBy = "cupboard", cascade= CascadeType.REMOVE)
	List<Shelf> polka;
	@OneToMany(mappedBy = "cupboardPhoto",cascade= CascadeType.REMOVE)
	List<PhotoGallery> photo;

	
	
	
}
