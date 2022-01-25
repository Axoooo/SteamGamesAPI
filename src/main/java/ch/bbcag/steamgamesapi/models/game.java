package ch.bbcag.steamgamesapi.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "id is required")
    private int id;

    @NotBlank(message = "url can't be blank")
    @NotNull(message = "url is required")
    private String url;

    @NotBlank(message = "name can't be blank")
    @NotNull(message = "name is required")
    private String name;

    @NotBlank(message = "description_snippet can't be blank")
    @NotNull(message = "description_snippet is required")
    private String description_snippet;

    @NotBlank(message = "overall_reviews can't be blank")
    @NotNull(message = "overall_reviews is required")
    private String overall_reviews;

    @NotNull(message = "release_date is required")
    private Date release_date;

    @NotBlank(message = "game_details can't be blank")
    @NotNull(message = "game_details is required")
    private String game_details;

    @NotNull(message = "achievements is required")
    private int achievements;

    @NotBlank(message = "game_description can't be blank")
    @NotNull(message = "game_description is required")
    private String game_description;

    @NotBlank(message = "comment can't be blank")
    @NotNull(message = "comment is required")
    private String price;

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<genre> linkedGenre = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_developer",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<developer> linkedDeveloper = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_snippet() {
        return description_snippet;
    }

    public void setDescription_snippet(String description_snippet) {
        this.description_snippet = description_snippet;
    }

    public String getOverall_reviews() {
        return overall_reviews;
    }

    public void setOverall_reviews(String overall_reviews) {
        this.overall_reviews = overall_reviews;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getGame_details() {
        return game_details;
    }

    public void setGame_details(String game_details) {
        this.game_details = game_details;
    }

    public int getAchievements() {
        return achievements;
    }

    public void setAchievements(int achievements) {
        this.achievements = achievements;
    }

    public String getGame_description() {
        return game_description;
    }

    public void setGame_description(String game_description) {
        this.game_description = game_description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Set<genre> getLinkedGenre() {
        return linkedGenre;
    }

    public void setLinkedGenre(Set<genre> linkedGenre) {
        this.linkedGenre = linkedGenre;
    }

    public Set<developer> getLinkedDeveloper() {
        return linkedDeveloper;
    }

    public void setLinkedDeveloper(Set<developer> linkedDeveloper) {
        this.linkedDeveloper = linkedDeveloper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        game game = (game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
