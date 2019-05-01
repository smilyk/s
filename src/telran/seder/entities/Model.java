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
@ToString
@Builder
public class Model {
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
