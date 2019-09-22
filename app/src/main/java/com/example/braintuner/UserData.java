package com.example.braintuner;

public class UserData {
    private String userName;
    private int Score;

    public UserData() {
    }

    public UserData(String userName, int score) {
        this.userName = userName;
        Score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
