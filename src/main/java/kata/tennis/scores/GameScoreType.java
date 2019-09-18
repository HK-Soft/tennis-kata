package kata.tennis.scores;

public enum GameScoreType {
    LOVE(0), FIFTEEN(15), THIRTY(30), FORTY(40), DEUCE(40), ADVANTAGE(40), WIN(40);

    private int score;

    GameScoreType(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

}
