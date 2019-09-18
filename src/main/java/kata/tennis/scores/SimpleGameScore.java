package kata.tennis.scores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGameScore {
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleGameScore that = (SimpleGameScore) o;
        return firstPlayerScore == that.firstPlayerScore &&
                secondPlayerScore == that.secondPlayerScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstPlayerScore, secondPlayerScore);
    }
}
