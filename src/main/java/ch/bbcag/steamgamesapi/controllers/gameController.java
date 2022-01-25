package ch.bbcag.steamgamesapi.controllers;

import ch.bbcag.steamgamesapi.models.game;
import ch.bbcag.steamgamesapi.repositorys.gameRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/game")
public class gameController {

    @Autowired
    private gameRepository gameRepository;


    @Operation(summary = " Find all items")
    @GetMapping
    public Iterable<game> findAll(){
        return gameRepository.findAll();
    }
}
