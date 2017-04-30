import java.util.HashMap;

public class Fetch {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	public static boolean occupied = false;
	public static int ir = -1;

	/*public Fetch(HashMap<Integer, Instruction> instructions, boolean occupied, int ir, MSC msc){
		System.out.println("Fetching the data...");
		this.instructions = instructions;
		this.occupied = occupied;
		msc.setMar(msc.getPC());
		msc.setMbr(instructions.get(msc.getMar()));
		msc.incrementPC();
		Decode decode = new Decode(instructions, ir, msc);
	}		OLD		*/
	
	// NEW
	public Fetch(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
	}
	
	public static void fetchInstruction() {
		
		msc.setMar(msc.getPC());
		ir = msc.getPC();
		System.out.println("fetching "+ir);
		//try {
		msc.setMbr(instructions.get(msc.getMar()));
		if(msc.getMbr()!=null) {
			msc.incrementPC();
			occupied = true;
		}
		//} catch(ArrayIndexOutOfBoundsException ee) {System.out.println("Out of bounds");}
		
		//Main.decode.getValues(ir);
		//Decode decode = new Decode(instructions, ir, msc);
	}
	
	public static void free() {
		Main.decode.setIr(ir);
		ir = -1;
		occupied = false;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
}