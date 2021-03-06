package terminals;

import java.util.ArrayList;
import java.util.List;

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

public class DoRestoration extends GPNode {

	private static final long serialVersionUID = 1;

	public String toString() {
		return "do_restoration";// This is for the visual representation
	}

	@Override
	public String name() {
		return "do_restoration";// this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData gd = ((GameData) (input));
		List<Unit> medics = new ArrayList<Unit>();
		for (Unit unit : gd.squads) {
			if (unit.getType() == UnitType.Terran_Medic) {
				medics.add(unit);
			}
		}
		if (!medics.isEmpty()) {
			if (!gd.squads.isEmpty()) {
				for (Unit unit : gd.squads) {
					if ((unit.isBlind()) || (unit.isEnsnared()) || (unit.isHallucination()) || (unit.isIrradiated())
							|| (unit.isLockedDown()) || (unit.isParasited()) || (unit.isPlagued())
							|| (unit.isStasised())) {
						Unit medic = medics.get(0);
						int distance = unit.getDistance(medics.get(0));
						for (Unit medic2 : medics) {
							// get nearest medic
							if ((unit.getDistance(medic2) < distance) && (unit.getType() == UnitType.Terran_Medic)) {
								distance = unit.getDistance(medic2);
								medic = medic2;
							}
						}

						if (TechType.Restoration.targetsPosition()) {
							medic.useTech(TechType.Restoration, unit.getPosition());
						} else if (TechType.Restoration.targetsUnit()) {
							medic.useTech(TechType.Restoration, unit);
						} else
							medic.useTech(TechType.Restoration);
					}

				}
			}
		}

	}
}
