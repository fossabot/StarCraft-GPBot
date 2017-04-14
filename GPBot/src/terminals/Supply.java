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
	 * Ok, this is the Supply constant
	 * it gets checked inside onFrame()
	 * it gets called while building the tree that'll be the build plan
	 * */
	private static final long serialVersionUID = -3881321596119928777L;

	public int value;
	public final int MAX_SUPPLY = 200;
	public int aux_supply = 200; //wildest guess ever

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
		return 0;
	}

	public void resetNode(EvolutionState state, int thread) {
		value = state.random[thread].nextInt(aux_supply);//I guess this is the "constructor" of the erc, it's called when the node is created, I think...
	}

	public void mutateNode(EvolutionState state, int thread) {
		//ok, this is very cheap, there's sure a better way of doing this
		//i, for the love of ecj gods, cannot find a way of including negative ints(?)
		//so I'm doing a workaround
		int v;
		int delta;
		do{
			delta = state.random[thread].nextInt(15);
			if(state.random[thread].nextBoolean()){
				v = value + state.random[thread].nextInt(delta);
			}
			else
				v = value - state.random[thread].nextInt(delta);
		}while (v < 0 || v >= MAX_SUPPLY);
		value = v;
		
		//mutation is done... somehow
	}

	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem Problem) {
		//or here
		aux_supply = ((GameData) input).s;//pfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff
		resetNode(state, thread);
		System.out.print(" supply: "+ value);
		((GameData) input).s = value;
		
	}
}
