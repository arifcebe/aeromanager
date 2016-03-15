package com.gac.aeromanager.round.model;

/**
 * Created by arifcebe on 8/23/14.
 */
public class RoundModel {
    private int roundId;
    private String roundKe;
    private String noDada;
    private String atletNama;
    private String teamNama;
    private String teamStatus;

    private float score_1;
    private float score_2;
    private float score;
    private String atletJk;
    private String rank;


    public RoundModel() {
    }

    public RoundModel(int roundId, String roundKe, String noDada,
                      String atletNama, String teamNama, float score, String atletJk) {
        this.roundId = roundId;
        this.roundKe = roundKe;
        this.noDada = noDada;
        this.atletNama = atletNama;
        this.teamNama = teamNama;
        this.score = score;
        this.atletJk = atletJk;
    }

    /* call this instantiate when you get athlete */
    public RoundModel(int roundId,String nodada,String atletNama,
                      String teamNama,float score_1,float score_2){
        this.roundId = roundId;
        this.noDada = nodada;
        this.atletNama = atletNama;
        this.teamNama = teamNama;
        this.score_1 = score_1;
        this.score_2 = score_2;
    }

    public RoundModel(int roundId, String roundKe, String noDada, String atletNama, String teamNama,
                      float score_1, float score_2, float score, String atletJk) {
        this.roundId = roundId;
        this.roundKe = roundKe;
        this.noDada = noDada;
        this.atletNama = atletNama;
        this.teamNama = teamNama;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score = score;
        this.atletJk = atletJk;
    }

    public RoundModel(int roundId, String roundKe, String noDada, float score) {
        this.roundId = roundId;
        this.roundKe = roundKe;
        this.noDada = noDada;
        this.score = score;
    }

    /* instantiate when update score */
    public RoundModel(int roundId,float score_1,float score_2, float score) {
        this.roundId = roundId;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score = score;
    }

    public RoundModel(String roundKe, String noDada, float score) {
        this.roundKe = roundKe;
        this.noDada = noDada;
        this.score = score;
    }

    /* instantiate when add athlete to round */
    public RoundModel(String roundKe, String noDada, float score_1, float score_2, float score, String rank) {
        this.roundKe = roundKe;
        this.noDada = noDada;
        this.score_1 = score_1;
        this.score_2 = score_2;
        this.score = score;
        this.rank = rank;
    }

    public RoundModel(String roundKe, String noDada) {
        this.roundKe = roundKe;
        this.noDada = noDada;
    }

    /* instantiate when update rank */
    public RoundModel(int roundId,String rank){
        this.roundId = roundId;
        this.rank = rank;
    }

    public RoundModel(int roundId) {
        this.roundId = roundId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public String getRoundKe() {
        return roundKe;
    }

    public void setRoundKe(String roundKe) {
        this.roundKe = roundKe;
    }

    public String getNoDada() {
        return noDada;
    }

    public void setNoDada(String noDada) {
        this.noDada = noDada;
    }

    public String getAtletNama() {
        return atletNama;
    }

    public void setAtletNama(String atletNama) {
        this.atletNama = atletNama;
    }

    public String getTeamNama() {
        return teamNama;
    }

    public void setTeamNama(String teamNama) {
        this.teamNama = teamNama;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getAtletJk() {
        return atletJk;
    }

    public void setAtletJk(String atletJk) {
        this.atletJk = atletJk;
    }

    public float getScore_1() {
        return score_1;
    }

    public void setScore_1(float score_1) {
        this.score_1 = score_1;
    }

    public float getScore_2() {
        return score_2;
    }

    public void setScore_2(float score_2) {
        this.score_2 = score_2;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }
}