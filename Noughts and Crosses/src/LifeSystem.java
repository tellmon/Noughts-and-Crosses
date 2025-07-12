/**
 * Manages the life totals for players X and O in the game.
 * Handles reducing lives, checking for a game over condition, 
 * and resetting life totals to the starting value.
 */
public class LifeSystem {
    
    /**
     * The number of lives each player starts with.
     */
    static int start = 5;
    
    /**
     * Current life total for player X.
     */
    static int XLife = start;
    
    /**
     * Current life total for player O.
     */
    static int OLife = start;
    
    /**
     * Checks the life total of a player (X or O), reduces it by 1, and triggers the death screen if their life total reaches 0.
     * 
     * @param XandOlife the character representing the player ('x' for player X or 'o' for player O)
     */
    public static void CheckLife(char XandOlife) {
        if ('x' == XandOlife) {
            XLife -= 1;
        }
        
        if ('o' == XandOlife) {
            OLife -= 1;
        }
        
        if (XLife <= 0) {
            DeathScreen.CreateDeathScreen("O");
        }
        
        if (OLife <= 0) {
            DeathScreen.CreateDeathScreen("X");
        }
    }
    
    /**
     * Returns the current life total for player O.
     * 
     * @return the life total of player O
     */
    public static int getOLifes() {
        return OLife;
    }
    
    /**
     * Returns the current life total for player X.
     * 
     * @return the life total of player X
     */
    public static int getXLifes() {
        return XLife;
    }
    
    /**
     * Resets the life totals for both players (X and O) to the starting value.
     */
    public static void resetLifes() {
        XLife = start;
        OLife = start;
    }
}
