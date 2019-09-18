package kata.tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class TiebreakGameScoreTest {

    @Test
    public void should_update_the_player_score_with_plus_one() {
        //Given
        TennisGame tennisGame = new TennisGame();
        TiebreakGameScore tiebreakGameScore = new TiebreakGameScore(0, 1);
        //When
        TiebreakGameScore result = tennisGame.nextTiebreakGameScore(PlayerID.SECOND_PLAYER, tiebreakGameScore);
        //Then
        Assertions.assertEquals(new TiebreakGameScore(0, 2), result);
    }

    @Test
    public void should_return_the_wining_player_when_score_is_win() {
        //Given
        TiebreakGameScore gameScore = new TiebreakGameScore(7, 0);
        //When
        Optional<PlayerID> mayContainPlayerId = gameScore.whoWonTheGame();
        //Then
        Assertions.assertTrue(mayContainPlayerId.isPresent());
        Assertions.assertEquals(Optional.of(PlayerID.FIRST_PLAYER), mayContainPlayerId);
    }

    @Test
    public void should_return_empty_when_players_score_is_less_then_7() {
        //Given
        TiebreakGameScore gameScore = new TiebreakGameScore(0, 0);
        //When
        Optional<PlayerID> mayContainPlayerId = gameScore.whoWonTheGame();
        //Then
        Assertions.assertFalse(mayContainPlayerId.isPresent());
    }

    @Test
    public void should_return_empty_when_players_diff_is_les_then_2() {
        //Given
        TiebreakGameScore gameScore = new TiebreakGameScore(8, 7);
        //When
        Optional<PlayerID> mayContainPlayerId = gameScore.whoWonTheGame();
        //Then
        Assertions.assertFalse(mayContainPlayerId.isPresent());
    }
}
