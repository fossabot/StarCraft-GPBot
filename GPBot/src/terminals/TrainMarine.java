package terminals;

import bwapi.Player;
import bwapi.Unit;
import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import problem.StarCraftBot;

public class TrainMarine extends GPNode {

	/**
	 * if there are enough minerals and at least one barrack, train a marine
	 */
	private static final long serialVersionUID = 6080692280755707043L;

	public String toString() {
		return "train marines";// This is for the visual representation
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {
		
		
		GameData rd = ((GameData) (input));
		rd.g = ((StarCraftBot) problem).myDamnGame;

		Player self = rd.g.self();

		for (Unit myUnit : self.getUnits()) {// I would like to know if this is
												// as important as I think/It is
			
			// This one is just to print units' position and type
			// units.append(myUnit.getType()).append("").append(myUnit.getTilePosition()).append("\n");

			
			//in BWAPI the cost of minerals is doubled because a zerling costs 25, here it would cost 50 minerals
				myUnit.train(UnitType.Terran_Marine);
				System.out.println("I'm training marines");

			

		}
	}
}