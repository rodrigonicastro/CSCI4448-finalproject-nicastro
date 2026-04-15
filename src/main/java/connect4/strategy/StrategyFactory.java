package Connect4.Strategy;

public class StrategyFactory{
    public IStrategy newUserStrategy() { return new UserStrategy(); }

    public IStrategy newRandomBotStrategy() { return new RandomBotStrategy(); }

    public IStrategy newAIBotStrategy() { return new AIBotStrategy(); }
}