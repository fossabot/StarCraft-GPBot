package data;


import java.util.Stack;
import bwapi.Game;
import ec.gp.GPData;
import support.Tuple;

public class GameData extends GPData {

	/**
	 * g is the StarCraft Game class, verrrry important
	 * the following variables are used inside the buildplan tree
	 * bp is Build Plan, also important
	 * s is for supply, which I don't know if I need it to be a simple integer or many, we'll see
	 * q is for quantity, it'll be our squad quantity
	 * 
	 * the following variables are used inside the main(onFrame) decision tree
	 * condition is a boolean value that will be changed real-time within the main tree inside the onFrame
	 * at is another erc that will be associated usually with the number of mineral, supply, gas or units
	 */
	
	private static final long serialVersionUID = 1;
	public transient Game g; // return value
	public transient Stack<Tuple> bp = new Stack<Tuple>(); //here we'll save our building plan, it'll have a build and a supply number, just like our usual build plan
	public int s = 200; //erc
	public int q; // yet another erc
	public transient boolean condition;
	public int at; //yet yet anothe erc
	
	public void copyTo(final GPData gpd) // copy my stuff to another GameData
	{
		initializeIfNull();
		((GameData) gpd).g = g;
		((GameData) gpd).bp = bp;
		((GameData) gpd).s = s;
		((GameData) gpd).q = q;
		((GameData) gpd).at = at;
		((GameData) gpd).condition = condition;
	}
	
	public void initializeIfNull() {
		if(bp == null) {
			bp = new Stack<Tuple>();
		}
	}
}
