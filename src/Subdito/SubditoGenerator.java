package Subdito;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;


public class SubditoGenerator {

    
	
	public SubditoGenerator (int[][] pLocations,int pLevel){
		Locations = pLocations;
		this.level =pLevel-1;
	}
	
	//The critter stream sets the type and quantity of each critter for each level. It can be easily adjusted
	int[][] SubditoStream = {		
			{2,1,0},
			{5,2,0},
			{6,2,1},
			{6,3,2},
			{6,3,3},
			{8,4,4},
			{9,5,5},
			{10,6,6},	
			{12,6,6},
			{15,6,8},
			{16,6,9},
			{20,8,10},
	};
        
	public void setSubditoStream(int[][] SubditoQueue) {
        this.SubditoStream = SubditoQueue;
        }

	//automate critter list generation for 100 levels after hardcoded stream
	public int[][] addSubditoList(int lvlStart){
		int[][] cListToAppend = new int[1000][3];
		for(int i=lvlStart;i<1000+lvlStart;i++){
			for(int j=0;j<3;j++){
				cListToAppend[i-lvlStart][j] = (i/3) + (4-j)*5;
			}
		}
		return cListToAppend;
	}

	//creating  critter queue and finding starting point

	private Queue<Subdito> 		SubditoQueue 	= new LinkedList<Subdito>();
	private int 				level;
	private int[][]				Locations;
	

	public void createSubditoQueue(){
		//for that level, create the critter objects as per the values in the critter stream and then randomize the queue 
		int[][] SubditoStreamToAppend = addSubditoList(SubditoStream.length);
		int[][] fullSubditoStream = new int[SubditoStream.length+SubditoStreamToAppend.length][3];

		//concatenate two 2d arrays
		for(int i=0;i<fullSubditoStream.length;i++){
			if(i<SubditoStream.length){
				fullSubditoStream[i]=SubditoStream[i];
			}
			else{
				fullSubditoStream[i]=SubditoStreamToAppend[i-SubditoStream.length];
			}
		}
		for(int x = 0; x < 3 ; x++)
		{
			for(int y = 0; y < fullSubditoStream[level][x] ; y++){	

				Subdito c = addSubdito(x);
				SubditoQueue.add(c);

			}
		}


	}
	
	
	//shuffle the order of the critters
	public void RandomizeSubQueue()
	{
		Collections.shuffle((LinkedList<Subdito>) SubditoQueue);
	}

	//add the critters according to the input x, whose value is determined by the critterStream
	private Subdito addSubdito(int x){
		if(x==0){
			Subdito c = new SubditoSoldado(Locations);
			return c;
		}
		if(x==1){
			Subdito c = new SubditoExplorador(Locations);
			return c;
		}
		if(x==2){
			Subdito c = new SubditoTanque(Locations);
			return c;
		}
                return null;
	}
	//prints the critter queue to comnsole
	public void printSubQueue(){
		
		System.out.print("<- [ ");
		for(Subdito s : SubditoQueue){
			System.out.print(s.getName()+" ");
		}
		System.out.println("]");
	}


	public Queue<Subdito> getSubditoQueue() {
		return SubditoQueue;
	}

	
	

}
