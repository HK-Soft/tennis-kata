package kata.tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TiebreakGameScoreTest {

    @Test
    public void should_update_the_player_score_with_plus_one() {
        //Given
        TennisGame tennisGame = new TennisGame();
        TiebreakGameScore tiebreakGameScore = new TiebreakGameScore(0, 1);
        //When
        TiebreakGameScore result = tennisGame.nextTiebreakGameScore(PlayerID.SECOND_PLAYER, tiebreakGameScore);
        //Then
        Assertions.assertEquals(new TiebreakGameScore(0,2),result);
    }
}
