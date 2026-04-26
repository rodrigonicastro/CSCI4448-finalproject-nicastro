[Project Logs](https://docs.google.com/document/d/1r77rkOFWH4Ak9dA77e6ci9oQjwvZd4cFC9xunDwMeO0/edit?usp=sharing)

# Connect 4!

This is a game of Connect4 developed using Java. Run the Main function to start the game. Once in the game, use the up/down arrows to navigate the menu, ENTER to confirm.

To control Player 1, use the left/right arrows, and if you are playing with a friend, tell them to use A/D to control Player 2. ENTER places a coin in the selected row for both players.

# Patterns
I used many OOAD concepts in the development of this project, including:
* Singleton Pattern
  * The Singleton Pattern was used in the Board class, which contains the board the game is played on. This was implemented to avoid a mismatch of the board between the players and the game itself;
* Strategy Pattern
  * To be able to change between different behaviors of players during runtime, the Strategy Pattern was used in the Player class;
* Factory Pattern
  * The Factory Pattern was implemented in the Player and Strategy classes, allowing for smoother creation of the needed objects;
* State Pattern
  * To manage the game, and traverse the many different stages of the program, the State Pattern was used in the Connect4 class;
