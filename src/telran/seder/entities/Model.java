package telran.seder.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Entity
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Model  implements Serializable {
	@Id
	String nameModel;
	String color;
	String description;
	String size;
	String seazon;
	String owner;
	@ManyToOne
	@JoinColumn
	Thing things;
}
