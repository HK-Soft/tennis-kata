package kata.tennis;

public interface GameScore<T> {

    PlayerID whoWonTheGame();

    T getScore();
}
