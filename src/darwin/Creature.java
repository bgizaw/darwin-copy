package darwin;

import java.util.*;

/**
 * This class represents one creature on the board. Each creature must remember
 * its species, position, direction, and the world in which it is living.
 * In addition, the Creature must remember the next instruction out of its
 * program to execute.
 * The creature is also responsible for making itself appear in the WorldMap. In
 * fact, you should only update the WorldMap from inside the Creature class.
 */
public class Creature {
	Species species;
	World world;
	Position pos;
	int dir;

	/**
	 * Create a creature of the given species, with the indicated position and
	 * direction. Note that we also pass in the world-- remember this world, so
	 * that you can check what is in front of the creature and update the board
	 * when the creature moves.
	 */
	public Creature(Species species, World world, Position pos, int dir) {
		this.species = species;
		this.world = world;
		this.pos = pos;
		this.dir = dir;
	}

		
	/**
	 * Return the species of the creature.
	 */
	public Species species() {
		return species;
	}

	/**
	 * Return the current direction of the creature.
	 */
	public int direction() {
		return dir;
	}

	/**
	 * Sets the current direction of the creature to the given value 
	 */
	public void setDirection(int dir){
		this.dir = dir;
	}

	/**
	 * Return the position of the creature.
	 */
	public Position position() {
		return pos; // fix
	}

	/**
	 * Execute steps from the Creature's program
	 *   starting at step #1
	 *   continue until a hop, left, right, or infect instruction is executed.
	 */
	public void takeOneTurn() {
		int stepNum = 1;
		Instruction step;
		
		do {
			step = species.programStep(stepNum); // get step
			// get x and y
			int x = pos.getX();
			int y = pos.getY();
			int direction = direction();

			if (step.getOpcode() == 5){ // ifempty instruction
				Creature test;

				if (direction == 0) { // if north
					Position newPos = new Position(x, y+1);
					test = world.get(newPos);
					System.out.println(test);
				}
				else if (direction == 1) { // if east
					Position newPos = new Position(x+1, y);
					test = world.get(newPos);
					System.out.println(test);
				}
				else if (direction == 2) { // if south
					Position newPos = new Position(x, y-1);
					test = world.get(newPos);
					System.out.println(test);
				}
				else if (direction == 3) { // if west
					Position newPos = new Position(x-1, y);
					test = world.get(newPos);
					System.out.println(test);
				}
				
				// if there is a creature, move to line in program Address
				if (test){
					System.out.println("found a creature");
					stepNum = step.getAddress();
				}
				else {
					System.out.println("did not find a creature");
					stepNum++;
				}


				// if direction facing up
					// get position
					// evaluate  pos column + 1
					// world.get().equals(null);
				
				// if direction facing down
					// get position
					// check pos column -1 

				// if direction facing left
					// get position
					// check pos row -1

				// if direction facing right
					// check pos row + 1 
					
				// check position
				// check direction
				// check square in front
				// if empty, stepNum = step.getAddress();
				// otherwise, stepNum++

			}
			else if (step.getOpcode() == 6){ // if wall
				
			}
			else if (step.getOpcode() == 7){ // if same
				
			}
			else if (step.getOpcode() == 8){ // if enemy
				
			}
			else if (step.getOpcode() == 9){ // if random
				
			}
			else if (step.getOpcode() == 10){ // go
				stepNum = step.getAddress();
			}
			else{
				stepNum++;
			}

		}
		while (step.getOpcode() > 4);
		
		// if hop
		// if left
		// if right
		// if infect
		// terminate

		//
		
		// at the end, update world and matrix and position
		
	}

	/**
	 * Return the compass direction the is 90 degrees left of the one passed in.
	 */
	public static int leftFrom(int direction) {
		return (4 + direction - 1) % 4;
	}

	/**
	 * Return the compass direction the is 90 degrees right of the one passed
	 * in.
	 */
	public static int rightFrom(int direction) {
		return (direction + 1) % 4;
	}

	public static void main() {
		BufferedReader in = new BufferedReader(new FileReader("./Creatures/Rover.txt"));
		Species sp = new Species(in);
		World w = new World(5, 5);
		Position pos = new Position(1,1);
		int dir = 1;
		Creature creature1 = new Creature(sp, w, pos, dir);
		creature1.takeOneTurn();
		System.out.println("terminated");
	}
}
