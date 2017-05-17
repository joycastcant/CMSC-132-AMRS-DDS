import java.util.HashMap;

public class WriteBack {
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	private static  HashMap<Integer, Instruction> instructions;
	private static int val;
	private static String dest;
	public static int ir;
	public static String reg1, reg2;
	public static boolean stall;
	public static boolean occupied;

	public WriteBack(HashMap<Integer, Instruction> instructions, MSC msc) {
		this.instructions = instructions;
		this.msc = msc;	
		this.reg = msc.getRegisters();
		this.reg1="w_none";
		this.reg2="w_none";
		this.stall=false;
		this.occupied=false;
		this.ir=-1;
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static int write() {
		if(ir!=-1) {
			System.out.println("write back by "+ir);
			if(Main.instructions.containsKey(ir))Main.instructions.get(ir).stages.add("W"); //add W to pipeline table
			msc.setReg(dest, val);
			System.out.println(reg);
		}
		return ir;
	}
	
	public static void free() {
		reg1 ="w_none";
		reg2="w_none";
		setIr(-1);
		occupied = false;
	}
	
	public static void setIr(int i) {
		ir = i;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}
	
	public static void setDest(String destination){
		dest = destination;
	}

	public static void setVal(int value){
		val = value;
	}
}