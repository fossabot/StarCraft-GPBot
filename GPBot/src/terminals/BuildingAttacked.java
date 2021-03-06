package terminals;

import bwapi.Game;
import bwapi.Unit;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class BuildingAttacked extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "building_attacked";// This is for the visual representation
	}

	@Override
	public String name() {
		return "building_attacked";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData data = (GameData) input;
		Game game = data.g;
		data.condition = false;
		for (Unit myUnit : game.self().getUnits()) {
			if (myUnit.isUnderAttack()) {
				data.condition = true;
			} 
		}

    }
}
