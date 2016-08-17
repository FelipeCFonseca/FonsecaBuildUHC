package me.felipefonseca.plugins.enums;

public enum GameState {

    LOBBY, IN_GAME, RESTARTING;
    public static GameState state;

    public enum Move {
        NO_MOVE;
        public static Move move;
    }

}
