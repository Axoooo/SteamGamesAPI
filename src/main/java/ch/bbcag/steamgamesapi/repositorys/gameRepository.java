package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.game;
import org.springframework.data.repository.CrudRepository;

public interface gameRepository extends CrudRepository<game, Integer> {
}
