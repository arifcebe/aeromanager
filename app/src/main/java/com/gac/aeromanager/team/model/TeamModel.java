package com.gac.aeromanager.team.model;

/**
 * Created by arifcebe on 8/21/14.
 * team model. contains 3 attribute there are team id, name and status team
 */
public class TeamModel {
    private int team_id;
    private String team_nama;
    private String team_status;

    public TeamModel(String team_nama, String team_status) {
        super();
        this.team_nama = team_nama;
        this.team_status = team_status;
    }

    public TeamModel() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TeamModel(int team_id, String team_nama, String team_status) {
        super();
        this.team_id = team_id;
        this.team_nama = team_nama;
        this.team_status = team_status;
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
