import java.util.HashMap;

public class WriteBack {
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	private static  HashMap<Integer, Instruction> instructions;
	private static int val;
	private static String dest;
	public static int ir = -1;
	public static String reg1 ="w_none", reg2="w_none";
	public static boolean stall = false;
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
			if(Main.instructions.containsKey(ir))Main.instructions.get(ir).stages.add("W"+ir); //add W to pipeline table
			msc.setReg(dest, val);
			System.out.println(reg);
			/* for(int i = 0; i<UI.model.getRowCount(); i++) {
				if(instructions.get(ir) != null && instructions.get(ir).getInstruction().equals(UI.model.getValueAt(i, 0))) {
						UI.model.setValueAt(""+Main.clockCycles,i,5);
						break;
				}
			} */
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