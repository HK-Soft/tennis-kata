package kata.tennis.scores;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleGameScore {
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;
}
