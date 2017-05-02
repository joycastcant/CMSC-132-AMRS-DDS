import java.util.HashMap;

public class Decode {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	private static  HashMap<String, Integer> reg;
	public static int ir = -1;
	public static boolean occupied = false;
	public static Instruction value;
	
	public Decode(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.reg = msc.getRegisters();
		this.msc = msc;
	}

	public static int getValues() {
		if(ir!=-1) System.out.println("decoding "+ir);
		return ir;
		//Main.execute.performOperation(operand, op1, op2, ir);
	}
	
	public static void occupy() {
		occupied = true;
	}
	
	public static void free() {
		//if(occupied) System.out.println("yes");
		Main.execute.setIr(ir);
		Main.execute.occupy();
		Main.execute.setDest(value.getOp1());
		Main.execute.setOperand(value.getOperation());
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
	}
	
	/* OLD
	public static void performOperation(String operand, String op1, String op2) {
		if (operand.equals("LOAD")){
			int oP2 = Integer.valueOf(op2);
			Main.execute.load(op1, oP2);
		}

		if (operand.equals("ADD")){
			Main.execute.add(op1, op2);
		}

		if (operand.equals("SUB")){
			Main.execute.sub(op1, op2);
		}

		if (operand.equals("CMP")){
			Main.execute.cmp(op1, op2);
		}
	}
	*/
	
	/*			OLD
	public Decode(HashMap<Integer, Instruction> instructions, int ir, MSC msc){
		System.out.println("Decoding...");
		this.instructions = instructions;
		this.occupied = true;
	
		Instruction value = instructions.get(ir);
		Execute execute = new Execute(msc);
		String operand = value.getOperation();
		String op1 = value.getOp1();
		String op2 = value.getOp2();

		System.out.println("Instruction: " + value.getInstruction());

		if (operand.equals("LOAD")){
			int oP2 = Integer.valueOf(op2);
			execute.load(op1, oP2);
		}

		if (operand.equals("ADD")){
			execute.add(op1, op2);
		}

		if (operand.equals("SUB")){
			execute.sub(op1, op2);
		}

		if (operand.equals("CMP")){
			execute.cmp(op1, op2);
		}
	}			*/
	
	
}