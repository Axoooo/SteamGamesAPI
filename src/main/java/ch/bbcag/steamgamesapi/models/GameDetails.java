package ch.bbcag.steamgamesapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class GameDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id is required")
    @JsonBackReference
    private int id;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name is required")
    private String name;

    @ManyToMany(mappedBy = "linkedGameDetails")
    @JsonBackReference
    private Set<Game> linkedGames = new HashSet<>();

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

    public Set<Game> getLinkedGames() {
        return linkedGames;
    }

    public void setLinkedGames(Set<Game> linkedGames) {
        this.linkedGames = linkedGames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDetails genres = (GameDetails) o;
        return id == genres.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
