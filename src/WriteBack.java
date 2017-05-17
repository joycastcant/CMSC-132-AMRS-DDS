import java.util.HashMap;

public class WriteBack {
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	private static  HashMap<Integer, Instruction> instructions;
	private static int val;
	private static String dest;
	public static int ir = -1;
	public static boolean occupied = false;
	
	public WriteBack(HashMap<Integer, Instruction> instructions, MSC msc) {
		this.instructions = instructions;
		this.msc = msc;	
		this.reg = msc.getRegisters();
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static int write() {
		if(ir!=-1) {
			System.out.println("write back by "+ir);
			msc.setReg(dest, val);
			System.out.println(reg);
		}
		return ir;
	}
	
	public static void free() {
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