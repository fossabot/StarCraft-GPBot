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

public class DoScanner extends GPNode {

	private static final long serialVersionUID = 1;

	
	public String toString() {
		return "do_scanner";// This is for the visual representation
	}

	@Override
	public String name() {
		return "do_scanner";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {
		
		GameData gd = ((GameData) (input));

		for (Unit unit : gd.buildings) {
			if (unit.getType() == UnitType.Terran_Comsat_Station) {
				unit.useTech(TechType.Scanner_Sweep);
			}
		}
    }
}
