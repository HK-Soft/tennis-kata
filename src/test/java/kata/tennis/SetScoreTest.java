package kata.tennis;

import kata.tennis.scores.GameScoreType;
import kata.tennis.scores.SetScore;
import kata.tennis.scores.StandardGameScore;
import kata.tennis.scores.TiebreakGameScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SetScoreTest {

    @Test
    public void should_return_update_set_score_when_player_is_win_stander_game() {
        //Given
        TennisGame tennisGame = new TennisGame();
        SetScore currentSetScore = new SetScore(3, 4);
        StandardGameScore winStandardGameScore = new StandardGameScore(GameScoreType.LOVE, GameScoreType.WIN);
        //When
        SetScore result = tennisGame.nextSetScore(winStandardGameScore, currentSetScore);
        //Then
        Assertions.assertEquals(new SetScore(3, 5), result);
    }

    @Test
    public void should_return_update_set_score_when_player_is_win_tiebreak_game() {
        //Given
        TennisGame tennisGame = new TennisGame();
        SetScore currentSetScore = new SetScore(3, 4);
        TiebreakGameScore witTiebreakGameScore = new TiebreakGameScore(7, 5);
        //When
        SetScore result = tennisGame.nextSetScore(witTiebreakGameScore, currentSetScore);
        //Then
        Assertions.assertEquals(new SetScore(4, 4), result);
    }
}
