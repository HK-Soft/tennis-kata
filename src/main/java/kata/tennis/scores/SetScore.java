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
        TiebreakGameScore that = (TiebreakGameScore) o;
        return getFirstPlayerScore() == that.getFirstPlayerScore() &&
                getSecondPlayerScore() == that.getSecondPlayerScore();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstPlayerScore(), getSecondPlayerScore());
    }

    public TiebreakGameScore copy() {
        return new TiebreakGameScore(this.getFirstPlayerScore(), this.getSecondPlayerScore());
    }

    public String toString() {
        return "(" + getFirstPlayerScore() + "-" + getSecondPlayerScore() + ")";
    }

    @Override
    public Optional<PlayerID> whoWonTheGame() {
        return Optional.empty();
    }

    @Override
    public SimpleGameScore getScore() {
        return null;
    }
}
