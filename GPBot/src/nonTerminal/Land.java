package nonTerminal;

import bwapi.Unit;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Land extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "land";// This is for the visual representation
	}

	@Override
	public String name() {
		return "land";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 1;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {
		GameData data = (GameData) input;
		data.q = 0;
		int count = 0;
		children[0].eval(state, thread, input, stack, individual, problem);
		for (Unit myUnit : data.g.self().getUnits()) {
			if(data.q < count) break;
			if (myUnit.getType().isBuilding()) {
				if (myUnit.canLand()){
					myUnit.land(myUnit.getTilePosition());
					count++;
				}
			}
		}
    }
}
