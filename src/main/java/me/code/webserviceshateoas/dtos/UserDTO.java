package me.code.webserviceshateoas.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.code.webserviceshateoas.models.Todo;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO extends RepresentationModel<UserDTO> {

    private String id, name;

}
