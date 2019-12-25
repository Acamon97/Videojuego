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
public class Nivel_4_Poseidon extends capitulo{
    
    public Nivel_4_Poseidon (){
        super( lives, coins, stream, mapa, lvl);
    }
    
    private static int lives = 6;
    private static int coins = 300;
    private static String mapa = "mapas/4.Poseidon.png";
    private static int lvl = 5;
    private static int[][] stream = { 
        {3,1,0},
        {5,2,0},
        {7,4,0},
        {8,3,0},
        {8,4,1},
        {10,6,1},
        {10,0,2},
        {14,5,1},
        {12,4,3},
        {14,6,4},
    };
}
