package me.code.webserviceshateoas.controllers;

import me.code.webserviceshateoas.dtos.UserDTO;
import me.code.webserviceshateoas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable String id
    ) {
        return userRepository
                .findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
