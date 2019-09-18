package kata.tennis;

import kata.tennis.scores.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.logging.Logger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TennisGame {

    private static final Logger LOGGER = Logger.getLogger(TennisGame.class.getName());

    private GameStatus gameStatus = new GameStatus();

    public void score(PlayerID playerID) {
        boolean tiebreak = false;
        SetScore currentScore = gameStatus.getCurrentGameScore().orElse(new SetScore(0, 0));
        StandardGameScore currentGameScore = gameStatus.getCurrentGameStatus()
                .orElse(new StandardGameScore(GameScoreType.LOVE, GameScoreType.LOVE));

        GameScore gameScore;
        if (currentScore.getSecondPlayerScore() == 6 &&
                currentScore.getFirstPlayerScore() == 6) {
            TiebreakGameScore currentTiebreakBreak = gameStatus.getCurrentTiebreakScore()
                    .orElse(new TiebreakGameScore(0, 0));
            gameScore = nextTiebreakGameScore(playerID, currentTiebreakBreak);
            tiebreak = true;
        } else {
            gameScore = nextStandardGameScore(playerID, currentGameScore);
            this.gameStatus.getStatus().add((StandardGameScore) gameScore);
        }
        SetScore setScore;
        if (!tiebreak)
            setScore = nextSetScore(gameScore, currentScore);
        else {
            setScore = (playerID == PlayerID.FIRST_PLAYER) ?
                    new SetScore(currentScore.getFirstPlayerScore() + 1, currentScore.getSecondPlayerScore()) :
                    new SetScore(currentScore.getFirstPlayerScore(), currentScore.getSecondPlayerScore() + 1);
        }
        MatchScore currentMatchScore = gameStatus.getCurrentGameMatch().orElse(new MatchScore(0, 0));
        MatchScore matchScore = nextMatchScore(setScore, currentMatchScore);


        if (gameScore.whoWon().isPresent())
            this.gameStatus.getStatus().add(new StandardGameScore(GameScoreType.LOVE, GameScoreType.LOVE));

        if (!tiebreak)
            this.gameStatus.getScore().add(setScore);
        if (setScore.whoWon().isPresent())
            this.gameStatus.getScore().add(new SetScore(0, 0));

        this.gameStatus.getMatches().add(matchScore);
    }

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

    public SetScore nextSetScore(GameScore currentGame, SetScore currentSetScore) {
        if (currentSetScore.whoWon().isPresent())
            return currentSetScore;
        SetScore result = currentSetScore.copy();
        currentGame.whoWon().ifPresent(playerId -> {
            if (playerId == PlayerID.FIRST_PLAYER)
                result.setFirstPlayerScore(result.getFirstPlayerScore() + 1);
            else result.setSecondPlayerScore(result.getSecondPlayerScore() + 1);
        });
        return result;
    }

    public MatchScore nextMatchScore(SetScore currentSetScore, MatchScore currentMatchScore) {
        if (currentMatchScore.whoWon().isPresent())
            return currentMatchScore;
        MatchScore result = currentMatchScore.copy();
        currentSetScore.whoWon().ifPresent(playerID -> {
            if (playerID == PlayerID.FIRST_PLAYER) {
                result.setFirstPlayerScore(result.getFirstPlayerScore() + 1);
            } else {
                result.setSecondPlayerScore(result.getSecondPlayerScore() + 1);
            }
        });
        return result;
    }

    public static void main(String[] args) {
        LOGGER.info("Game started");
    }

}
