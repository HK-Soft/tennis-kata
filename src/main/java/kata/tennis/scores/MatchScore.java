package kata.tennis.scores;

import kata.tennis.PlayerID;

import java.util.Objects;
import java.util.Optional;

public class MatchScore extends SimpleGameScore implements GameScore<SimpleGameScore> {

    public MatchScore() {
        super();
    }

    public MatchScore(int firstPlayerScore, int secondPlayerScore) {
        super(firstPlayerScore, secondPlayerScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchScore that = (MatchScore) o;
        return getFirstPlayerScore() == that.getFirstPlayerScore() &&
                getSecondPlayerScore() == that.getSecondPlayerScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayerScore(), getSecondPlayerScore());
    }

    public MatchScore copy() {
        return new MatchScore(this.getFirstPlayerScore(), this.getSecondPlayerScore());
    }

    public String toString() {
        return "(" + getFirstPlayerScore() + "-" + getSecondPlayerScore() + ")";
    }


    @Override
    public Optional<PlayerID> whoWonTheGame() {
        if(getFirstPlayerScore() >= 3)
            return Optional.of(PlayerID.FIRST_PLAYER);
        if(getSecondPlayerScore() >= 3)
            return Optional.of(PlayerID.SECOND_PLAYER);
        return Optional.empty();
    }

    @Override
    public SimpleGameScore getScore() {
        return null;
    }
}
