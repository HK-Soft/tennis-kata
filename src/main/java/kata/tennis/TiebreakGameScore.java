package kata.tennis;

import java.util.Objects;
import java.util.Optional;

public class TiebreakGameScore extends SimpleGameScore implements GameScore<SimpleGameScore> {

    public TiebreakGameScore() {
        super();
    }

    public TiebreakGameScore(int firstPlayerScore, int secondPlayerScore) {
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
        return null;
    }

    @Override
    public SimpleGameScore getScore() {
        return this;
    }
}
