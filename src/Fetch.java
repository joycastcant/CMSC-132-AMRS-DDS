import java.util.HashMap;

public class Fetch {
	public static HashMap<Integer, Instruction> instructions;
	public static boolean occupied;

	public Fetch(HashMap<Integer, Instruction> instructions, boolean occupied, int ir, MSC msc){
		System.out.println("Fetching the data...");
		this.instructions = instructions;
		this.occupied = occupied;
		msc.incrementPC();
		Decode decode = new Decode(instructions, ir, msc);
	}
}