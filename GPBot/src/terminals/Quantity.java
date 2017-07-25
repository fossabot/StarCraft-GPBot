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

public class Quantity extends ERC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;


	public int value1;
	public final int MAX_NUM = 20;//max number of squads to train


	public String encode() {
		return Code.encode(value1);
	}

	public String toStringForHumans() {
		return "quantity" + value1;// This is for the visual
							// representation
	}

	@Override
	public String name() {
		return "quantity";// this is for the grammar magic
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
		value1 = (int) ret.l;
		return true;
	}

	public boolean nodeEquals(GPNode node) {
		return (node.getClass() == this.getClass() && ((Quantity) node).value1 == value1);
	}

	public void readNode(EvolutionState state, DataInput input) throws IOException {
		value1 = input.readInt();
	}

	public void writeNode(EvolutionState state, DataOutput output) throws IOException {
		output.writeInt(value1);
	}

	public int expectedChildren() {
		return 0;
	}

	public void resetNode(EvolutionState state, int thread) {
		value1 = state.random[thread].nextInt(20);//I guess this is the "constructor" of the erc, it's called when the node is created, I think...
	}

	public void mutateNode(EvolutionState state, int thread) {
		//ok, this is very cheap, there's sure a better way of doing this
		//i, for the love of ecj gods, cannot find a way of including negative ints(?)
		//so I'm doing a workaround
		int v;
		int delta;
		do{
			delta = state.random[thread].nextInt(7);
			if(state.random[thread].nextBoolean()){
				v = value1 + state.random[thread].nextInt(delta);
			}
			else
				v = value1 - state.random[thread].nextInt(delta);
		}while (v < 0 || v >= MAX_NUM);
		value1 = v;
		
		//mutation is done... somehow
	}

	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem Problem) {
		((GameData) input).q = value1;
	}
}