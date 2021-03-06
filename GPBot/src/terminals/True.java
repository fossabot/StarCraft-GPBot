package terminals;

import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class True extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "true";// This is for the visual representation
	}

	@Override
	public String name() {
		return "true";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		
		GameData data = (GameData) input;
		data.condition = true;

    }
}
