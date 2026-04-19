Feature: details of game play
  As a player
  I want the features work as intended
  So that the game works properly

  Scenario: coin cannot be placed on full column
    Given an empty board
    And column 3 is full
    When player places a coin on column 3
    Then the move is invalid
    And the board stays the same
    
  Scenario: the game ends when the board is full
    Given an almost full board
    When player places a coin on column 0
    Then the game is over
    And Player 1 did not win the game
    And Player 2 did not win the game