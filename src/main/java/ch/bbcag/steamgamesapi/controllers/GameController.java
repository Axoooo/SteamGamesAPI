package ch.bbcag.steamgamesapi.controllers;

import ch.bbcag.steamgamesapi.models.Game;
import ch.bbcag.steamgamesapi.repositorys.GameRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Operation(summary = "Find an game by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))}),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content)})
    @GetMapping(path = "{id}")
    public Game findById(@Parameter(description = "Id of Game to get") @PathVariable Integer id) {
        try {
            return gameRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game could not be found");
        }
    }

    @Operation(summary = "Find an game by Category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Game.class))}),
            @ApiResponse(responseCode = "404", description = "Game not found",
                    content = @Content)})
    @GetMapping
    public Iterable<Game> findByCategory(@Parameter(description = "Category to search by") @RequestParam(required = false) Category category,
                                         @Parameter(description = "Value to search the given categroy") @RequestParam(required = false) String value) {
        if (category == null) return gameRepository.findAll();
        switch (category) {
            case NAME:
                return gameRepository.findByName(value);
            case REVIEWS:
                return gameRepository.findByReviews(value);
            case RELEASE_DATE:
                return gameRepository.findByReleaseDate(Date.valueOf(value));
            case GAME_DETAILS:
                return gameRepository.findByGameDetails(value);
            case PRICE:
                return gameRepository.findByPrice(value);
            case PUBLISHER:
                return gameRepository.findByPublisher(value);
            case DEVELOPERS:
                return gameRepository.findByDevelopers(value);
            case GENRE:
                return gameRepository.findByGenre(value);
        }
        return new ArrayList<>();
    }

    private enum Category {
        NAME,
        PRICE,
        REVIEWS,
        GAME_DETAILS,
        GENRE,
        RELEASE_DATE,
        DEVELOPERS,
        PUBLISHER
    }
}
