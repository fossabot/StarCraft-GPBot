package terminals;

import bwapi.TechType;
import bwapi.Unit;
import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class DoSpiderMines extends GPNode {

	private static final long serialVersionUID = 1;

	public String toString() {
		return "do_spider_mines";// This is for the visual representation
	}

	@Override
	public String name() {
		return "do_spider_mines";// this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData gd = ((GameData) (input));

		if (!gd.squads.isEmpty()) {
			for (Unit unit : gd.squads) {
				if ((unit.getType() == UnitType.Terran_Vulture) && (!unit.isAttacking())) {
					if (TechType.Spider_Mines.targetsPosition()) {
						unit.useTech(TechType.Spider_Mines, unit.getPosition());
					} else if (TechType.Spider_Mines.targetsUnit()) {
						unit.useTech(TechType.Spider_Mines, unit);
					} else
						unit.useTech(TechType.Spider_Mines);
				}

			}
		}

	}
}
