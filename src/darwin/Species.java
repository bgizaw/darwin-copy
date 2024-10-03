package darwin;

import java.io.*;
import java.util.ArrayList;

/**
 * The individual creatures in the world are all representatives of some
 * species class and share certain common characteristics, such as the species
 * name and the program they execute. Rather than copy this information into
 * each creature, this data can be recorded once as part of the description for
 * a species and then each creature can simply include the appropriate species
 * reference as part of its internal data structure.
 * 
 * Note: The instruction addresses start at one, not zero.
 */
public class Species {
	private String name;
	private String color;
	private char speciesChar; // the first character of Species name
	private ArrayList<Instruction> program;

	/**
	 * Create a species for the given fileReader. 
	 */
	public Species(BufferedReader fileReader) {
			try {
				// initialize first instance variables of Species class using file reader
				name = fileReader.readLine();
				speciesChar = name.charAt(0);
				color = fileReader.readLine();

				// create an array of instructions
				program = new ArrayList<Instruction>();
				
				// look at next line (first set of instructions)
				String line = fileReader.readLine();

				// when we have another set of instructions add the Instruction x to our ArrayList program
				while (line != null){
					// create an instruction and opcode instance variable
					Instruction instruction;
					int lineOpcode;

					// a line that corresponds to instruction NOT requiring address
					// in every if else if, we add the Instruction instruction to program when condition met
					if (line.equals("hop")){
						lineOpcode = Instruction.HOP;
						instruction = new Instruction(lineOpcode);
						program.add(instruction);
					}
					else if (line.equals("left")){
						lineOpcode = Instruction.LEFT;
						instruction = new Instruction(lineOpcode);
						program.add(instruction);
					}
					else if (line.equals("right")){
						lineOpcode = Instruction.RIGHT;
						instruction = new Instruction(lineOpcode);
						program.add(instruction);
					}
					else if (line.equals("infect")){
						lineOpcode = Instruction.INFECT;
						instruction = new Instruction(lineOpcode);
						program.add(instruction);
					}

					// a line with instruction that takes an opcode AND address
					
					// splice the line by spaces
					// since lines are up to two words, instruction Array should be of size 2
					String[] instructionArray = line.split(" ");

					if (instructionArray[0].equals("ifempty")){
						lineOpcode = Instruction.IFEMPTY;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}
					else if (instructionArray[0].equals("ifwall")){
						lineOpcode = Instruction.IFWALL;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}
					else if (instructionArray[0].equals("ifsame")){
						lineOpcode = Instruction.IFSAME;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}
					else if (instructionArray[0].equals("ifenemy")){
						lineOpcode = Instruction.IFENEMY;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}
					else if (instructionArray[0].equals("ifrandom")){
						lineOpcode = Instruction.IFRANDOM;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}
					else if (instructionArray[0].equals("go")){
						lineOpcode = Instruction.GO;
						instruction = new Instruction(lineOpcode,Integer.parseInt(instructionArray[1]));
						program.add(instruction);
					}

					// read next line
					line = fileReader.readLine();
				}

			} catch (IOException e) {
				System.out.println(
					"Could not read file '"
						+ fileReader
						+ "'");
				System.exit(1);
			}
		
	}


	/**
	* Return the char for the species
	*/
	public char getSpeciesChar() {
		return speciesChar;
	}

	/**
	 * Return the name of the species.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the color of the species.
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Return the number of instructions in the program.
	 */
	public int programSize() {
		return program.size();    // FIX
	}

	/**
	 * Return an instruction from the program.
	 * @pre 1 <= i <= programSize().
	 * @post returns instruction i of the program.
	 */
	public Instruction programStep(int i) {
		return program.get(i-1);    // FIX
	}

	/**
	 * Return a String representation of the program.
	 * 
	 * do not change
	 */
	public String programToString() {
		String s = "";
		for (int i = 1; i <= programSize(); i++) {
			s = s + (i) + ": " + programStep(i) + "\n";
		}
		return s;
	}

}