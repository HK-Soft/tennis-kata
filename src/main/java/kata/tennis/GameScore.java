package kata.tennis;

import java.util.Optional;

public interface GameScore<T> {

    Optional<PlayerID> whoWonTheGame();

    T getScore();
}
