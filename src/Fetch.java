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
	
	public static int fetchInstruction() {
		
		msc.setMar(msc.getPC());
		ir = msc.getPC();
		
		//try {
		msc.setMbr(instructions.get(msc.getMar()));
		if(msc.getMbr()!=null) {
			System.out.println("fetching "+ir);
			msc.incrementPC();
			occupied = true;
		}
		return ir;
		//} catch(ArrayIndexOutOfBoundsException ee) {System.out.println("Out of bounds");}
		
		//Main.decode.getValues(ir);
		//Decode decode = new Decode(instructions, ir, msc);
	}
	
	public static void occupy() {
		occupied = true;
	}

	public static void free(Boolean hazard) {
		if(hazard == true){
			Main.decode.setIr(-1);	
		}
		else{
			Main.decode.setIr(ir);
			Main.decode.occupy();
			Main.decode.setValue(msc.getMbr());
			ir = -1;
			occupied = false;
		}
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
}