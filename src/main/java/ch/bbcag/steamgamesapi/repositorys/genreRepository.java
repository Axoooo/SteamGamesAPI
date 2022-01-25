package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.genre;
import org.springframework.data.repository.CrudRepository;

public interface genreRepository extends CrudRepository<genre, Integer>
{
}
