package me.felipefonseca.plugins.utils;

import me.felipefonseca.plugins.Main;

public class MessagesLoader {

    private final Main plugin;

    public MessagesLoader(Main plugin) {
        this.plugin = plugin;
    }

    private String prefix;

    private String lobbyState;
    private String inGameState;
    private String restartingState;

    private String welcomeMessage;
    private String welcomeTitle;
    private String welcomeSubtitle;

    private String saveInventory;
    private String goodluck;
    private String winnerMessage;

    private String scoreboardTitle;
    private String scoreboardLine1;
    private String scoreboardLine2;
    private String scoreboardLine3;
    private String scoreboardLine4;
    private String scoreboardline5;
    private String scoreboardline6;
    private String scoreboardline7;
    private String scoreboardline8;
    private String scoreboardline9;
    private String scoreboardline10;
    
    private String startingMessage;

    private String playerDeath;
    private String playerDeathByPlayer;
    private String player_shot;

    private String mapName;
    private String authorName;

    private static String goldenHead;

    public void init() {
        prefix = plugin.getConfig().getString("prefix").replace("&", "§");
        //State
        lobbyState = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.State.Lobby").replace("&", "§");
        inGameState = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.State.InGame").replace("&", "§");
        restartingState = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.State.Ending").replace("&", "§");
        //Game
        welcomeMessage = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.welcomeMessage").replace("&", "§");
        welcomeTitle = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.welcomeTitle").replace("&", "§");
        welcomeSubtitle = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.welcomeSubtitle").replace("&", "§");
        saveInventory = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.saveInventory").replace("&", "§");
        goodluck = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.goodluck").replace("&", "§");
        goldenHead = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.goldenHead").replace("&", "§");
        winnerMessage = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.winnerMessage").replace("&", "§");
        startingMessage = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.starting").replace("&", "§");
        playerDeath = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.player_death").replace("&", "§");
        playerDeathByPlayer = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.player_death_by_player").replace("&", "§");
        player_shot = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.player_shot").replace("&", "§");
        mapName = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.arenaName").replace("&", "§");
        authorName = plugin.getLangConfiguration().getLangConfiguration().getString("BuildUHC.Game.authorName").replace("&", "§");

        scoreboardTitle = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.title").replace("&", "§");
        scoreboardLine1 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line1").replace("&", "§");
        scoreboardLine2 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line2").replace("&", "§");
        scoreboardLine3 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line3").replace("&", "§");
        scoreboardLine4 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line4").replace("&", "§");
        scoreboardline5 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line5").replace("&", "§");
        scoreboardline6 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line6").replace("&", "§");
        scoreboardline7 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line7").replace("&", "§");
        scoreboardline8 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line8").replace("&", "§");
        scoreboardline9 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line9").replace("&", "§");
        scoreboardline10 = plugin.getLangConfiguration().getLangConfiguration().getString("scoreboard.line10").replace("&", "§");
        
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLobbyState() {
        return lobbyState;
    }

    public String getInGameState() {
        return inGameState;
    }

    public String getRestartingState() {
        return restartingState;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public String getWelcomeTitle() {
        return welcomeTitle;
    }

    public String getWelcomeSubtitle() {
        return welcomeSubtitle;
    }

    public String getSaveInventory() {
        return saveInventory;
    }

    public String getWinnerMessage() {
        return winnerMessage;
    }

    public String getStartingMessage() {
        return startingMessage;
    }

    public String getPlayerDeath() {
        return playerDeath;
    }

    public String getPlayerDeathByPlayer() {
        return playerDeathByPlayer;
    }

    public String getPlayer_shot() {
        return player_shot;
    }

    public static String getGoldenHead() {
        return goldenHead;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getMapName() {
        return mapName;
    }

    public String getGoodluck() {
        return goodluck;
    }

    public String getScoreboardTitle() {
        return scoreboardTitle;
    }

    public String getScoreboardLine1() {
        return scoreboardLine1;
    }

    public String getScoreboardLine2() {
        return scoreboardLine2;
    }

    public String getScoreboardLine3() {
        return scoreboardLine3;
    }

    public String getScoreboardLine4() {
        return scoreboardLine4;
    }

    public String getScoreboardline5() {
        return scoreboardline5;
    }

    public String getScoreboardline6() {
        return scoreboardline6;
    }

    public String getScoreboardline7() {
        return scoreboardline7;
    }

    public String getScoreboardline8() {
        return scoreboardline8;
    }

    public String getScoreboardline9() {
        return scoreboardline9;
    }

    public String getScoreboardline10() {
        return scoreboardline10;
    }
    
    
            

}
