package nonTerminal;

import bwapi.UnitType;
import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.Tuple;

public class Academy extends GPNode {

	/**
	 * 
	 */

	//OO gods forbid me
	private String name = "academy";
	private int noChildren = 1;
	private UnitType unit = UnitType.Terran_Academy;
	
	
	
	public String toString() {
		return this.name;// This is for the visual
							// representation
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return this.name;//this is for the magic
	}

	public int expectedChildren() {
		return noChildren;//this
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		children[0].eval(state, thread, input, stack, individual, problem);
		rd.bp.push(new Tuple(unit, rd.s));


	}
}
