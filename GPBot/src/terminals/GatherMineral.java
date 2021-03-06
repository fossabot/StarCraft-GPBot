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

public class GatherMineral extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "gather_mineral";// This is for the visual representation
	}

	@Override
	public String name() {
		return "gather_mineral";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData data = (GameData) input;
		Game game = data.g;
		for (Unit myUnit : game.self().getUnits()) {
			if (myUnit.getType().isWorker() && myUnit.isIdle()) {
				for (Unit neutralUnit : game.neutral().getUnits()) {
					if (neutralUnit.getType().isMineralField()) {
						myUnit.gather(neutralUnit, false);
					}

				}
			}
		}
    }
}
