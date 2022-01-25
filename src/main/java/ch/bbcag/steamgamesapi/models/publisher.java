package ch.bbcag.steamgamesapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id is required")
    private int id;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name is required")
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private game game;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ch.bbcag.steamgamesapi.models.game getGame() {
        return game;
    }

    public void setGame(ch.bbcag.steamgamesapi.models.game game) {
        this.game = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        publisher publisher = (publisher) o;
        return id == publisher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);

    }
}
