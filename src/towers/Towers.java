package towers;

import game.Player;
import Subdito.Subdito;

public abstract class Towers {

    private long lastAttackTime;
    private int  level = 1;
    private int maxLevel = 3;
    int upgradeCost;


    public enum type {
        Arrow, Sniper
    }
    protected int buyingCost;
    protected int refundValue;
    protected double range;
    protected double power;
    private double xPos;
    private double yPos;
    private Subdito subdito;
    protected double reloadTime; //tiempo que tarda en atacar
    private double angleOfRotation;
    protected type towerType;
    private double critterTravelDistanceMaximun = 0;

    public Towers(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.reloadTime = reloadTime;
        angleOfRotation = 0;
        this.lastAttackTime = 0;
    }

    public boolean canAttack() {
        if ((System.currentTimeMillis()- lastAttackTime) / 1000.0 >= reloadTime) {
            return true;
        } else {
            return false;
        }
    }

    public void refoundTower() {

        //player.getPlayer().addCredits(refundValue);
    }

    public void setBuyingCost(int buyingCost) {
        this.buyingCost = buyingCost;
    }

    public int getBuyingCost() {
        //Cuanto cuesta la torre
        return buyingCost;
    }

    public int getRefundValue() {
        return refundValue;
    }

    public void setRefundValue(int refundValue) {
        this.refundValue = refundValue;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getPower() {
        return power;
    }
    
    public void refundTower() {
        Player.getPlayer().addCredits(refundValue);
    }


    public void setPower(double power) {
        this.power = power;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public Subdito getSubdito() {
        return subdito;
    }

    public void setSubdito(Subdito subdito) {
        this.subdito = subdito;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(double reloadTime) {
        this.reloadTime = reloadTime;
    }

    public double getAngleOfRotation() {
        if (subdito != null) {
            angleOfRotation = (180 / Math.PI) * Math.atan2(subdito.getYLoc() - yPos, subdito.getXLoc() - xPos);
        }
        return angleOfRotation;
    }
    public boolean upgrade() {
		if(level < maxLevel && Player.getPlayer().getCredits()>=upgradeCost){
			level++;
			Player.getPlayer().addCredits(-1*upgradeCost);
			return true;
		}
		return false;
	}

    public void setAngleOfRotation(double angleOfRotation) {
        this.angleOfRotation = angleOfRotation;
    }

    public type getTowerType() {
        return towerType;
    }

    public void setTowerType(type towerType) {
        this.towerType = towerType;
    }

    public double getSubditoTravelDistanceMaximun() {
        return critterTravelDistanceMaximun;
    }

    public void setTimeOfLastAttack(long time){
	lastAttackTime = time;
    }
    
    public double getUpgradeCost() {
		return upgradeCost;
	}

    public void setUpgradeCost(int pUpgradeCost) {
		upgradeCost = pUpgradeCost;
    }
    
    public void setSubditoTravelDistanceMaximun(double critterTravelDistanceMaximun) {
        this.critterTravelDistanceMaximun = critterTravelDistanceMaximun;
    }

}
