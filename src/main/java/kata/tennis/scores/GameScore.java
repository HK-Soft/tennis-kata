package kata.tennis.scores;

import kata.tennis.PlayerID;

import java.util.Optional;

public interface GameScore {

    Optional<PlayerID> whoWon();

}
