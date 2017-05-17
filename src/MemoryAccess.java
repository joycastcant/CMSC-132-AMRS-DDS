import java.util.HashMap;

public class MemoryAccess {
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	private static  HashMap<Integer, Instruction> instructions;
	private static int val;
	private static String dest;
	public static int ir = -1;
	public static String reg1 ="m_none", reg2="m_none";
	public static boolean stall = false;
	public static boolean occupied = false;
	
	public MemoryAccess(HashMap<Integer, Instruction> instructions, MSC msc) {
		this.instructions = instructions;
		this.msc = msc;
	}
	
	public static void accessMem() {
		// do something
		if(ir!=-1) {
			System.out.println("memaccess by "+ir);
			
			if(Main.instructions.containsKey(ir))Main.instructions.get(ir).stages.add("M"); //add M to pipeline table
			
			/* for(int i = 0; i<UI.model.getRowCount(); i++) {
				if(instructions.get(ir) != null && instructions.get(ir).getInstruction().equals(UI.model.getValueAt(i, 0))) {
						UI.model.setValueAt(""+Main.clockCycles,i,4);
						break;
				}
			} */
		}
	}
	
	public static void free() {
		Main.wb.reg1 = reg1;
		Main.wb.reg2 = reg2;
		reg1 ="m_none";
		reg2="m_none";
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