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
public class Nivel_5_Hades extends capitulo{
    
    public Nivel_5_Hades (){
        super( lives, coins, stream, mapa, lvl);
    }
    
    private static int lives = 5;
    private static int coins = 400;
    private static String mapa = "mapas/5.Hades.png";
    private static int lvl = 6;
    private static int[][] stream = { 
        {4,2,0},
        {7,2,0},
        {14,2,0},
        {9,5,0},
        {14,2,2},
        {14,4,4},
        {14,5,5},
        {16,0,7},
        {18,2,8},
        {20,0,10},
    };
}
