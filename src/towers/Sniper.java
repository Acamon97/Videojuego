/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package towers;

/**
 *
 * @author FrancescoAndreace
 */


public class Sniper extends Towers {

    public Sniper(double xPos, double yPos) {
        super(xPos, yPos);
        this.buyingCost = newbuyingCost;
        this.refundValue = newRefundValue;
        this.reloadTime = newReloadTime;
        this.power = newPower;
        this.range = newRange;
        this.upgradeCost = newUpgradeCost;
        this.towerType = Towers.type.Sniper;
    }
    //los atributos estan puestos al azar
    private static int newbuyingCost = 200;
    private static int newRefundValue = 180;
    private static double newReloadTime = 1.75;
    private static int newUpgradeCost = 100;
    private static double newRange = 140;
    private static double newPower = 60;

}
