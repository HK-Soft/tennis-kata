package kata.tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StandardGameScoreTest {

    @Test
    public void should_return_the_next_value_in_0_15_30_40_sequence_when_both_players_less_then_40() {
        //Given
        TennisGame tennisGame = new TennisGame();
        StandardGameScore loveLoveScore = new StandardGameScore();
        StandardGameScore thirtyFifteenScore = new StandardGameScore(GameScoreType.THIRTY, GameScoreType.FIFTEEN);
        //When
        StandardGameScore shouldBeFifteenLove = tennisGame.nextStandardGameScore(PlayerID.FIRST_PLAYER, loveLoveScore);
        StandardGameScore shouldBeThirtyThirty = tennisGame.nextStandardGameScore(PlayerID.SECOND_PLAYER, thirtyFifteenScore);
        //Then
        Assertions.assertEquals(new StandardGameScore(GameScoreType.FIFTEEN, GameScoreType.LOVE), shouldBeFifteenLove);
        Assertions.assertEquals(new StandardGameScore(GameScoreType.THIRTY, GameScoreType.THIRTY), shouldBeThirtyThirty);
    }
}
