package kata.tennis;

import kata.tennis.scores.MatchScore;
import kata.tennis.scores.SetScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    @Test
    public void should_return_the_same_score_when_set_is_not_won() {
        //Given
        SetScore setScore = new SetScore(4, 5);
        MatchScore matchScore = new MatchScore(0, 0);
        TennisGame tennisGame = new TennisGame();
        //When
        MatchScore result = tennisGame.nextMatchScore(setScore, matchScore);
        //Then
        Assertions.assertEquals(matchScore, result);
    }

    @Test
    public void should_return_the_same_score_when_match_is_won(){
        //Given
        SetScore setScore = new SetScore(4, 6);
        MatchScore matchScore = new MatchScore(2, 3);
        TennisGame tennisGame = new TennisGame();
        //When
        MatchScore result = tennisGame.nextMatchScore(setScore, matchScore);
        //Then
        Assertions.assertEquals(matchScore, result);
    }

    @Test
    public void should_return_the_wining_player_when_score_is_more_equal_3() {
        //Given
        MatchScore matchScore = new MatchScore(2, 3);
        //When
        Optional<PlayerID> mayContianPlayerId = matchScore.whoWonTheGame();
        //Then
        Assertions.assertTrue(mayContianPlayerId.isPresent());
        Assertions.assertEquals(Optional.of(PlayerID.SECOND_PLAYER), mayContianPlayerId);
    }

    @Test
    public void should_return_empty_when_no_player_won() {
        //Given
        MatchScore matchScore = new MatchScore(2, 2);
        //When
        Optional<PlayerID> mayContianPlayerId = matchScore.whoWonTheGame();
        //Then
        Assertions.assertFalse(mayContianPlayerId.isPresent());
    }

}
