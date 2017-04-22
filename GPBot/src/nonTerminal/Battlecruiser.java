package nonTerminal;

import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.Tuple;

public class Battlecruiser extends GPNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3901565888957597984L;

	/**
	 * 
	 */

	
	public String toString() {
		return "battlecruiser";// This is for the visual representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "battlecruiser";//this is for the magic
	}

	public int expectedChildren() {
		return 3;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		children[children.length - 1].eval(state, thread, input, stack, individual, problem);
		rd.bp.push(new Tuple(UnitType.Terran_Battlecruiser, rd.q)); // Push de ti mismo mas el supply que te de tu nodo hijo
		
		for(int i = 0; i < children.length - 1; i++) {
			// Eval each children
			children[i].eval(state, thread, input, stack, individual, problem);
		}
		System.out.println("Battlecruiser");
    }
}
