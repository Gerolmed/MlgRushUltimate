package de.gerolmed.mru.player;

public class Team {

    private TeamColor teamColor;
    private MlgPlayer mlgPlayer;

    private int score;

    public Team(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public MlgPlayer getMlgPlayer() {
        return mlgPlayer;
    }

    public void setMlgPlayer(MlgPlayer mlgPlayer) {
        this.mlgPlayer = mlgPlayer;

        if(this.mlgPlayer != null)
            this.mlgPlayer.setTeam(this);
    }
    public TeamColor getTeamColor() {
        return teamColor;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public enum TeamColor {
        RED,BLUE
    }
}
