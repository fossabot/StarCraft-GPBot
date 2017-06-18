package nonTerminal;

import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class MineralAt extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "mineral_at";// This is for the visual representation
	}

	@Override
	public String name() {
		return "mineral_at";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 1;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		
		GameData data = (GameData) input;
		children[0].eval(state, thread, input, stack, individual, problem);
		if(data.g.self().minerals() > data.at){
			data.condition = true;
		}
		else data.condition = false;
    }
}
