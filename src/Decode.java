import java.util.HashMap;

public class Decode {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	public static int ir = -1;
	public static boolean occupied = false;
	public static Instruction value;
	public static String reg1 ="d_none", reg2="d_none";
	public static boolean stall = false;
	
	public Decode(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.reg = msc.getRegisters();
		this.msc = msc;
	}

	public static int getValues() {
		if(ir!=-1) {
			System.out.println("decoding "+ir);
			
			if(Main.instructions.containsKey(ir))Main.instructions.get(ir).stages.add("D");
		}
		return ir;
	}
	
	
	public static void occupy() {
		occupied = true;
	}
	
	public static void free() {
		Main.execute.setIr(ir);
		Main.execute.occupy();
		Main.execute.setDest(value.getOp1());
		Main.execute.setOperand(value.getOperation());
		
		Main.execute.reg1 = reg1;
		Main.execute.reg2 = reg2;
		reg1 = "d_none";
		reg2 = "d_none";
		
		Main.execute.setOp1(reg.get(value.getOp1()));
		

		if(value.getOperation().equals("LOAD"))
			Main.execute.setOp2(Integer.valueOf(value.getOp2()));
		else
			Main.execute.setOp2(reg.get(value.getOp2()));
		setIr(-1);
		
		
		occupied = false;
	}
	
	public static void setIr(int i) {
		ir = i;
	}
	
	public static boolean isOccupied() {
		return occupied;
	}

	public static void setValue(Instruction val){
		value = val;
		reg1 = val.getOp1();
		if(val.getOp2().charAt(0)=='R') reg2 = val.getOp2();
		else reg2 = "";
	}	
}