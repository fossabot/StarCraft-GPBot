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

public class CommandCenter extends GPNode {

	private static final long serialVersionUID = -7928947479251600424L;

	public String toString() {
		return "command_center";// This is for the visual
							// representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "command_center";//this is for the magic
	}

	public int expectedChildren() {
		return 0;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		rd.bp.push(new Tuple(UnitType.Terran_Command_Center, 0));
		System.out.println("Command Center");
	}
}
