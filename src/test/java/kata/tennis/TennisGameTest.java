package kata.tennis;

import kata.tennis.scores.SimpleGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void should_return_gamte_status_after_tow_scores() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        tennisGame.score(PlayerID.SECOND_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        //Then
        List<SimpleGameScore> expectedStatus = new ArrayList<>();
        expectedStatus.add(new SimpleGameScore(15, 0));
        expectedStatus.add(new SimpleGameScore(15,15));
        Assertions.assertIterableEquals(expectedStatus, result.getStatus());

        List<SimpleGameScore> expectedScore = new ArrayList<>();
        expectedScore.add(new SimpleGameScore(0, 0));
        expectedScore.add(new SimpleGameScore(0, 0));
        Assertions.assertIterableEquals(expectedScore, result.getScore());

        List<SimpleGameScore> expectedMatch = new ArrayList<>();
        expectedMatch.add(new SimpleGameScore(0, 0));
        expectedMatch.add(new SimpleGameScore(0, 0));
        Assertions.assertIterableEquals(expectedMatch, result.getMatches());
    }
}
