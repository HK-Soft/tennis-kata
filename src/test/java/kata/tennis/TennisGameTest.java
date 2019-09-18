package kata.tennis;

import kata.tennis.scores.GameScoreType;
import kata.tennis.scores.SetScore;
import kata.tennis.scores.SimpleGameScore;
import kata.tennis.scores.StandardGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TennisGameTest {

    @Test
    public void should_return_initial_game_status() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        //Then
        Assertions.assertIterableEquals(Collections.singleton(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.LOVE))
                , result.getStatus());
        Assertions.assertIterableEquals(Collections.singleton(new SetScore(0, 0)), result.getScore());
        Assertions.assertIterableEquals(Collections.singleton(new SimpleGameScore(0, 0)), result.getMatches());
    }

    @Test
    public void should_return_game_status_after_tow_scores() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        tennisGame.score(PlayerID.SECOND_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        //Then
        List<StandardGameScore> expectedStatus = new ArrayList<>();
        expectedStatus.add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.LOVE));
        expectedStatus.add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.FIFTEEN));
        Assertions.assertIterableEquals(expectedStatus, result.getStatus());

        List<SimpleGameScore> expectedScore = new ArrayList<>();
        expectedScore.add(new SetScore(0, 0));
        expectedScore.add(new SetScore(0, 0));
        Assertions.assertIterableEquals(expectedScore, result.getScore());

        List<SimpleGameScore> expectedMatch = new ArrayList<>();
        expectedMatch.add(new SimpleGameScore(0, 0));
        expectedMatch.add(new SimpleGameScore(0, 0));
        Assertions.assertIterableEquals(expectedMatch, result.getMatches());
    }

    @Test
    public void should_update_set_score_when_set_is_won() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        result.getStatus().add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.FORTY));
        tennisGame.score(PlayerID.SECOND_PLAYER);
        //Then
        List<StandardGameScore> expectedStatus = new ArrayList<>();
        expectedStatus.add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.LOVE));
        expectedStatus.add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.FORTY));
        expectedStatus.add(new StandardGameScore(GameScoreType.LOVE, GameScoreType.LOVE));

        Assertions.assertIterableEquals(expectedStatus, result.getStatus());

        List<SimpleGameScore> expectedScore = new ArrayList<>();
        expectedScore.add(new SetScore(0, 0));
        expectedScore.add(new SetScore(0, 1));
        Assertions.assertIterableEquals(expectedScore, result.getScore());

        List<SimpleGameScore> expectedMatch = new ArrayList<>();
        expectedMatch.add(new SimpleGameScore(0, 0));
        expectedMatch.add(new SimpleGameScore(0, 0));
        Assertions.assertIterableEquals(expectedMatch, result.getMatches());
    }

    @Test
    public void should_update_set_score_when_set_is_won_by_advantage() {
        //Given
        TennisGame tennisGame = new TennisGame();
        //When
        tennisGame.score(PlayerID.FIRST_PLAYER);
        GameStatus result = tennisGame.getGameStatus();
        result.getStatus().add(new StandardGameScore(GameScoreType.FORTY, GameScoreType.FORTY));
        tennisGame.score(PlayerID.SECOND_PLAYER);
        tennisGame.score(PlayerID.SECOND_PLAYER);
        //Then
        List<StandardGameScore> expectedStatus = new ArrayList<>();
        expectedStatus.add(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.LOVE));
        expectedStatus.add(new StandardGameScore(GameScoreType.FORTY, GameScoreType.FORTY));
        expectedStatus.add(new StandardGameScore(GameScoreType.FORTY, GameScoreType.ADVANTAGE));
        expectedStatus.add(new StandardGameScore(GameScoreType.FORTY, GameScoreType.WIN));

        Assertions.assertIterableEquals(expectedStatus, result.getStatus());

        List<SimpleGameScore> expectedScore = new ArrayList<>();
        expectedScore.add(new SetScore(0, 0));
        expectedScore.add(new SetScore(0, 0));
        expectedScore.add(new SetScore(0, 1));
        Assertions.assertIterableEquals(expectedScore, result.getScore());

        List<SimpleGameScore> expectedMatch = new ArrayList<>();
        expectedMatch.add(new SimpleGameScore(0, 0));
        expectedMatch.add(new SimpleGameScore(0, 0));
        expectedMatch.add(new SimpleGameScore(0, 0));
        Assertions.assertIterableEquals(expectedMatch, result.getMatches());
    }
}
