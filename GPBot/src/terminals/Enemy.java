package terminals;

import bwapi.Player;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Enemy extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "enemy";// This is for the visual representation
	}

	@Override
	public String name() {
		return "enemy";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		//too easy, may not work
		GameData gd = ((GameData) (input));
		for (Player enemy : gd.g.enemies()){
			gd.enemies = enemy.getUnits();
		}
    }
}
