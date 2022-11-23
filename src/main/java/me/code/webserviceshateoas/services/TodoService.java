package me.code.webserviceshateoas.services;

import me.code.webserviceshateoas.models.Todo;
import me.code.webserviceshateoas.models.User;
import me.code.webserviceshateoas.repositories.TodoRepository;
import me.code.webserviceshateoas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo create(String title, String description, String username) {
        var user = new User(
                UUID.randomUUID().toString(),
                username
        );

        user = userRepository.save(user);

        var todo = new Todo(
                UUID.randomUUID().toString(),
                title, description,
                user
        );

        return todoRepository.save(todo);
    }

    public Optional<Todo> getById(String id) {
        return todoRepository.findById(id);
    }
}
