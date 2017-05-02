import java.util.HashMap;

public class MemoryAccess {
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	private static  HashMap<Integer, Instruction> instructions;
	private static int val;
	private static String dest;
	public static int ir = -1;
	public static boolean occupied = false;
	
	public MemoryAccess(HashMap<Integer, Instruction> instructions, MSC msc) {
		this.instructions = instructions;
		this.msc = msc;
	}
	
	public static void accessMem() {
		// do something
		if(ir!=-1) System.out.println("memaccess by "+ir);
	}
	
	public static void free() {
		Main.wb.setIr(ir);
		Main.wb.occupy();
		Main.wb.setDest(dest);
		Main.wb.setVal(val);
		setIr(-1);
		occupied = false;
	}
	
	public static void occupy() {
		occupied = true;
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