package homework_13.builder_character;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();

        Character character1 = game.constructPowerfulCharacter("Nikita");
        System.out.println(character1);

        Character character2 = game.constructDefaultCharacter("Lev");
        System.out.println(character2);
    }
}
