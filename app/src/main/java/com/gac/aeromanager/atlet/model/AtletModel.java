package com.gac.aeromanager.atlet.model;

/**
 * Created by arifcebe on 8/23/14.
 */
public class AtletModel {
    private String no_dada;
    private String nama;
    private String jenKel;
    private int team_id;
    private String team_nama;
    private String team_status;

    public AtletModel(String no_dada, String nama, String jenKel, String team_nama,int team_id,String team_status) {
        this.no_dada = no_dada;
        this.nama = nama;
        this.jenKel = jenKel;
        this.team_id = team_id;
        this.team_nama = team_nama;
        this.team_status = team_status;
    }

    public AtletModel(String no_dada, String nama, String jenKel, int team_id) {
        super();
        this.no_dada = no_dada;
        this.nama = nama;
        this.jenKel = jenKel;
        this.team_id = team_id;
    }

    public AtletModel(String nama, String jenKel, int team_id) {
        super();
        this.nama = nama;
        this.jenKel = jenKel;
        this.team_id = team_id;
    }

    public AtletModel(String no_dada) {
        super();
        this.no_dada = no_dada;
    }

    public AtletModel() {
        super();
    }

    public String getNo_dada() {
        return no_dada;
    }
    public void setNo_dada(String no_dada) {
        this.no_dada = no_dada;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getJenKel() {
        return jenKel;
    }
    public void setJenKel(String jenKel) {
        this.jenKel = jenKel;
    }
    public int getTeam_id() {
        return team_id;
    }
    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_nama() {
        return team_nama;
    }

    public void setTeam_nama(String team_nama) {
        this.team_nama = team_nama;
    }

    public String getTeam_status() {
        return team_status;
    }

    public void setTeam_status(String team_status) {
        this.team_status = team_status;
    }
}
