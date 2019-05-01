package telran.seder.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MoveThings {
	public String thingName;
	public String polkaFrom;
	public String polkaTo;
}
