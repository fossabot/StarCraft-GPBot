package problem;

import ec.util.*;
import support.ExeContext;
import support.Tuple;
import ec.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

import bot.Bot;
import data.GameData;
import ec.gp.*;
import ec.gp.koza.*;

import ec.simple.*;

public class StarCraftBot extends GPProblem implements SimpleProblemForm {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1;

	public static final String P_DATA = "data";

	protected BlockingQueue<Tuple<Integer,Double>> fitnessQueue = null;
	protected BlockingQueue<ExeContext> individualsQueue = null;
	public static String[] arguments;

	public Bot bot;
	
	public void setup(final EvolutionState state, final Parameter base) {
		// very important, remember this
		super.setup(state, base);

		// verify our input is the right class (or subclasses from it)
		if (!(input instanceof GameData))
			state.output.fatal("GPData class must subclass from " + GameData.class, base.push(P_DATA), null);
	}

	protected Thread workerThread;
	
	@SuppressWarnings("deprecation")
	@Override
	public void finishEvaluating(EvolutionState state, int threadnum) {
		// TODO Auto-generated method stub
		super.finishEvaluating(state, threadnum);
		
		try {
			//workerThread.interrupt();
			workerThread.stop();
			System.err.println("Esperando aqui sentado a que hilo termine");
			workerThread.join();
			System.err.println("El hilo ha terminado y todo funciona, o eso creo");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected EvolutionState currentEvolutionState;
	protected Individual currentIndividual;
	
	public void evaluate(final EvolutionState state, final Individual ind, final int subpopulation,
			final int threadnum) {
		if (!ind.evaluated) // don't bother reevaluating
		{	
			this.currentEvolutionState = state;
			this.currentIndividual = ind;
			ExeContext c = new ExeContext (state, ((GPIndividual) ind), threadnum, stack, (GameData) this.input,  this);
			try {
				this.individualsQueue.put(c);
				((GameData) input).g = bot.getGame();                                                                                 
				c.setInput((GameData) input);

				// Wait for game to finish...
				Tuple<Integer, Double> results = this.fitnessQueue.take();
				
				KozaFitness f = ((KozaFitness) currentIndividual.fitness);
				f.setStandardizedFitness(currentEvolutionState, results.getY());
				f.hits = results.getX();
				currentIndividual.evaluated = true;
				//((GameData) input).g.leaveGame();
				
				System.err.println("I'M OUTTA HERE!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

							
			
		}
	}

	public static void main(String[] args) {
		run(args);
	}

	public StarCraftBot() {
		
		//arguments = args;
		fitnessQueue = new SynchronousQueue<>();
		individualsQueue = new SynchronousQueue<>();
		bot = new Bot(fitnessQueue, individualsQueue);		
		
		workerThread = new Thread(bot);
		workerThread.start();
		

	}
	
	@Override
	public void describe(EvolutionState state, Individual ind, int subpopulation, int threadnum, int log) {
		// TODO Auto-generated method stub
		super.describe(state, ind, subpopulation, threadnum, log);
	}

	@Override
	public void prepareToEvaluate(EvolutionState state, int threadnum) {
		// TODO Auto-generated method stub
		super.prepareToEvaluate(state, threadnum);
	}

	public static void run(String[] arguments) {
		// TODO Auto-generated method stub
		Evolve.main(arguments);
	}

}
