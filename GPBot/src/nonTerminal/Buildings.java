package nonTerminal;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Buildings extends GPNode {
	
	
	/**
	 * This node is here in hopes it's called recursively(?) so build plan can have more than one building
	 */
	private static final long serialVersionUID = 9066493861339801532L;

	public String toString() {
		return "buildings";// This is for the visual
	}

	@Override
	public String name() {
		return "buildings";//this is for the magic
	}

	public int expectedChildren() {
		return 2;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		children[0].eval(state, thread, input, stack, individual, problem);
		children[1].eval(state, thread, input, stack, individual, problem);
	}
}
