/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niveles;

/**
 *
 * @author antonio
 */
public class Nivel_3_Apolo extends capitulo{
    
    public Nivel_3_Apolo (){
        super( lives, coins, stream, mapa, lvl);
    }
    
    private static int lives = 8;
    private static int coins = 250;
    private static String mapa = "mapas/3.Apolo.png";
    private static int lvl = 4;
    private static int[][] stream = { 
        {3,0,0},
        {4,1,0},
        {4,3,0},
        {6,2,0},
        {7,3,0},
        {8,6,0},
        {10,6,0},
        {10,0,1},
        {10,4,2},
        {12,4,2},
    };
}
