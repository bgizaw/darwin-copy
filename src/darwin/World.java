package darwin;

/**
 * This class includes the functions necessary to keep track of the creatures in
 * a two-dimensional world. 
 */

public class World {
	private Matrix<Creature> creatures;
	
	/**
	 * This method creates a new world consisting of width columns and height
	 * rows, each of which is numbered beginning at 0. A newly created world
	 * contains no objects.
	 */
	public World(int w, int h) {
		// BE CAREFUL: think about how width/heights translates to row/col in a matrix
		creatures = new Matrix<Creature>(h,w);
	}

	/**
	 * Returns the height of the world.
	 */
	public int height() {
		return creatures.numRows(); // FIX
	}

	/**
	 * Returns the width of the world.
	 */
	public int width() {
		return creatures.numCols(); // FIX
	}

	/**
	 * Returns whether position `pos` is in the world or not.
	 * 
	 * returns true *if* pos is an (x,y) location within the bounds of the board.
	 */
	public boolean inRange(Position pos) {
		if (pos.getY() < height() && pos.getX() < width() && pos.getY() > 0 && pos.getX() > 0) {
			return true; 
		}
		return false;
	}

	/**
	 * Set a position on the board to contain e.
	 * 
	 * @throws IllegalArgumentException if pos is not in range
	 */
	public void set(Position pos, Creature e) {
		if (inRange(pos)) {
			creatures.set(pos.getY(), pos.getX(), e);
		}
		else {
			throw new IllegalArgumentException("SetError: pos is not a valid coordinate in the World");
		}	
	}

	/**
	 * Return the contents of a position on the board.
	 * 
	 * @throws IllegalArgumentException if pos is not in range
	 */
	public Creature get(Position pos) {
		// BE CAREFUL: think about how x,y translates to row/col in a matrix
		if (inRange(pos)) {
			return creatures.get(pos.getY(), pos.getX());
		}
		else {
			throw new IllegalArgumentException("GetError: pos is not a valid coordinate in the World");
		}	
	}

	public String toString() {
		return "rows: " + creatures.numRows() + " cols: " + creatures.numCols();
	}

	public static void main(String args[]) {
		World world1 = new World(5,5);
		System.out.println(world1);
	}

}