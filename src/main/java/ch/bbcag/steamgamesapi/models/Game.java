package ch.bbcag.steamgamesapi.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Game {
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
    private String descriptionSnippet;

    @NotBlank(message = "overall_reviews can't be blank")
    @NotNull(message = "overall_reviews is required")
    private String overallReviews;

    @NotNull(message = "release_date is required")
    private Date releaseDate;

    @NotBlank(message = "game_details can't be blank")
    @NotNull(message = "game_details is required")
    private String gameDetails;

    @NotNull(message = "achievements is required")
    private int achievements;

    @NotBlank(message = "game_description can't be blank")
    @NotNull(message = "game_description is required")
    private String game_description;

    @NotBlank(message = "comment can't be blank")
    @NotNull(message = "comment is required")
    private String price;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    private Set<Publisher> LinkedPublishers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_genre",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> linkedGenres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "game_developer",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> linkedDeveloper = new HashSet<>();






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

    public String getDescriptionSnippet() {
        return descriptionSnippet;
    }

    public void setDescriptionSnippet(String description_snippet) {
        this.descriptionSnippet = description_snippet;
    }

    public String getOverallReviews() {
        return overallReviews;
    }

    public void setOverallReviews(String overall_reviews) {
        this.overallReviews = overall_reviews;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date release_date) {
        this.releaseDate = release_date;
    }

    public String getGameDetails() {
        return gameDetails;
    }

    public void setGameDetails(String game_details) {
        this.gameDetails = game_details;
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

    public Set<Genre> getLinkedGenres() {
        return linkedGenres;
    }

    public void setLinkedGenres(Set<Genre> linkedGenre) {
        this.linkedGenres = linkedGenre;
    }

    public Set<Developer> getLinkedDeveloper() {
        return linkedDeveloper;
    }

    public void setLinkedDeveloper(Set<Developer> linkedDeveloper) {
        this.linkedDeveloper = linkedDeveloper;
    }

    public Set<Publisher> getLinkdePublishers() {
        return LinkedPublishers;
    }

    public void setLinkdePublishers(Set<Publisher> publishers) {
        this.LinkedPublishers = publishers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
