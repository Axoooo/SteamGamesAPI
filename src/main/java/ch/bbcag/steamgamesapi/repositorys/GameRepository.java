package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("SELECT i FROM Game i WHERE i.name LIKE CONCAT('%', :name, '%')")
    Iterable<Game> findByName(@Param("name") String name);

    @Query("SELECT i FROM Game i WHERE i.price LIKE CONCAT('%', :price, '%')")
    Iterable<Game> findByPrice(@Param("price") String price);

    @Query("SELECT i FROM Game i WHERE i.gameDetails LIKE CONCAT('%', :gameDetails, '%')")
    Iterable<Game> findByGameDetails(@Param("gameDetails") String gameDetails);

    @Query("SELECT i FROM Game i WHERE i.releaseDate =:releaseDate ")
    Iterable<Game> findByReleaseDate(@Param("releaseDate") Date releaseDate);

    @Query("SELECT i FROM Game i WHERE i.overallReviews LIKE CONCAT('%', :review, '%')")
    Iterable<Game> findByReviews(@Param("review") String review);

    @Query("SELECT i FROM Game i " +
            "JOIN i.LinkedPublishers p " +
            "WHERE p.name LIKE CONCAT('%', :publisher, '%') ")
    Iterable<Game> findByPublisher(@Param("publisher") String publisher);

    @Query("SELECT i FROM Game i " +
            "JOIN i.linkedDeveloper d " +
            "WHERE d.name LIKE CONCAT('%', :developer, '%') ")
    Iterable<Game> findByDevelopers(@Param("developer") String developer);

    @Query("SELECT i FROM Game i " +
            "JOIN i.linkedGenres g " +
            "WHERE g.genre LIKE CONCAT('%', :genre, '%') ")
    Iterable<Game> findByGenre(@Param("genre") String genre);

}
