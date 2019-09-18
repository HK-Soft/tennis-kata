package kata.tennis.scores;

import kata.tennis.PlayerID;

import java.util.Objects;
import java.util.Optional;

public class SetScore extends SimpleGameScore implements GameScore<SimpleGameScore> {

    public SetScore() {
        super();
    }

    public SetScore(int firstPlayerScore, int secondPlayerScore) {
        super(firstPlayerScore, secondPlayerScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetScore that = (SetScore) o;
        return getFirstPlayerScore() == that.getFirstPlayerScore() &&
                getSecondPlayerScore() == that.getSecondPlayerScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayerScore(), getSecondPlayerScore());
    }

    public SetScore copy() {
        return new SetScore(this.getFirstPlayerScore(), this.getSecondPlayerScore());
    }

    @Override
    public String toString() {
        return "(" + getFirstPlayerScore() + "-" + getSecondPlayerScore() + ")";
    }

    @Override
    public Optional<PlayerID> whoWon() {
        int diff = getFirstPlayerScore() - getSecondPlayerScore();
        if (getFirstPlayerScore() >= 6 && getSecondPlayerScore() >= 6)
            return (diff > 0) ? Optional.of(PlayerID.FIRST_PLAYER) : Optional.of(PlayerID.SECOND_PLAYER);
        if (getFirstPlayerScore() >= 6 && diff >= 2)
            return Optional.of(PlayerID.FIRST_PLAYER);
        if (getSecondPlayerScore() >= 6 && diff <= -2)
            return Optional.of(PlayerID.SECOND_PLAYER);
        return Optional.empty();
    }

}
