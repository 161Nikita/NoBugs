package homework_2;

public class GameSettings {
    static int maxPlayers = 5;
    final String GAME_NAME;
    int currentPlayers;

    GameSettings(String someGAME_NAME, int someCurrentPlayers) {
        this.GAME_NAME = someGAME_NAME;
        this.currentPlayers = someCurrentPlayers;
    }

    static int setMaxPlayers(int Players) {
        return maxPlayers = Players;
    }

    int addPlayer() {
       return currentPlayers++;
    }

    void printGameStatus(){
        System.out.println("Название: " + GAME_NAME + " Текущее количество игроков: " + this.currentPlayers + " Максимальное количество игроков " + maxPlayers);
    }
}