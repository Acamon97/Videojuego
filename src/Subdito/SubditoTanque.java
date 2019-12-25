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
public class SubditoTanque extends Subdito{
    
    
    public SubditoTanque (int[][] pLocations){
        
        super (pLocations, health, speed, armor, name, type.Tank,reward);
    }
    private static String name = "Tank";
    private static double health = 400;
    private static double speed = 0.5;
    private static int reward = 60;
    private static double armor = 20;
    
}
