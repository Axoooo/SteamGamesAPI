package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("SELECT i FROM Game i WHERE i.name LIKE CONCAT('%', :name, '%')")
    Iterable<Game> findByName(String name);

    @Query("SELECT i FROM Game i WHERE i.price LIKE CONCAT('%', :name, '%')")
    Iterable<Game> findByPrice (String price);
}
