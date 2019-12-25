/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niveles;

import org.newdawn.slick.Image;

/**
 *
 * @author antonio
 */
public class capitulo {
    
    
    private int startingLives;
    private int startingCoins;
    private int[][] subditoStream;
    private final String txtmapa;
    private final int lvl;
    
    public capitulo (int lives, int coins, int[][] stream, String txtmapa, int lvl){
        this.startingCoins = coins;
        this.startingLives = lives;
        this.txtmapa = txtmapa;
        this.subditoStream = stream;
        this.lvl = lvl;
    }

    public int getStartingLives() {
        return startingLives;
    }
    
    public int getLvl(){
        return lvl;
    }
    
    public int getStartingCoins() {
        return startingCoins;
    }

    public int[][] getSubditoStream() {
        return subditoStream;
    }

    public String getMapa() {
        return txtmapa;
    }
}
