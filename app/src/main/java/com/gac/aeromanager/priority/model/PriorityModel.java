package com.gac.aeromanager.priority.model;

/**
 * Created by arifcebe on 8/30/14.
 */
public class PriorityModel {
    private String nodada;
    private String nama;
    private float score;
    private String jenKel;
    private String teamNama;



    public PriorityModel() {
    }

    public PriorityModel(String nodada, String nama, float score, String jenKel) {
        this.nodada = nodada;
        this.nama = nama;
        this.score = score;
        this.jenKel = jenKel;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getTeamNama() {
        return teamNama;
    }

    public void setTeamNama(String teamNama) {
        this.teamNama = teamNama;
    }
}
