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
public class Nivel_6_Zeus extends capitulo{
    
    public Nivel_6_Zeus (){
        super( lives, coins, stream, mapa, lvl);
    }
    
    private static int lives = 1;
    private static int coins = 300;
    private static String mapa = "mapas/6.Zeus.png";
    private static int lvl = 6;
    private static int[][] stream = { 
        {5,0,0},
        {8,2,0},
        {16,2,0},
        {11,5,0},
        {16,4,2},
        {17,6,4},
        {18,7,5},
        {22,0,7},
        {20,6,8},
        {24,0,14},
    };
}
