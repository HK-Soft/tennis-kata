package kata.tennis;

import kata.tennis.scores.*;

import java.util.logging.Logger;

public class TennisGame {

    private static final Logger LOGGER = Logger.getLogger(TennisGame.class.getName());

    public StandardGameScore nextStandardGameScore(PlayerID player, StandardGameScore currentScore) {
        StandardGameScore result = currentScore.copy();
        if (
                player == PlayerID.FIRST_PLAYER &&
                        currentScore.getFirstPlayerScore() == GameScoreType.ADVANTAGE
        ) {
            result.setFirstPlayerScore(GameScoreType.WIN);
            return result;
        }
        if (
                player == PlayerID.SECOND_PLAYER &&
                        currentScore.getSecondPlayerScore() == GameScoreType.ADVANTAGE
        ) {
            result.setSecondPlayerScore(GameScoreType.WIN);
            return result;
        }
        if (
                player == PlayerID.FIRST_PLAYER &&
                        currentScore.getFirstPlayerScore() == GameScoreType.FORTY &&
                        currentScore.getSecondPlayerScore() == GameScoreType.ADVANTAGE
        ) {
            result.setFirstPlayerScore(GameScoreType.DEUCE);
            result.setSecondPlayerScore(GameScoreType.DEUCE);
            return result;
        }
        if (
                player == PlayerID.SECOND_PLAYER &&
                        currentScore.getSecondPlayerScore() == GameScoreType.FORTY &&
                        currentScore.getFirstPlayerScore() == GameScoreType.ADVANTAGE
        ) {
            result.setFirstPlayerScore(GameScoreType.DEUCE);
            result.setSecondPlayerScore(GameScoreType.DEUCE);
            return result;
        }
        if (
                (currentScore.getFirstPlayerScore() == GameScoreType.FORTY) &&
                        (currentScore.getSecondPlayerScore() == GameScoreType.FORTY)
        ) {
            if (player == PlayerID.FIRST_PLAYER) {
                result.setFirstPlayerScore(GameScoreType.ADVANTAGE);
            } else {
                result.setSecondPlayerScore(GameScoreType.ADVANTAGE);
            }
            return result;
        }
        if (
                (player == PlayerID.FIRST_PLAYER) &&
                        (currentScore.getFirstPlayerScore() == GameScoreType.FORTY)
        ) {
            result.setFirstPlayerScore(GameScoreType.WIN);
            return result;
        }
        if (
                (player == PlayerID.SECOND_PLAYER) &&
                        (currentScore.getSecondPlayerScore() == GameScoreType.FORTY)
        ) {
            result.setSecondPlayerScore(GameScoreType.WIN);
            return result;
        }
        if (
                (player == PlayerID.FIRST_PLAYER) &&
                        (currentScore.getFirstPlayerScore().ordinal() >= GameScoreType.LOVE.ordinal()) &&
                        (currentScore.getFirstPlayerScore().ordinal() < GameScoreType.FORTY.ordinal())
        ) {
            result.setFirstPlayerScore(GameScoreType.values()[currentScore.getFirstPlayerScore().ordinal() + 1]);
            return result;
        }
        if (
                (player == PlayerID.SECOND_PLAYER) &&
                        (currentScore.getFirstPlayerScore().ordinal() >= GameScoreType.LOVE.ordinal()) &&
                        (currentScore.getFirstPlayerScore().ordinal() < GameScoreType.FORTY.ordinal())
        ) {
            result.setSecondPlayerScore(GameScoreType.values()[currentScore.getSecondPlayerScore().ordinal() + 1]);
            return result;
        }
        return result;
    }

    public TiebreakGameScore nextTiebreakGameScore(PlayerID playerID, TiebreakGameScore currentScore) {
        TiebreakGameScore result = currentScore.copy();
        if (playerID == PlayerID.FIRST_PLAYER)
            result.setFirstPlayerScore(currentScore.getFirstPlayerScore() + 1);
        else
            result.setSecondPlayerScore(currentScore.getSecondPlayerScore() + 1);
        return result;
    }

    public SetScore nextSetScore(GameScore<?> currentGame, SetScore currentSetScore) {
        SetScore result = currentSetScore.copy();
        currentGame.whoWonTheGame().ifPresent(playerId -> {
            if (playerId == PlayerID.FIRST_PLAYER)
                result.setFirstPlayerScore(result.getFirstPlayerScore() + 1);
            else result.setSecondPlayerScore(result.getSecondPlayerScore() + 1);
        });
        return result;
    }

    public static void main(String[] args) {
        LOGGER.info("Game started");
    }

}
