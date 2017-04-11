package terminals;

import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.Tuple;

public class SupplyDepot extends GPNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 671776237981268191L;
	
	
	public String toString() {
		return "supply_depot";// This is for the visual
							// representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "supply_depot";//this is for the magic
	}

	public int expectedChildren() {
		return 0;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		rd.bp.push(new Tuple(UnitType.Terran_Supply_Depot, 0));
		System.out.println("Supply Depot");
	}
}
