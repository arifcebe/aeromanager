package com.gac.aeromanager.hasilakhir.model;

/**
 * Created by arifcebe on 8/31/14.
 * hasil akhir model entity
 */
public class HasilAkhirModel {
    private String nodada;
    private String nama;
    private String team_nama;
    private float score_total;
    private String team_status;
    private String jenKel;

    public HasilAkhirModel() {
    }

    public HasilAkhirModel(String nodada, String nama, String team_nama,
                           float score_total, String team_status,
                           String jenKel) {
        this.nodada = nodada;
        this.nama = nama;
        this.team_nama = team_nama;
        this.score_total = score_total;
        this.team_status = team_status;
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

    public String getTeam_nama() {
        return team_nama;
    }

    public void setTeam_nama(String team_nama) {
        this.team_nama = team_nama;
    }

    public float getScore_total() {
        return score_total;
    }

    public void setScore_total(float score_total) {
        this.score_total = score_total;
    }

    public String getTeam_status() {
        return team_status;
    }

    public void setTeam_status(String team_status) {
        this.team_status = team_status;
    }

    public String getJenKel() {
        return jenKel;
    }

    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }
}
