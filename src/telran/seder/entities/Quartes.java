package telran.seder.entities;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Quartes  implements Serializable {
	@Id
	String nameQuartes;
	String description;
//	@JsonIgnore
	@OneToMany(mappedBy = "quartes",cascade= CascadeType.REMOVE)
	private List<Room> room;

}
