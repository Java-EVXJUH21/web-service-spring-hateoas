package me.code.webserviceshateoas.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import me.code.webserviceshateoas.models.Todo;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
public class TodoDTO extends RepresentationModel<TodoDTO> {

    private String id, title, description;
    private String user;

}
