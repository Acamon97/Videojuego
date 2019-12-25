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
public class Nivel_1_Artemisa extends capitulo{
    
    public Nivel_1_Artemisa (){
        super( lives, coins, stream, mapa, lvl);
    }
    
    private static int lives = 15;
    private static int coins = 150;
    private static String mapa = "mapas/1.Artemisa.png";
    private static int lvl = 2;
    private static int[][] stream = { 
        {0,2,0},
        {1,2,0},
        {4,0,0},
        {6,0,0},
        {4,3,0},
        {6,4,0},
        {7,5,0},
        {10,0,0},
        {10,2,0},
        {8,0,2},
    };
}
