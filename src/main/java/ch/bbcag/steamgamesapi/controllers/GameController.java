package ch.bbcag.steamgamesapi.controllers;

import ch.bbcag.steamgamesapi.models.Game;
import ch.bbcag.steamgamesapi.repositorys.GameRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public Iterable<Game> findByName(@Parameter(description = "search by Name the Game") @RequestParam String name) {
        if (StringUtils.isNotBlank(name)) {
            return gameRepository.findByName(name);
        } else {
            return gameRepository.findAll();
        }
    }
    @GetMapping("/$")
    public Iterable<Game> FindbyPrice(@Parameter(description = "search by Price the Game") @RequestParam String price){
        return gameRepository.findByPrice(price);
    }
}
