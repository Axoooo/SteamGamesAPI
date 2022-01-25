package ch.bbcag.steamgamesapi.repositorys;

import ch.bbcag.steamgamesapi.models.Developer;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer, Integer> {
}
