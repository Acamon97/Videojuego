/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subdito;

/**
 *
 * @author Antonio
 */
abstract public class Subdito {

    private int[][] locations;
    private int reward;

    
    
    public enum type{Tank, Pathfinder, Soldier};
    public enum direccion{LEFT,RIGHT, UP, DOWN};
    private double health;
    direccion direccionSubdito;

    private double speed; 
    private double armor;
    private double XLoc;
    private double YLoc;
    private String name;
    private boolean alive;
    private int locationIncrementer = 0;
    private final double freezeMultiplier = 0.3;	//how much speed is reduced by when frozen
    private boolean frozen = false;
    private long freezeStartTime = -10000;	//when the critter was frozen
    private long freezeDuration = 4000;	//How long freeze lasts in ms
    public boolean canMove = true;
    protected type tipoSubdito;
    private double distanciaRecorrida;
    private boolean arrive = false;

    private boolean visible = true;
    
    
    public Subdito (int[][] pLocations, double pHealth, double pSpeed, double pArmor, String pName, type pTipoSubdito, int reward){
        this.health = pHealth;
        this.reward = reward;
        this.armor = pArmor;
        this.speed = pSpeed;
        this.name = pName;
        XLoc = pLocations[0][0];
	YLoc = pLocations[0][1];
        locations = pLocations;
        alive = true;
        this.tipoSubdito = pTipoSubdito;
        distanciaRecorrida = 0;
        
    }
    
    public void takeDamage (double damage){
        health = health - (damage - armor);
        if (health <= 0){
            alive = false;
            visible = false;
        }
    }
    
    public void move(){
        if(System.currentTimeMillis() > freezeStartTime + freezeDuration&&frozen){
			unfreezeSubdito();
		}

		if(locationIncrementer ==0)
		{
			visible = true;
		}

		try{

			if(!(XLoc>locations[locationIncrementer+1][0]-this.getSpeed()&&XLoc<locations[locationIncrementer+1][0]+this.getSpeed()) ){
				if(XLoc<=locations[locationIncrementer+1][0]){
					XLoc += this.getSpeed();
					distanciaRecorrida+=this.getSpeed();
					direccionSubdito = Subdito.direccion.RIGHT;
				}
				else if(XLoc>=locations[locationIncrementer+1][0])
				{
					XLoc -= this.getSpeed();
					distanciaRecorrida+=this.getSpeed();
					direccionSubdito = Subdito.direccion.LEFT;
				}
			}
			else if(!(YLoc>=locations[locationIncrementer+1][1]-this.getSpeed()&&YLoc<=locations[locationIncrementer+1][1]+this.getSpeed()) ){
				if(YLoc<=locations[locationIncrementer+1][1]){
					YLoc += this.getSpeed();
					distanciaRecorrida+=this.getSpeed();
					direccionSubdito = Subdito.direccion.DOWN;
				}
				else if(YLoc>=locations[locationIncrementer+1][1]){
					YLoc -= this.getSpeed();
					distanciaRecorrida+=this.getSpeed();
					direccionSubdito = Subdito.direccion.UP;
				}
			}
			else{
				locationIncrementer++;
			}


		}
		catch(IndexOutOfBoundsException e){
			visible=false;
			arrive = true;
		}
	}
    
    
    //Getters
    
    
    public double getHealth() {
        return health;
    }

    public double getSpeed() {
        return speed;
    }

    public double getArmor() {
        return armor;
    }

    public double getXLoc() {
        return XLoc;
    }

    public double getYLoc() {
        return YLoc;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getReward() {
        return reward;
    }
    public boolean isCanMove() {
        return canMove;
    }

    public type getTipoSubdito() {
        return tipoSubdito;
    }

    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public boolean isVisible() {
        return visible;
    }
    
    public boolean isArrive() {
        return arrive;
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }
    
    public void freezeSubdito(){
		this.frozen = true;
		freezeStartTime = System.currentTimeMillis();
	}
	public void unfreezeSubdito(){
		this.frozen = false;
		freezeStartTime = -10000;
	}
    
    public direccion getDireccionSubdito() {
        return direccionSubdito;
    }
}
