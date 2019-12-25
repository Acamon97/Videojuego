 package game;

public class Player {

    private static int credits = 0;
    private static int lives;
    private static int STARTINGCREDITS = 220;
    private static int STARTINGLIVES = 10;

    public static void setLives(int STARTINGLIVES) {
        Player.lives = STARTINGLIVES;
    }

    public static void setCredits(int STARTINGCREDITS) {
        Player.credits = STARTINGCREDITS;
    }
    private static Player instance = null;

    public static synchronized Player getPlayer() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void reset() {
        lives = STARTINGLIVES;
        credits = STARTINGCREDITS;
    }

    public void addCredits(int amount) {
        credits += amount;
    }

    public void addLife() {
        lives++;
    }

    public void decreaseLife() {
        lives--;
    }

    public int getLives() {
        return lives;
    }

    public int getCredits() {
        return credits;
    }
}
