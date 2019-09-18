package kata.tennis.scores;

import kata.tennis.InvalidScoreValue;

public enum GameScoreType {
    LOVE(0), FIFTEEN(15), THIRTY(30), FORTY(40), DEUCE(40), ADVANTAGE(40), WIN(40);

    private int score;

    GameScoreType(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    public static GameScoreType fromScore(int score) {
        switch (score) {
            case 0:
                return LOVE;
            case 15:
                return FIFTEEN;
            case 30:
                return THIRTY;
            case 40:
                return FORTY;
        }
        throw new InvalidScoreValue();
    }
}
