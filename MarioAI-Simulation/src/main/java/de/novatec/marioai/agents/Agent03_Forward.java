package de.novatec.marioai.agents;

import ch.idsia.agents.AgentOptions;
import ch.idsia.agents.IAgent;
import ch.idsia.agents.controllers.MarioHijackAIBase;
import ch.idsia.benchmark.mario.engine.LevelScene;
import ch.idsia.benchmark.mario.engine.VisualizationComponent;
import ch.idsia.benchmark.mario.engine.input.MarioInput;
import ch.idsia.benchmark.mario.environments.IEnvironment;

import java.awt.*;

/**
 * An agent that sprints forward and jumps if it detects an obstacle ahead.
 *
 * @author Jakub 'Jimmy' Gemrot, gemrot@gamedev.cuni.cz
 */
public class Agent03_Forward extends MarioHijackAIBase implements IAgent {

    @Override
    public void reset(AgentOptions options) {
        super.reset(options);
    }

    private boolean enemyAhead() {
        return
                e.danger(1, 0) || e.danger(1, -1)
                        || e.danger(2, 0) || e.danger(2, -1)
                        || e.danger(3, 0) || e.danger(2, -1);
    }

    private boolean brickAhead() {
        return
                t.brick(1, 0) || t.brick(1, -1)
                        || t.brick(2, 0) || t.brick(2, -1)
                        || t.brick(3, 0) || t.brick(3, -1);
    }

    @Override
    public void debugDraw(VisualizationComponent vis, LevelScene level, IEnvironment env, Graphics g) {
        super.debugDraw(vis, level, env, g);
        String debug = "";
        if (enemyAhead()) {
            debug += "|ENEMY AHEAD|";
        }
        if (brickAhead()) {
            debug += "|BRICK AHEAD|";
        }
        if (mario != null && mario.onGround) {
            debug += "|ON GROUND|";
        }
        VisualizationComponent.drawStringDropShadow(g, debug, 0, 26, 1);
    }

    public MarioInput actionSelectionAI() {
        // ALWAYS RUN RIGHT
        control.runRight();

        // ALWAYS SPEED RUN
        control.sprint();

        // IF (ENEMY || BRICK AHEAD) => JUMP
        if (enemyAhead() || brickAhead())
            control.jump();

        // If (In the air) => keep JUMPing
        if (!mario.onGround) {
            control.jump();
        }

        return action;
    }

}