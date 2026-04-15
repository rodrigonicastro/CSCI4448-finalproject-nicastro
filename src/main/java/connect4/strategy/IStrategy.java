package connect4.strategy;

import connect4.players.Player;

public interface IStrategy{
    int determineMove(Player myself);
    default boolean isUser() { return false; }
    default boolean isRandomBot() { return  false; }
    default boolean isAIBot() { return false; }
}