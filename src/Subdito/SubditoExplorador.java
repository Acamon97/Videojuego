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
public class SubditoExplorador extends Subdito{
    
    
    public SubditoExplorador (int[][] pLocations){
        
        super (pLocations, health, speed, armor, name, type.Pathfinder,reward);
    }
    private static String name = "Pathfinder";
    private static double health = 100;
    private static double speed = 1.4;
    private static int reward = 30;
    private static double armor = 0;
    
}
