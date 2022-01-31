package ch.bbcag.steamgamesapi.controllers;

import ch.bbcag.steamgamesapi.controllers.GameController;
import ch.bbcag.steamgamesapi.models.Game;
import ch.bbcag.steamgamesapi.repositorys.GameRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
@AutoConfigureMockMvc(addFilters = false)
class GameControllerTest {

    @Autowired
    private MockMvc fakeController;

    @MockBean
    private GameRepository gameRepository;

    Gson gson = new Gson();

    private List<Game> games = gson.fromJson(JSON_DOOM, List.class);

    private static final String JSON_DOOM = """
            [
                {
                     "id": 1,
                     "url": "https://store.steampowered.com/app/379720/DOOM/",
                     "name": "DOOM",
                     "descriptionSnippet": "Now includes all three premium DLC packs (Unto the Evil, Hell Followed, and Blood fall), maps, modes, and weapons, as well as all feature updates including Arcade Mode, Photo Mode, and the latest Update 6.66, which brings further multiplayer improvements as well as revamps multiplayer progression.",
                     "overallReviews": "Very Positive,(42,550 Reviews)",
                     "releaseDate": "2016-05-12",
                     "achievements": 54.0,
                     "game_description": " About This Game Developed by id software, the studio that pioneered the first-person shooter genre and created multiplayer Deathmatch, DOOM returns as a brutally fun and challenging modern-day shooter experience. Relentless demons, impossibly destructive guns, and fast, fluid movement provide the foundation for intense, first-person combat â€“ whether you™re obliterating demon hordes through the depths of Hell in the single-player campaign, or competing against your friends in numerous multiplayer modes. Expand your gameplay experience using DOOM SnapMap game editor to easily create, play, and share your content with the world. STORY: You`ve come here for a reason. The Union Aerospace Corporationâ€™s massive research facility on Mars is overwhelmed by fierce and powerful demons, and only one person stands between their world and ours.  As the lone DOOM Marine, you`he been activated to do one thing kill them all. KEY FEATURES: A Relentless Campaign There is no taking cover or stopping to regenerate health as you beat back Hell`s raging demon hordes.  Combine your arsenal of futuristic and iconic guns, upgrades, movement and an advanced melee system to knock-down, slash, stomp, crush, and blow apart demons in creative and violent ways. Return of id Multiplayer Dominate your opponents in DOOMâ€™s signature, fast-paced arena-style combat. In both classic and all-new game modes, annihilate your enemies utilizing your personal blend of skill, powerful weapons, vertical movement, and unique power-ups that allow you to play as a demon. Endless Possibilities DOOM SnapMap â€“ a powerful, but easy-to-use game and level editor â€“ allows for limitless gameplay experiences on every platform.  Without any previous experience or special expertise, any player can quickly and easily snap together and visually customize maps, add pre-defined or completely custom gameplay, and even edit game logic to create new modes.  Instantly play your creation, share it with a friend, or make it available to players around the world â€“ all in-game with the push of a button. ",
                     "price": 19.99,
                     "linkedGenres": [
                         {
                             "name": "Action"
                         }
                     ],
                     "linkedGameDetails": [
                         {
                             "name": "Single-player"
                         },
                         {
                             "name": "Multi-player"
                         },
                         {
                             "name": "Co-op"
                         },
                         {
                             "name": "Steam Achievements"
                         },
                         {
                             "name": "Steam Trading Cards"
                         },
                         {
                             "name": "Partial Controller Support"
                         },
                         {
                             "name": "Steam Cloud"
                         }
                     ],
                     "linkedDeveloper": [
                         {
                             "name": "id Software"
                         }
                     ],
                     "linkedPublishers": [
                         {
                             "name": "Bethesda Softworks"
                         }
                     ]
                 }
            ]
            """;


    //Tests for Get games by Id

    @Test
    public void checkGet_WithId_thenIsFound() throws Exception {
        Game game = new Game();
        game.setId(12);
        game.setName("TestGame");

        when(gameRepository.findById(12)).thenReturn(Optional.of(game));

        fakeController.perform(get("/game/12")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 12,
                            "name": "TestGame"
                        }
                        """));
    }

    @Test
    public void checkGet_WithId_thenNotFound() throws Exception {
        when(gameRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        fakeController.perform(get("/game/234")
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    //Tests for Get games by Category

    //Tests for Get games by Name
    @Test
    public void checkGet_WithName_thenAllGamesWithName() throws Exception {
        when(gameRepository.findByName("DOOM")).thenReturn(games);
        fakeController.perform(get("/game?category=NAME&value=DOOM")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));
    }


    //Tests for Get games by Price
    @Test
    public void checkGet_WithPrice_thenAllGamesWithPrice() throws Exception {
        when(gameRepository.findByPrice("19")).thenReturn(games);
        fakeController.perform(get("/game?category=PRICE&value=19")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));
    }


    //Tests for Get games by Reviews

    @Test
    public void checkGet_WithReviews_thenAllGamesWithReviews() throws Exception {
        when(gameRepository.findByReviews("Very Positive")).thenReturn(games);
        fakeController.perform(get("/game?category=REVIEWS&value=Very Positive")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));
    }

    //Tests for Get games by GameDetails

    @Test
    public void checkGet_WithGamDetails_thenAllGamesWithGamDetails() throws Exception {
        when(gameRepository.findByGameDetails("Single-player")).thenReturn(games);
        fakeController.perform(get("/game?category=GAME_DETAILS&value=Single-player")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));

    }

    //Tests for Get games by Genre

    @Test
    public void checkGet_WithGenre_thenAllGamesWithGenre() throws Exception {
        when(gameRepository.findByGenre("Action")).thenReturn(games);
        fakeController.perform(get("/game?category=GENRE&value=Action")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));

    }

    //Tests for Get games by Release Date

    @Test
    public void checkGet_WithReleaseDate_thenAllGamesWithReleaseDate() throws Exception {
        when(gameRepository.findByReleaseDate(Date.valueOf("2016-05-12"))).thenReturn(games);
        fakeController.perform(get("/game?category=RELEASE_DATE&value=2016-05-12")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));

    }

    //Tests for Get games by Developers

    @Test
    public void checkGet_WithDevelopers_thenAllGamesWithDevelopers() throws Exception {
        when(gameRepository.findByDevelopers("id Software")).thenReturn(games);
        fakeController.perform(get("/game?category=DEVELOPERS&value=id Software")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));

    }

    //Tests for Get games by Publisher

    @Test
    public void checkGet_WithPublisher_thenAllGamesWithPublisher() throws Exception {
        when(gameRepository.findByPublisher("Bethesda Softworks")).thenReturn(games);
        fakeController.perform(get("/game?category=PUBLISHER&value=Bethesda Softworks")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));

    }

}
