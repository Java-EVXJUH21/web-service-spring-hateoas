package me.code.webserviceshateoas.controllers;

import lombok.Getter;
import lombok.Setter;
import me.code.webserviceshateoas.dtos.TodoDTO;
import me.code.webserviceshateoas.models.Todo;
import me.code.webserviceshateoas.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<EntityModel<TodoDTO>> create(
            @PathVariable String id
    ) {

        return todoService
                .getById(id)
                .map(value -> new TodoDTO(
                        value.getId(),
                        value.getTitle(),
                        value.getDescription(),
                        value.getCreator().getId()
                ))
                .map(value -> {
                    var methodOn = WebMvcLinkBuilder
                            .methodOn(UserController.class)
                            .getUser(value.getUser());

                    var link = WebMvcLinkBuilder
                            .linkTo(methodOn)
                            .withRel("user");
                    value.add(link);

                    return EntityModel.of(value);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/todo")
    public ResponseEntity<EntityModel<TodoDTO>> create(
            @RequestBody TodoInput input
    ) {
        var todo = todoService.create(input.title, input.description, input.username);

        var dto = new TodoDTO(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreator().getId()
        );
        var methodOn = WebMvcLinkBuilder
                .methodOn(UserController.class)
                .getUser(dto.getUser());

        var link = WebMvcLinkBuilder
                .linkTo(methodOn)
                .withRel("user");
        dto.add(link);

        return ResponseEntity.ok(EntityModel.of(dto));
    }

    @Getter
    @Setter
    public static class TodoInput {
        private String title, description, username;
    }

}
