package telran.seder.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Room implements Serializable {

	@Id
	
	String nameRoom;
	String description;
	@ManyToOne
	@JoinColumn
	Quartes quartes;
//	@JsonIgnore
	@OneToMany(mappedBy = "room",cascade= CascadeType.REMOVE)
	List<Cupboard> cupboard;
	
	
}
