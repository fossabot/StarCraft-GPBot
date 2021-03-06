package terminals;

import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.UnitTuple;

public class SupplyDepot extends GPNode {

	private static final long serialVersionUID = 1;


	public String toString() {
		return "supply_depot";// This is for the visual representation
	}

	@Override
	public String name() {
		return "supply_depot";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		//if (rd.g.self().supplyTotal() - rd.g.self().supplyUsed() < 5){
			//if(!rd.bp.contains(new UnitTuple(UnitType.Terran_Supply_Depot, 0))){
				rd.bp.push(new UnitTuple(UnitType.Terran_Supply_Depot, 0)); // Push of yourself and your last node, which is the supply or the quantity
				System.out.println("supply_depot");
				//}
		//}
		
    }
}
