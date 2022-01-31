package ch.bbcag.steamgamesapi;

import ch.bbcag.steamgamesapi.controllers.GameController;
import ch.bbcag.steamgamesapi.repositorys.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
@AutoConfigureMockMvc(addFilters = false)
class SteamGamesApiApplicationTests {

    @Autowired
    private MockMvc fakeController;

    @MockBean
    private GameRepository gameRepository;
    private static final String JSON_DOOM = """
            [
                {
                     "id": 1,
                     "url": "https://store.steampowered.com/app/379720/DOOM/",
                     "name": "DOOM",
                     "descriptionSnippet": "Now includes all three premium DLC packs (Unto the Evil, Hell Followed, and Blood fall), maps, modes, and weapons, as well as all feature updates including Arcade Mode, Photo Mode, and the latest Update 6.66, which brings further multiplayer improvements as well as revamps multiplayer progression.",
                     "overallReviews": "Very Positive,(42,550 Reviews)",
                     "releaseDate": "2016-05-12",
                     "achievements": 54,
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

    @Test
    public void checkGet_WithPrice_thenAllGame() throws Exception {

        fakeController.perform(get("/game?category=PRICE&value=19")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json(JSON_DOOM));
    }

}
