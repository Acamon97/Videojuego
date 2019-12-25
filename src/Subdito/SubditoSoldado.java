/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subdito;

/**
 *
 * @author Francesco
 */
public class SubditoSoldado extends Subdito{
    
    
    public SubditoSoldado (int[][] pLocations){      
        super (pLocations, health, speed, armor, name, type.Soldier, reward);
    }
    private static String name = "Soldier";
    private static double health = 250;
    private static double speed = 0.75;
    private static int reward = 40;
    private static double armor = 10;
    
}
