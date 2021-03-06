package nonTerminal;

import java.util.ArrayList;
import java.util.List;

import bwapi.Unit;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Load extends GPNode {

	private static final long serialVersionUID = 1;

	public String toString() {
		return "load";// This is for the visual representation
	}

	@Override
	public String name() {
		return "load";// this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 1;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		// this does:
			// Given a number of units and their type
			// it loads them into the nearest ship/bunker
		GameData gd = ((GameData) (input));
		gd.q = 0;
		children[0].eval(state, thread, input, stack, individual, problem);

		int q = gd.q;
		List<Unit> ships = new ArrayList<Unit>();
		if (!gd.squads.isEmpty()) {
			for (Unit unit : gd.squads) {
				if (unit.canLoad()) {
					ships.add(unit);
				}
			}
			if (!ships.isEmpty()) {
				for (Unit unit : gd.squads) {
					if ((unit.isIdle()) && (unit.canMove())) {
						int distance_best = unit.getDistance(ships.get(0));
						Unit ship = ships.get(0);
						for (Unit ship2 : ships) {
							if (unit.getDistance(ship2) < distance_best) {
								distance_best = unit.getDistance(ship2);
								ship = ship2;
							}
						}
						if (ship.canLoad()){
							ship.load(unit);
						}
						q--;
					}
					if (q <= 0) {
						break;
					}
				}
			}
		}
	}
}
