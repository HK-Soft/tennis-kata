package kata.tennis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TiebreakGameScore {
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TiebreakGameScore that = (TiebreakGameScore) o;
        return firstPlayerScore == that.firstPlayerScore &&
                secondPlayerScore == that.secondPlayerScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayerScore, secondPlayerScore);
    }

    public TiebreakGameScore copy() {
        return new TiebreakGameScore(this.firstPlayerScore, this.secondPlayerScore);
    }

    public String toString() {
        return "(" + firstPlayerScore + "-" + secondPlayerScore + ")";
    }

}
