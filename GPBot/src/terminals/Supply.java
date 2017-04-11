package terminals;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import data.GameData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.ERC;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Code;
import ec.util.DecodeReturn;

public class Supply extends ERC {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881321596119928777L;

	public int value;


	public String encode() {
		return Code.encode(value);
	}

	public String toStringForHumans() {
		return "supply" + value;// This is for the visual
							// representation
	}

	@Override
	public String name() {
		return "supply";// this is for the grammar magic
	}

	public boolean decode(DecodeReturn ret) {
		int pos = ret.pos;
		String data = ret.data;
		Code.decode(ret);
		if (ret.type != DecodeReturn.T_INT) // uh oh! Restore and signal
											// error.
		{
			ret.data = data;
			ret.pos = pos;
			return false;
		}
		value = (int) ret.l;
		return true;
	}

	public boolean nodeEquals(GPNode node) {
		return (node.getClass() == this.getClass() && ((Supply) node).value == value);
	}

	public void readNode(EvolutionState state, DataInput input) throws IOException {
		value = input.readInt();
	}

	public void writeNode(EvolutionState state, DataOutput output) throws IOException {
		output.writeInt(value);
	}

	public int expectedChildren() {
		return 0;// this
	}

	public void resetNode(EvolutionState state, int thread) {
		value = state.random[thread].nextInt();
	}

	public void mutateNode(EvolutionState state, int thread) {
		int v;
		//add here a diff mutation
		do
			v = value + state.random[thread].nextInt();
		while (v < 0.0 || v >= 1.0);
		value = v;
	}

	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem Problem) {
		//or here
		((GameData) input).s = value;
	}
}
