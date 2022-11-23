package me.code.webserviceshateoas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "ws_hateoas_todos")
public class Todo {

    @Id
    private String id;

    private String title, description;

    @ManyToOne
    private User creator;
}
