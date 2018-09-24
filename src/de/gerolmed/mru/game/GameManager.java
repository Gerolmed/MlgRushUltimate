package de.gerolmed.mru.game;

import de.gerolmed.mru.Main;
import de.gerolmed.mru.map.Map;
import de.gerolmed.mru.player.Team;
import de.gerolmed.mru.utils.ChatUtils;

public class GameManager {
    private static GameManager instance;
    public static GameManager getInstance() {
        return instance;
    }

    private GameState gameState;
    private GameType gameType;
    private Map currentMap;
    private Team teamBlue, teamRed;

    public GameManager() {
        instance = this;
        setGameState(GameState.LOBBY);
        teamBlue = new Team(Team.TeamColor.BLUE);
        teamRed = new Team(Team.TeamColor.RED);

        //TODO: remove tests
        setCurrentMap(Main.getInstance().getMapManager().getMaps().get(0));
        setGameType(GameType.ONE_LINE);
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    public void start() {
        setGameState(GameState.RUNNING);
        getGameType().start(this);
    }

    public void score(Team team) {
        if(team == null)
            return;
        team.addScore(1);
        if(team.getScore() == getMaxScore())
            win(team);
    }

    public void win(Team team) {
        ChatUtils.broadcast("Victory to: " + team.getTeamColor().toString());
    }

    private int getMaxScore() {
        return 10; // TODO: read from config
    }

    public Team getOpposingTeam(Team team) {
        if(team == null)
            return null;

        if(team == teamRed)
            return teamBlue;
        else if(team == teamBlue)
            return teamRed;

        return null;
    }

    public enum GameState {
        LOBBY, RUNNING, ENDING
    }

    public Team getTeamBlue() {
        return teamBlue;
    }
    public Team getTeamRed() {
        return teamRed;
    }
}
