package ch.bbcag.steamgamesapi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id is required")
    private int id;

    @NotBlank(message = "genre can't be blank")
    @NotNull(message = "genre is required")
    private String genre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String name) {
        this.genre = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        genre genres = (genre) o;
        return id == genres.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
