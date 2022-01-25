package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Integer>
{
}
