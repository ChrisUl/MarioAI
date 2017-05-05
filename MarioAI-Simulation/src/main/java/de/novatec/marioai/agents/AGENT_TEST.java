package de.novatec.marioai.agents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.idsia.agents.IAgent;
import ch.idsia.benchmark.mario.engine.input.MarioInput;
import de.novatec.marioai.MarioAiAgent;
import de.novatec.marioai.simulation.LevelConfig;
import de.novatec.marioai.simulation.MarioAiRunner;

/**
 * Agent that sprints forward, jumps and shoots.
 * <p>
 * This agent has successful rate ~ 50%.
 *
 * @author Jakub 'Jimmy' Gemrot, gemrot@gamedev.cuni.cz
 */
public class AGENT_TEST extends MarioAiAgent {

	private static Logger log = LoggerFactory.getLogger(AGENT_TEST.class);

	@Override
	public MarioInput doAiLogic() {
		// ALWAYS RUN RIGHT
		getMarioControl().runRight();

		// ALWAYS SHOOT
		getMarioControl().shoot();

		// ENEMY || BRICK AHEAD => JUMP
		// WARNING: do not press JUMP if UNABLE TO JUMP!
		if (isEnemyAhead() || isBrickAhead())
			getMarioControl().jump();

		// If in the air => keep JUMPing
		if (!getMarioEntity().onGround)
			getMarioControl().jump();

		return getMarioInput();
	}

	@Override
	public String getName() {
		return "AGENT_TEST";
	}

	public static void main(String[] args) {
		IAgent agent = new AGENT_TEST();
		MarioAiRunner.run(agent, LevelConfig.LEVEL_3, false, false);
	}
}