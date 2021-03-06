package terminals;

import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import support.UpgradeTuple;
import bwapi.UpgradeType;


public class InfWeapon extends GPNode {

	private static final long serialVersionUID = 1;


	public String toString() {
		return "inf_weapon";// This is for the visual representation
	}

	@Override
	public String name() {
		return "inf_weapon";//this is for the magic, aka the grammar
	}

	public int expectedChildren() {
		return 0;
	}

	public void eval(final EvolutionState state, final int thread, final GPData input, final ADFStack stack,
			final GPIndividual individual, final Problem problem) {

		GameData rd = ((GameData) (input));
		rd.bp.push(new UpgradeTuple(UpgradeType.Terran_Infantry_Weapons, 0)); // Push of yourself and your last node, which is the supply or the quantity
		System.out.println("inf_weapon");
    }
}
