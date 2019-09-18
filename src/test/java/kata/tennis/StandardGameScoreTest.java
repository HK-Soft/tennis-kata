package kata.tennis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    @Test
    public void should_return_win_when_one_player_score_is_40_then_scores() {
        //Given
        TennisGame tennisGame = new TennisGame();
        StandardGameScore thirtyFortyScore = new StandardGameScore(GameScoreType.THIRTY, GameScoreType.FORTY);
        StandardGameScore fortyLoveScore = new StandardGameScore(GameScoreType.FORTY, GameScoreType.LOVE);
        //When
        StandardGameScore shouldBeWinLove = tennisGame.nextStandardGameScore(PlayerID.FIRST_PLAYER, fortyLoveScore);
        StandardGameScore shouldBeThirtyWin = tennisGame.nextStandardGameScore(PlayerID.SECOND_PLAYER, thirtyFortyScore);
        //Then
        Assertions.assertEquals(new StandardGameScore(GameScoreType.WIN, GameScoreType.LOVE), shouldBeWinLove);
        Assertions.assertEquals(new StandardGameScore(GameScoreType.THIRTY, GameScoreType.WIN), shouldBeThirtyWin);
    }

    @Test
    public void should_return_advantage_when_both_players_score_is_40() {
        //Given
        TennisGame tennisGame = new TennisGame();
        StandardGameScore fortyFortyScore = new StandardGameScore(GameScoreType.FORTY, GameScoreType.FORTY);
        //When
        StandardGameScore shouldBeAdvantageForty = tennisGame.nextStandardGameScore(PlayerID.FIRST_PLAYER, fortyFortyScore);
        StandardGameScore shouldBeFortyAdvantage = tennisGame.nextStandardGameScore(PlayerID.SECOND_PLAYER, fortyFortyScore);
        //Then
        Assertions.assertEquals(new StandardGameScore(GameScoreType.ADVANTAGE, GameScoreType.FORTY), shouldBeAdvantageForty);
        Assertions.assertEquals(new StandardGameScore(GameScoreType.FORTY, GameScoreType.ADVANTAGE), shouldBeFortyAdvantage);
    }

    @Test
    public void should_return_deuce_when_both_player_lose_advantage() {
        //Given
        TennisGame tennisGame = new TennisGame();
        StandardGameScore fortyAdvantageScore = new StandardGameScore(GameScoreType.FORTY, GameScoreType.ADVANTAGE);
        //When
        StandardGameScore shouldBeDeuceDeuce = tennisGame.nextStandardGameScore(PlayerID.FIRST_PLAYER, fortyAdvantageScore);
        //Then
        Assertions.assertEquals(new StandardGameScore(GameScoreType.DEUCE, GameScoreType.DEUCE), shouldBeDeuceDeuce);
    }

    @Test
    public void should_return_win_when_player_scores_will_advantage() {
        //Given
        TennisGame tennisGame = new TennisGame();
        StandardGameScore fortyAdvantageScore = new StandardGameScore(GameScoreType.FORTY, GameScoreType.ADVANTAGE);
        //When
        StandardGameScore shouldBeFortyWin = tennisGame.nextStandardGameScore(PlayerID.SECOND_PLAYER, fortyAdvantageScore);
        //Then
        Assertions.assertEquals(new StandardGameScore(GameScoreType.FORTY, GameScoreType.WIN), shouldBeFortyWin);
    }

    @Test
    public void should_return_the_wining_player_when_score_is_win() {
        //Given
        StandardGameScore gameScore = new StandardGameScore(GameScoreType.WIN, GameScoreType.LOVE);
        //When
        Optional<PlayerID> mayContainPlayerId = gameScore.whoWonTheGame();
        //Then
        Assertions.assertTrue(mayContainPlayerId.isPresent());
        Assertions.assertEquals(Optional.of(PlayerID.FIRST_PLAYER), mayContainPlayerId);
    }
}
