package Connect4.Strategy;

import Connect4.Players.Player;

public interface IStrategy{
    int determineMove(Player myself);
    default boolean isUser() { return false; }
    default boolean isRandomBot() { return  false; }
    default boolean isAIBot() { return false; }
}