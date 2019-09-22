package com.example.braintuner;

public class FinalScore {
    private String userName;
    private int finalScore;

    public FinalScore() {
    }

    public FinalScore(String userName, int finalScore) {
        this.userName = userName;
        this.finalScore = finalScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
