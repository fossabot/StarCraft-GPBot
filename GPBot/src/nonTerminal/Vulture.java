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

public class Vulture extends GPNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8350044677122976929L;

	
	public String toString() {
		return "vulture";// This is for the visual representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "vulture";//this is for the magic
	}

	public int expectedChildren() {
		return 2;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		children[children.length - 1].eval(state, thread, input, stack, individual, problem);
		rd.bp.push(new Tuple(UnitType.Terran_Vulture, rd.q)); // Push de ti mismo mas el supply que te de tu nodo hijo
		
		for(int i = 0; i < children.length - 1; i++) {
			// Eval de cada hijo que es otro edificio
			children[i].eval(state, thread, input, stack, individual, problem);
		}
		System.out.println("Vulture");
    }
}
