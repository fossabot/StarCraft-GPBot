package nonTerminal;

import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.UnitTuple;

public class SCV extends GPNode {

	private static final long serialVersionUID = 1;

	public String toString() {
		return "scv";// This is for the visual representation
	}

	@Override
	public String name() {
		return "scv";// this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 1;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData data = (GameData) input;
		data.q = 0;
		children[0].eval(state, thread, input, stack, individual, problem);
		data.bp.push(new UnitTuple(UnitType.Terran_SCV, data.q));
		System.out.println(UnitType.Terran_SCV + "| quantity: "+ data.q);
	}


}
