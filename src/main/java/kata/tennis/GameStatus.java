package kata.tennis;

import kata.tennis.scores.SimpleGameScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameStatus {

    private String firstPlayerName = "nom du joueur1";
    private String secondPlayerName = "nom du joueur2";
    private List<SimpleGameScore> score = new ArrayList<>();
    private List<SimpleGameScore> status = new ArrayList<>();
    private List<SimpleGameScore> matches = new ArrayList<>();

    public Optional<SimpleGameScore> getCurrentGameStatus() {
        if (!this.status.isEmpty())
            return Optional.of(this.status.get(this.status.size() - 1));
        return Optional.empty();
    }
}
