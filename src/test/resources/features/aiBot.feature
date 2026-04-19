Feature: Details of AI Bot
  As a game player
  I want to play against an intelligent bot
  So that I can play a more interesting game

  Scenario: Win the game when possible vertically
    Given an empty board
    And Player 2 is a AIBot
    And Player 2 has 3 coins in a row vertically
    When Player 2 plays a turn
    Then the game is over
    And Player 2 won the game

  Scenario: Win the game when possible horizontally
    Given an empty board
    And Player 2 is a AIBot
    And Player 2 has 3 coins in a row horizontally
    When Player 2 plays a turn
    Then the game is over
    And Player 2 won the game

  Scenario: Win the game when possible diagonally
    Given an empty board
    And Player 2 is a AIBot
    And Player 2 has 3 coins in a row diagonally from bottom-left to top-right
    When Player 2 plays a turn
    Then the game is over
    And Player 2 won the game

  Scenario: Win the game when possible diagonally the other way
    Given an empty board
    And Player 2 is a AIBot
    And Player 2 has 3 coins in a row diagonally from top-left to bottom-right
    When Player 2 plays a turn
    Then the game is over
    And Player 2 won the game