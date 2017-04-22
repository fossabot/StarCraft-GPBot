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

public class ScienceVessel extends GPNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8943707298669249234L;

	
	public String toString() {
		return "science_vessel";// This is for the visual representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "science_vessel";//this is for the magic
	}

	public int expectedChildren() {
		return 3;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		children[children.length - 1].eval(state, thread, input, stack, individual, problem);
		rd.bp.push(new Tuple(UnitType.Terran_Science_Vessel, rd.q)); // Push de ti mismo mas el supply que te de tu nodo hijo
		
		for(int i = 0; i < children.length - 1; i++) {
			// Eval de cada hijo que es otro edificio
			children[i].eval(state, thread, input, stack, individual, problem);
		}
		System.out.println("Science Vessel");
    }
}
