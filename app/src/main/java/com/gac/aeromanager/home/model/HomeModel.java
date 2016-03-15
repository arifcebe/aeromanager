package com.gac.aeromanager.home.model;

/**
 * Created by arifcebe on 8/20/14.
 */
public class HomeModel {
    private String[] round;

    public HomeModel(String[] round) {
        this.round = round;
    }

    public HomeModel() {

    }

    public String[] getRound() {
        round = new String[]{"Round 1","Round 2","Round 3","Round 4","Round 5","Round 6","Round 7"};
        return round;
    }
}
