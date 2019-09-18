package kata.tennis;

import kata.tennis.scores.*;
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
    private List<SetScore> score = new ArrayList<>();
    private List<StandardGameScore> status = new ArrayList<>();
    private List<MatchScore> matches = new ArrayList<>();
    private List<TiebreakGameScore> tiebreaks = new ArrayList<>();

    public Optional<StandardGameScore> getCurrentGameStatus() {
        if (!this.status.isEmpty())
            return Optional.of(this.status.get(this.status.size() - 1));
        return Optional.empty();
    }

    public Optional<SetScore> getCurrentGameScore() {
        if(!this.score.isEmpty())
            return Optional.of(this.score.get(this.score.size()-1));
        return Optional.empty();
    }

    public Optional<MatchScore> getCurrentGameMatch() {
        if(!this.matches.isEmpty())
            return Optional.of(this.matches.get(this.matches.size()-1));
        return Optional.empty();
    }
}
