package kata.tennis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StandardGameScore implements GameScore<StandardGameScore> {

    private GameScoreType firstPlayerScore = GameScoreType.LOVE;
    private GameScoreType secondPlayerScore = GameScoreType.LOVE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandardGameScore that = (StandardGameScore) o;
        return firstPlayerScore == that.firstPlayerScore &&
                secondPlayerScore == that.secondPlayerScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayerScore, secondPlayerScore);
    }

    public StandardGameScore copy() {
        return new StandardGameScore(this.firstPlayerScore, this.secondPlayerScore);
    }

    public String toString() {
        return "(" + firstPlayerScore + "-" + secondPlayerScore + ")";
    }

    @Override
    public Optional<PlayerID> whoWonTheGame() {
        return Optional.empty();
    }

    @Override
    public StandardGameScore getScore() {
        return null;
    }
}
