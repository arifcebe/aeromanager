package com.gac.aeromanager.hasilakhir.model;

/**
 * Created by arifcebe on 8/31/14.
 */
public class HasilAkhirDetailModel {
    private String roundKe;
    private String nodada;
    private String nama;
    private String team;
    private float score;
    private String rank;

    public HasilAkhirDetailModel() {
    }

    public HasilAkhirDetailModel(String roundKe, String nodada, String nama, String team, float score) {
        this.roundKe = roundKe;
        this.nodada = nodada;
        this.nama = nama;
        this.team = team;
        this.score = score;
    }

    public HasilAkhirDetailModel(String roundKe, String nodada, String nama, String team, float score, String rank) {
        this.roundKe = roundKe;
        this.nodada = nodada;
        this.nama = nama;
        this.team = team;
        this.score = score;
        this.rank = rank;
    }

    public String getRoundKe() {
        return roundKe;
    }

    public void setRoundKe(String roundKe) {
        this.roundKe = roundKe;
    }

    public String getNodada() {
        return nodada;
    }

    public void setNodada(String nodada) {
        this.nodada = nodada;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
