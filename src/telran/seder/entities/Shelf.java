package telran.seder.entities;

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
@Table(name="shelf")
public class Shelf {

	@Id
	String nameShelf;
	String description;
	Boolean fool;
	
	@ManyToOne
	@JoinColumn
	Cupboard cupboard;
//	@JsonIgnore
	@OneToMany(mappedBy = "shelf", cascade= CascadeType.REMOVE)
	List<Thing> thing;
}
