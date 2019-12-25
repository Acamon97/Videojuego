package grid;


public abstract class Tile {

	private int x, y, type;
	
	
	public Tile(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}

	public void setType(int type){
		this.type = type;
	}
	
	
	public int getX(){
		return x;
	}

	
	public int getY(){
		return y;
	}

	
	public int getType(){
		return type;
	}
	
	public String toString(){
		return String.valueOf(getType());
	}
}
