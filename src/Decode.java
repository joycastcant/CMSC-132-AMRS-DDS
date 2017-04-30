import java.util.HashMap;

public class Decode {
	public static HashMap<Integer, Instruction> instructions;
	public static MSC msc;
	public static int ir = -1;
	public static boolean occupied = false;
	
	public Decode(HashMap<Integer, Instruction> instructions, MSC msc){
		this.instructions = instructions;
		this.msc = msc;
	}
	
	public static void getValues() {
		if(ir!=-1) {
			System.out.println("decoding "+ir);
			Instruction value = msc.getMbr();
			try{
				Main.execute.setOperand(value.getOperation());
				Main.execute.setOp1(value.getOp1());
				Main.execute.setOp2(value.getOp2());
				//performOperation(operand, op1, op2);
				occupied = true;
			} catch(NullPointerException ee) {}
		}
		//Main.execute.performOperation(operand, op1, op2, ir);
	}
	
	public static void free() {
		Main.execute.setIr(ir);
		setIr(-1);
		occupied = false;
	}
	
	public static void setIr(int i) {
		ir = i;
	}
	
	public static boolean isOccupied() {
		return occupied;
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