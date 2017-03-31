package de.novatec.marioai.simulation;

import ch.idsia.benchmark.mario.engine.generalization.Enemy;
import ch.idsia.benchmark.mario.options.FastOpts;

public enum LevelConfig {
	
	/**
	 * Level for workshop 1 exercise 1
	 */
	HSKL_LEVEL_0(FastOpts.VIS_ON_2X + FastOpts.LEVEL_01_FLAT),
	
	/**
	 * Level for workshop 1 exercise 2
	 */
	HSKL_LEVEL_1(FastOpts.VIS_ON_2X + FastOpts.LEVEL_01_FLAT + FastOpts.L_ENEMY(Enemy.GOOMBA, Enemy.GREEN_KOOPA)),
	
	/**
	 * Level for workshop 1 exercise 3
	 */
	HSKL_LEVEL_2(FastOpts.VIS_ON_2X + FastOpts.LEVEL_01_FLAT + FastOpts.L_ENEMY(Enemy.GOOMBA, Enemy.GREEN_KOOPA, Enemy.SPIKY)),
	
	/**
	 * Level for workshop 1 exercise 4
	 */
	HSKL_LEVEL_3(FastOpts.VIS_ON_2X + FastOpts.L_RANDOM_SEED(41) + FastOpts.L_GAPS_ON + FastOpts.L_PLATFORMS_ON + FastOpts.L_COINS_OFF),
	
	/**
	 * Level for workshop 1 exercise 5
	 */
	HSKL_LEVEL_4(FastOpts.VIS_ON_2X + FastOpts.L_GAPS_ON + FastOpts.L_PLATFORMS_ON + FastOpts.L_TUBES_ON + FastOpts.L_ENEMY(Enemy.GOOMBA, Enemy.GREEN_KOOPA, Enemy.SPIKY)),

	LEVEL_1(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF + FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF
			+ FastOpts.L_HIDDEN_BLOCKS_OFF + FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)),

	LEVEL_2(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF + FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF
			+ FastOpts.L_HIDDEN_BLOCKS_OFF + FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0) + FastOpts.L_ENEMY(Enemy.GOOMBA) ),

	LEVEL_3(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF + FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF
			+ FastOpts.L_HIDDEN_BLOCKS_OFF + FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0) + FastOpts.L_ENEMY(Enemy.GREEN_KOOPA)),


	LEVEL_4(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF + FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF
			+ FastOpts.L_HIDDEN_BLOCKS_OFF + FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0) + FastOpts.L_ENEMY(Enemy.SPIKY)),


	LEVEL_5(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF + FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF
			+ FastOpts.L_HIDDEN_BLOCKS_OFF + FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0) + FastOpts.L_ENEMY(Enemy.SPIKY,Enemy.GOOMBA));

	private String options;

	private LevelConfig(String options) {
		this.options = options;
	}

	public String getOptions() {
		return options;
	}

	public String getOptionsRandomized() {
		return options + FastOpts.L_RANDOMIZE;
	}

	public String getOptionsVisualizationOff() {
		return options + FastOpts.VIS_OFF;
	}

	public String getOptionsRndVissOff() {
		return options + FastOpts.VIS_OFF + FastOpts.L_RANDOMIZE;
	}

}
