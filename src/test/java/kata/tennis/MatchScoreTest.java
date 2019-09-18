package kata.tennis;

import kata.tennis.scores.MatchScore;
import kata.tennis.scores.SetScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MatchScoreTest {

    @Test
    public void should_return_the_next_score_when_set_is_won() {
        //Given
        SetScore setScore = new SetScore(4, 6);
        MatchScore matchScore = new MatchScore(0, 0);
        TennisGame tennisGame = new TennisGame();
        //When
        MatchScore result = tennisGame.nextMatchScore(setScore, matchScore);
        //Then
        Assertions.assertEquals(new MatchScore(0, 1), result);
    }
}
