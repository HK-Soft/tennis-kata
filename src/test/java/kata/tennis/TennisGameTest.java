package kata.tennis;

import kata.tennis.scores.SimpleGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class TennisGameTest {

    @Test
    public void should_return_initial_game_status() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        //Then
        Assertions.assertIterableEquals(Collections.singleton(new SimpleGameScore(15, 0)), result.getStatus());
        Assertions.assertIterableEquals(Collections.singleton(new SimpleGameScore(0, 0)), result.getScore());
        Assertions.assertIterableEquals(Collections.singleton(new SimpleGameScore(0, 0)), result.getMatches());
    }
}
