package telran.seder.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DtoForOneThing {
    String nameThing;
    String description;
    Boolean onTheShelf;
    List<String> namePhoto;
    List<String> nameModel;
    String shelf;
    String cupboard;
    String room;
}
