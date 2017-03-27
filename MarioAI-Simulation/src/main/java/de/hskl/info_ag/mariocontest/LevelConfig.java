package de.hskl.info_ag.mariocontest;

import ch.idsia.benchmark.mario.engine.SimulatorOptions.ReceptiveFieldMode;
import ch.idsia.benchmark.mario.engine.generalization.Enemy;
import ch.idsia.benchmark.mario.options.FastOpts;

public enum LevelConfig {
	HSKL_LEVEL_0(FastOpts.VIS_ON_2X + FastOpts.LEVEL_01_FLAT),

	HSKL_LEVEL_1(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)),

	HSKL_LEVEL_1_DEBUG(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.VIS_FIELD(ReceptiveFieldMode.GRID)),

	HSKL_LEVEL_2(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.GOOMBA)),

	HSKL_LEVEL_2_DEBUG(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.GOOMBA) + FastOpts.VIS_FIELD(ReceptiveFieldMode.GRID)),

	HSKL_LEVEL_3(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.GREEN_KOOPA)),

	HSKL_LEVEL_3_DEBUG(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.GREEN_KOOPA) + FastOpts.VIS_FIELD(ReceptiveFieldMode.GRID)),

	HSKL_LEVEL_4(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.SPIKY)),

	HSKL_LEVEL_4_DEBUG(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.SPIKY) + FastOpts.VIS_FIELD(ReceptiveFieldMode.GRID)),

	HSKL_LEVEL_5(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.SPIKY, Enemy.GOOMBA)),

	HSKL_LEVEL_5_DEBUG(FastOpts.VIS_ON_2X + FastOpts.L_FLAT_OFF + FastOpts.L_BLOCKS_OFF + FastOpts.L_CANNONS_OFF
			+ FastOpts.L_COINS_OFF + FastOpts.L_DEAD_ENDS_OFF + FastOpts.L_GAPS_OFF + FastOpts.L_HIDDEN_BLOCKS_OFF
			+ FastOpts.L_PLATFORMS_OFF + FastOpts.L_LADDERS_OFF + FastOpts.L_TUBES_OFF + FastOpts.L_DIFFICULTY(0)
			+ FastOpts.L_ENEMY(Enemy.SPIKY, Enemy.GOOMBA) + FastOpts.VIS_FIELD(ReceptiveFieldMode.GRID)),

	/**
	 * Level where you have to jump.
	 */
	LEVEL_1_JUMPING(FastOpts.VIS_ON_2X + FastOpts.LEVEL_02_JUMPING),

	/**
	 * And here you must mind malicious GOOMBAs!
	 */
	LEVEL_2_GOOMBAS(FastOpts.VIS_ON_2X + FastOpts.LEVEL_02_JUMPING + FastOpts.L_ENEMY(Enemy.GOOMBA)),

	/**
	 * + Tubes with dangerous flowers.
	 */
	LEVEL_3_TUBES(FastOpts.VIS_ON_2X + FastOpts.LEVEL_02_JUMPING + FastOpts.L_ENEMY(Enemy.GOOMBA) + FastOpts.L_TUBES_ON),

	/**
	 * Here we're adding SPIKIES! (Cannot be killed by fireballs...)
	 */
	LEVEL_4_SPIKIES(FastOpts.VIS_ON_2X + FastOpts.LEVEL_02_JUMPING + FastOpts.L_ENEMY(Enemy.GOOMBA, Enemy.SPIKY) + FastOpts.L_TUBES_ON),

	/**
	 * Finally, level with green tourtles (so called KOOPAs).
	 */
	LEVEL_5_SPIKIES(FastOpts.VIS_ON_2X + FastOpts.LEVEL_02_JUMPING + FastOpts.L_ENEMY(Enemy.GOOMBA, Enemy.SPIKY, Enemy.GREEN_KOOPA) + FastOpts.L_TUBES_ON);

	private String options;

	private LevelConfig(String options) {
		this.options = options;
	}

	public String getOptions() {
		return options;
	}
}
