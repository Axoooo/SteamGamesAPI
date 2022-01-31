package ch.bbcag.steamgamesapi;

import ch.bbcag.steamgamesapi.controllers.GameController;
import ch.bbcag.steamgamesapi.repositorys.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameController.class)
@AutoConfigureMockMvc(addFilters = false)
class SteamGamesApiApplicationTests {

    @Autowired
    private MockMvc fakeController;

    @MockBean
    private GameRepository gameRepository;
    private static final String JSON_BATTELTECH = """
            [
                {
                    "id": 2,
                    "url": "https://store.steampowered.com/app/637090/BATTLETECH/",
                    "name": "BATTLETECH",
                    "descriptionSnippet": "Take command of your own mercenary outfit of 'Mechs and the MechWarriors that pilot them, struggling to stay afloat as you find yourself drawn into a brutal interstellar civil war.",
                    "overallReviews": "Mostly Positive,(7,030),- 71% of the 7,030 user reviews for this game are positive.",
                    "releaseDate": "2018-04-24",
                    "gameDetails": "Single-player,Multi-player,Online Multi-Player,Cross-Platform Multiplayer,Steam Achievements,Steam Trading Cards,Steam Cloud",
                    "achievements": 128,
                    "game_description": " About This Game  From original BATTLETECH/MechWarrior creator Jordan Weisman and the developers of the award-winning Shadowrun Returns series comes the next-generation of turn-based tactical 'Mech combat. The year is 3025 and the galaxy is trapped in a cycle of perpetual war, fought by noble houses with enormous, mechanized combat vehicles called BattleMechs. Take command of your own mercenary outfit of 'Mechs and the MechWarriors that pilot them, struggling to stay afloat as you find yourself drawn into a brutal interstellar civil war. Upgrade your starfaring base of operations, negotiate mercenary contracts with feudal lords, repair and maintain your stable of aging BattleMechs, and execute devastating combat tactics to defeat your enemies on the battlefield. COMMAND A SQUAD OF 'MECHS IN TURN-BASED COMBAT Deploy over 30 BattleMechs in a wide variety of combinations. Use terrain, positioning, weapon selection and special abilities to outmaneuver and outplay your opponents. MANAGE YOUR MERCENARY COMPANY Recruit, customize, and develop unique MechWarriors. Improve and customize your dropship. As a Mercenary, travel a wide stretch of space, taking missions and managing your reputation with a variety of noble houses and local factions. TAKE PART IN A DESPERATE CIVIL WAR Immerse yourself in the story of a violently deposed ruler, waging a brutal war to take back her throne with the support of your ragtag mercenary company. CUSTOMIZE YOUR 'MECHS Use your MechLab to maintain and upgrade your units, replacing damaged weapon systems with battlefield salvage taken from fallen foes. PVP MULTIPLAYER & SKIRMISH MODE Customize a Lance of 'Mechs and MechWarriors to go head-to-head with your friends, compete against opponents online, or jump into single-player skirmish mode to test your strategies against the AI. ",
                    "price": "$39.99",
                    "linkedGenres": [
                        {
                            "id": 1,
                            "genre": "Action"
                        },
                        {
                            "id": 2,
                            "genre": "Strategy"
                        },
                        {
                            "id": 3,
                            "genre": "Adventure"
                        }
                    ],
                    "linkedDeveloper": [
                        {
                            "id": 2,
                            "name": "Harebrained Schemes"
                        }
                    ],
                    "linkdePublishers": [
                        {
                            "id": 2,
                            "name": "Paradox Interactive"
                        }
                    ]
                }
            ]
            """;

    @Test
    public void checkGet_whenNoParam_thenAllTagsAreReturned() throws Exception {

        fakeController.perform(get("/game?category=RELEASE_DATE&value=2018-04-24")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

}
