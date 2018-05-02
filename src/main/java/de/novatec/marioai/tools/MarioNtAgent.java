package de.novatec.marioai.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import ch.idsia.ai.agents.Agent;
import ch.idsia.mario.engine.LevelScene;
import ch.idsia.mario.environments.Environment;
import de.novatec.mario.engine.generalization.Coordinates;
import de.novatec.mario.engine.generalization.Entities;
import de.novatec.mario.engine.generalization.Tiles;
import de.novatec.mario.engine.generalization.Entities.EntityType;
import de.novatec.mario.engine.generalization.Entity;
import de.novatec.mario.engine.generalization.Tile;
import de.novatec.mario.engine.generalization.Tiles.TileType;

/**
 * Helper class to implement a proper Agent.
 * Implement {@link #doAiLogic()},{@link #getName()} and if needed {@link #reset()}.
 * @author rgu
 *
 */
public abstract class MarioNtAgent implements Agent{
	
	private MarioInput input=new MarioInput();

	private MarioControl control=new MarioControl(input);
	
	private Environment env;
	
	private Tiles tiles;
	
	private Entities entities;
	
	private static final int ENEMY_CHECK_DISTANCE=4/*,BRICK_CHECK_DISTANCE=3*/;
	
	private List<Coordinates> coordList=new ArrayList<>(), oldCoordList=new ArrayList<>(); 
	private Color debugColor=Color.RED;

	/**
	 * Resets the agent. Should be overridden if needed.
	 */
	@Override
	public void reset() {		
	}

	/**
	 * Do not touch this.
	 */
	@Override
	public final boolean[] getAction(Environment observation) {
		
		env=observation;
		if(tiles==null) tiles=new Tiles(observation);
		else tiles.setEnvironment(observation);
		
		if(entities==null) entities=new Entities(observation);
		else entities.setEnvironment(observation);
		
		control.setEnvironment(observation);
		
		control.tick();
		input.tick();
		
		MarioInput input=doAiLogic();
		if(input==null) {
			System.err.println("MarioInput is null! Did you forget to return getMarioInput() in your doAiLogic Method?");
			System.err.println("Simulation will be stopped!");
			System.exit(1);
		}
		
		return input.toArray();
	}

	/**
	 * Returns the AGENT_TYPE of the agent, not very useful anymore.
	 */
	@Override
	public AGENT_TYPE getType() {
		return AGENT_TYPE.AI;
	}

	/**
	 * Must be overridden. Naming the agent. Can be any String.
	 */

	public abstract String getName();

	@Override
	public final void setName(String name) {
			}
	
	/**
	 * Implement the logic of your agent here! If you want to use MarioControl methods like moveRight(), jump() etc. return getMarioInput()! 
	 * @return MarioInput generated by Agent's logic, can not be null!
	 */
	public abstract MarioInput doAiLogic(); // must be implemented by agents who extend this!
		
	/**
	 * The actual MarioInput object contains all inputs generated by the actual MarioControl object (moveRight(), jump() etc.). You can also modify the actual MarioInput object yourself.
	 * @return the actual instance of MarioInput
	 */
	public final MarioInput getMarioInput() {
		return input;
	}
	
	/**
	 * Returns the actual MarioControl object, used to simplify MarioInput generation.
	 * @return the actual instance of MarioControl
	 */
	public final MarioControl getMarioControl() {
		return control;
	}
	
	////////////////////////////////
	// Mario interaction methods
	////////////////////////////////
	
	/**
	 * Mario will move right in the next frame.
	 */
	public final void moveRight() {
		this.getMarioControl().moveRight();
	}
	
	/**
	 * Mario will move left in the next frame.
	 */
	public final void moveLeft() {
		this.getMarioControl().moveLeft();
	}
	
	/**
	 * Mario will sprint in the next frame.
	 */
	public final void sprint() {
		this.getMarioControl().sprint();
	}
	
	/**
	 * Mario will jump (if possible) in the next frame.
	 */
	public final void jump() {
		this.getMarioControl().jump();
	}
	
	/**
	 * Mario will shoot (if possible) in the next frame.
	 */
	public final void shoot() {
		this.getMarioControl().shoot();
	}
	
	////////////////////////////////
	// Environment detection methods
	////////////////////////////////
	
	/**
	 * Can Mario shoot?
	 * @return a boolean value indicating whether Mario can shoot
	 */
	public final boolean mayShoot() {
		return this.env.mayMarioShoot();
	}
	
	/**
	 * Can Mario jump?
	 * @return a boolean value indicating whether Mario can jump
	 */
	public final boolean mayJump() {
		return this.env.mayMarioJump();
	}
	
	/**
	 * Mario's Map Position as Coordinates, contains X and Y position.
	 * @return an instance of Coordinates with Mario's (map) position.
	 */
	public final Coordinates getMarioPos(){
		return this.env.getMarioPos();
	}
	
	/**
	 * Mario's Physical Position as Coordinates
	 * @return an instance of Coordinates with Mario's (physical) position.
	 */
	public final Coordinates getMarioFloatPos() {
		return this.env.getMarioFloatPos();
	}
	
	/**
	 * Returns Mario's Map X Position
	 * @return an integer value with Mario's X position
	 */
	public final int getMarioMapX() {
		return this.env.getMarioMapX();
	}
	
	/**
	 * Returns Mario's Map Y Position
	 * @return an integer value with Mario's Y position
	 */
	public final int getMarioMapY() {
		return this.env.getMarioMapY();
	}
	
	/**
	 * Returns Mario's physical X Position
	 * @return a float value with Mario's physical X Position 
	 */
	public final float getMarioX() {
		return env.getLevelScene().getMarioX();
	}
	
	/**
	 * Returns Mario's physical Y Position
	 * @return a float value with Mario's physical Y Position
	 */
	public final float getMarioY() {
		return env.getLevelScene().getMarioY();
	}
	
	/**
	 * Is Mario on the ground?
	 * @return a boolean value indicating whether Mario is on the ground
	 */
	public final boolean isOnGround() {
		return this.env.isMarioOnGround();
	}
	
	/**
	 * Is Mario falling?
	 * @return a boolean value indicating whether Mario is falling
	 */
	public final boolean isFalling() {
		return this.env.isMarioFalling();
	}
	
	/**
	 * Is Mario carrying a shell?
	 * @return a boolean value indicating whether Mario is carrying a shell
	 */
	public final boolean isCarrying() {
		return this.env.isMarioCarrying();
	}
	
	///--- Simple Detection Methods
	
	/**
	 * Is a brick ahead? Defined as: isBrick(1,0)||isBrick(1,-1)||isBrick(2, 0)||isBrick(2, -1)||isBrick(3, 0)||isBrick(3, -1) (all Coordinates relative to Mario)
	 * @return a boolean value indicating whether a brick is ahead
	 */
	public final boolean isBrickAhead() {
		return isBrick(1,0)||isBrick(1,-1)||isBrick(2, 0)||isBrick(2, -1)||isBrick(3, 0)||isBrick(3, -1);
	}
	
	/**
	 * Is a slope ahead? Defined as: isBrick(0,1)&amp;&amp;isEmpty(1, 1)&amp;&amp;isEmpty(2, 1) (all Coordinates relative to Mario)
	 * @return a boolean value indicating whether a slope is ahead 
	 */
	public final boolean isSlopeAhead() {
		return isBrick(0,1)&&isEmpty(1, 1)&&isEmpty(2, 1);
	}
	
	/**
	 * Is a deep slope ahead? Defined as: isSlopeAhead()&amp;&amp;isEmpty(1,2)&amp;&amp;isEmpty(2, 2); (all Coordinates relative to Mario)
	 * @return a boolean value indicating whether a deep slope is ahead 
	 */
	public final boolean isDeepSlopeAhead() {
		return isSlopeAhead()&&isEmpty(1,2)&&isEmpty(2, 2);
	}
	
	/**
	 * Is an enemy ahead? 
	 * @return a boolean value indicating whether an enemy is ahead
	 */
	public final boolean isEnemyAhead() {

		for(int i=1;i<ENEMY_CHECK_DISTANCE;i++) {
			if(isDangerousAt(i, 0)||isDangerousAt(i, -1)) return true;
		}
		return false;
	}
	
	/**
	 * Is a hole ahead? Defined as: isEmpty(1,env.getLevelScene().getLevelHight()-1-(int)getMarioY()/16)&amp;&amp;isEmpty(2,env.getLevelScene().getLevelHight()-1-(int)getMarioY()/16)
	 * @return a boolean value indicating whether a hole is ahead 
	 */
	public final boolean isHoleAhead() { 
		return isEmpty(1,env.getLevelScene().getLevelHight()-1-(int)getMarioY()/16)&&isEmpty(2,env.getLevelScene().getLevelHight()-1-(int)getMarioY()/16);
	}
	
	/**
	 * Is Mario beyond a questionbrick? 
	 * @return a boolean value indicating whether a questionbrick is above Mario
	 */
	public final boolean isQuestionbrickAbove() {
		return isQuestionbrick(0,-1)||isQuestionbrick(0,-2)||isQuestionbrick(0,-3);
	}
	
	////--- Tiles - Abstracted Level Information
	
	/**
	 * Returns the tiles member, contains all Tile objects in Mario's view.
	 * @return the actual instance of Tiles 
	 */
	public final Tiles getTiles() {
		return tiles;
	}

	/**
	 * Returns all tiles on screen that are defined as interactive. (Questionbrick,Breakable Brick, Coins)
	 * @return a list of all interactive Tile objects on Screen
	 */
	public final List<Tile> getInteractiveBlocksOnScreen(){
		return tiles.getInteractiveBlocksOnScreen();
	}
	
	/**
	 * Gets the type of the Tile at (x,y) (relative to Mario)
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return the TileType of the Tile at (x,y) 
	 */
	public final TileType getTile(int x, int y) {
		return tiles.getTile(x, y);
	}
	
	/**
	 * Is there a Brick at (x,y)? 
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether a brick is at (x,y).
	 */
	public final  boolean isBrick(int x,int y) {
		return tiles.isBrick(x, y);
	}
	
	/**
	 * Is there no Tile at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether no tile is at (x,y).
	 */
	public final boolean isEmpty(int x, int y) {
		return tiles.isEmpty(x, y);
	}
	
	/**
	 * Is there some Tile at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether any kind of tile is at (x,y) (except TileType.NOTHING).
	 */
	public final boolean isNotEmpty(int x,int y) {
		return tiles.isNotEmpty(x, y);
	}
	
	/**
	 * Is there a questionbrick at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether a questionbrick is at (x,y).
	 */
	public final boolean isQuestionbrick(int x,int y) {
		return tiles.isQuestionbrick(x, y);
	}
	
	////--- Entities - Abstracted Enemy Detection
	
	/**
	* Returns the Entities member, contains all Entity objects in Mario's view.
	 * @return the actual instance of Entities 
	 */
	public final Entities getEntities() {
		return entities;
	}
	
	/**
	 * Returns all entities at (x,y).
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a list with all entities at (x,y)
	 */
	public final List<Entity> getEntitiesAt(int x,int y){
		return entities.getEntitiesAt(x, y);
	}
	
	/**
	 * Returns all entities on screen.
	 * @return a list with all entities in Mario's View
	 */
	public final List<Entity> getAllEntitiesOnScreen(){
		return entities.getEntitiesOnScreen();
	}
	
	/**
	 * Returns all entities defined as enemies on screen. (Goomba, Kooper, EnemyFlower etc.)
	 * @return a list with all dangerous entities in Mario's View
	 */
	public final List<Entity> getAllEnemiesOnScreen(){
		return entities.getEnemiesOnScreen();
	}
	
	/**
	 * Returns all entities defined as enemies at (x,y).
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a list with all dangerous entities at (x,y)
	 */
	public final List<Entity> getEnemiesAt(int x,int y){
		return entities.getEnemiesAt(x,y);
	}
	
	/**
	 * Returns all entities defined as collectibles on screen. (Mushroom &amp; FireFlower)
	 * @return a list with all collectible entities in Mario's View
	 */
	public final List<Entity> getCollectiblesOnScreen(){
		return entities.getCollectiblesOnScreen();
	}
	
	/**
	 * Returns all entities defined as collectibles at (x,y).
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a list with all collectible entities at (x,y)
	 */
	public final List<Entity> getCollectiblesAt(int x, int y){
		return entities.getCollectiblesAt(x, y);
	}
		
	/**
	 * Returns the EntityType of the most dangerous Entity at (x,y).
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return the EntityType of the most dangerous Entity at (x,y) 
	 */
	public final EntityType getMostDangrousEntityTypeAt(int x,int y) {
		return entities.getMostDangrousEntityTypeAt(x, y);
	}
	
	/**
	 * Is there no Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether no entity is at (x,y)
	 */
	public final boolean isNothingAt (int x,int y) {
		return entities.isNothingAt(x, y);
	}
	
	/**
	 * Is there an Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether an entity is at (x,y)
	 */
	public final boolean isSomethingAt (int x, int y) {
		return entities.isSomethingAt(x, y);
	}
	
	/**
	 * Is a dangerous Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether a dangerous entity is at (x,y)
	 */
	public final boolean isDangerousAt(int x, int y) {
		return entities.isDangerousAt(x, y);
	}
	
	/**
	 * Is a squishable Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether a squishable entity is at (x,y)
	 */
	public final boolean isSquishableAt(int x, int y) {
		return entities.isSquishableAt(x, y);
	}
	
	/**
	 * Is a shootable Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating whether a shootable entity is at (x,y)
	 */
	public final boolean isShootableAt(int x, int y) {
		return entities.isShootableAt(x, y);
	}
	
	/**
	 * Is a collectible Entity at (x,y)?
	 * @param x x coordinate to check (relative to Mario)
	 * @param y y coordinate to check (relative to Mario)
	 * @return a boolean value indicating wether a collectible entity is at (x,y)
	 */
	public final boolean isCollectableAt(int x,int y) {
		return entities.isCollectableAt(x, y);
	}
	
	/**
	 * The eucledian Distance between Mario and the given Entity. 
	 * @param entity Entity to measure the distance to
	 * @return a double value with the euclidean distance between Mario and the given Entity
	 */
	public final double getDistance(Entity entity) {
		return Coordinates.getDistanceFromTo(0, 0, entity.getRelX(), entity.getRelY());
	}
	
	//--- A* Helper Methods
	
	/**
	 * Returns a deep copy of the actual LevelScene of the {@link #env}.
	 * @return a exact deep copy of the actual LevelScene
	 */
	public final LevelScene getDeepCopyOfLevelScene(){
		return env.getLevelScene().getDeepCopy();
	}
	
	/**
	 * Returns a lightweight copy of the actual LevelScene of the {@link #env}. Contains every object needed to perform  tick().
	 * @return a lightweight copy of the actual LevelScene
	 */
	public final LevelScene getAStarCopyOfLevelScene() {
		return env.getLevelScene().getAStarCopy();
	}
	
	/**
	 * The score of the actual LevelScene. The value of score is determined by the given task. 
	 * @return a double value with the Score of the actual LevelScene 
	 */
	public final double getActualScore() {
		return env.getLevelScene().getScore();
	}
	
	/**
	 * Returns a byte representation of mario's complete view
	 * @param zLevelScene generalization factor of the levelscene
	 * @param zLevelEnemies generalization factor of the enemies
	 * @return a byte[] array 
	 */
	public byte[][] getMergedObservationZ(int zLevelScene, int zLevelEnemies) {
        return env.getMergedObservationZ(zLevelScene, zLevelEnemies);
	}
	
	/**
	 * Returns a byte representation of mario's levelscene view
	 * @param zLevelScene  generalization factor of the levelscene
	 * @return a byte[] array 
	 */
	public byte[][] getLevelSceneObservationZ(int zLevelScene) {
        return getLevelSceneObservationZ(zLevelScene);
	}

	/**
	 * Returns a byte representation of mario's enemy view
	 * @param zLevelEnemies generalization factor of the enemies
	 * @return a byte[] array
	 */
	public byte[][] getEnemiesObservationZ(int zLevelEnemies) {
         return env.getEnemiesObservationZ(zLevelEnemies);
	}
	
	////////////////////////////////
	// Debug methods
	////////////////////////////////

	
	/**
	 * Shows an Ascii representation of Mario's view
	 */
	public final void showMarioViewAsAscii() {
		env.showMarioViewAsAscii();
	}
	
	/**
	 * Given Coordinate will be added to the List of Coordinates to be drawn if Debug View is enabled.
	 * @param coord next Coordinate to draw a line to
	 */
	protected final void addCoordToDraw(Coordinates coord) {
		 this.coordList.add(coord);
	}
	
	/**
	 * Standard debug draw.
	 * @param og the Graphics object to draw to
	 * @param debug indicating wheather debug view should be drawn
	 * @param delete indicating wheather old Coordinate objects should be  
	 */
	public void debugDraw(Graphics og,boolean debug,boolean delete) {
		
		if(!delete) coordList=getDeepCopy(oldCoordList);
		if(debug) {
		Coordinates oldCoords=null; 
		Color oldColor=og.getColor();
		og.setColor(debugColor);
		
		for(Coordinates next:coordList) {
			if(oldCoords!=null)og.drawLine((int) (oldCoords.getX()-env.getLevelScene().getMarioXCam()), (int) oldCoords.getY(), (int) (next.getX()-env.getLevelScene().getMarioXCam()), (int) next.getY());
			oldCoords=next;
		}
		og.setColor(oldColor);
		}
		oldCoordList.clear();
		oldCoordList=getDeepCopy(coordList);
		coordList.clear();
	}
	
	/**
	 * Helper Method to make a deep copy of a coordinates list
	 * @param toCopy the list to copy
	 * @return a deep copy of the given list
	 */
	private List<Coordinates> getDeepCopy(List<Coordinates> toCopy){
		List<Coordinates> res=new ArrayList<>();
		
		for(Coordinates next:toCopy) {
			res.add(new Coordinates(next.getX(),next.getY()));
		}
		return res;
	}
	
	/**
	 * Sets the Color that is used by {@link #debugDraw(Graphics, boolean, boolean)} to draw on the screen.
	 * @param c the new Color for debugDraw 
	 */
	protected void setColor(Color c) {
		this.debugColor=c;
	}
	
}
